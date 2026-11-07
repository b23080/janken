package oit.is.z3183.kaizi.janken.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z3183.kaizi.janken.model.MatchInfo;
import oit.is.z3183.kaizi.janken.model.MatchInfoMapper;
import oit.is.z3183.kaizi.janken.model.Janken;
import oit.is.z3183.kaizi.janken.model.MatchMapper;
import oit.is.z3183.kaizi.janken.model.Match;
import oit.is.z3183.kaizi.janken.model.User;
import oit.is.z3183.kaizi.janken.model.UserMapper;

@Service
public class AsyncKekka {
  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper matchMapper;

  /**
   * 自分が先手(user1)で isActive=TRUE の試合を取得する
   */
  @Transactional
  public ArrayList<Match> syncShowActive(int userId) {
    return matchMapper.selectActiveByUser1(userId);
  }

  /**
   * 表示済みの試合を isActive=FALSE にする
   */
  @Transactional
  public void syncDeactivate(ArrayList<Match> matches) {
    for (Match m : matches) {
      matchMapper.deactivate(m.getId());
    }
  }

  /**
   * 結果が出るまで0.5秒ごとにDBを見て、見つかったらブラウザに送る
   */
  @Async
  public void asyncShowResult(SseEmitter emitter, int userId) {
    boolean dbUpdated = false;
    try {
      while (true) {
        // DBを確認して、結果があれば dbUpdated を true にする
        ArrayList<Match> matches = this.syncShowActive(userId);
        dbUpdated = !matches.isEmpty();

        // 結果がまだなら 0.5s 休む
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }

        // 結果があればブラウザに send
        emitter.send(matches);

        // dbUpdated を false に戻し、その後 isActive を FALSE に更新
        dbUpdated = false;
        this.syncDeactivate(matches);
        break; // 1回送ったら終了
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowResult complete");
  }
}
