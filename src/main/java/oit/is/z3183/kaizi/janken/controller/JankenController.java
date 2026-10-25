package oit.is.z3183.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z3183.kaizi.janken.model.Janken;
import oit.is.z3183.kaizi.janken.model.MatchMapper;
import oit.is.z3183.kaizi.janken.model.Match;
import oit.is.z3183.kaizi.janken.model.User;
import oit.is.z3183.kaizi.janken.model.UserMapper;
import oit.is.z3183.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;
  @Autowired
  UserMapper userMapper;
  @Autowired
  MatchMapper matchMapper;

  @GetMapping("/janken")
  public String janken(
      @RequestParam(required = false) String hand,
      Principal principal,
      Model model) {

    // if (principal != null) {
    // model.addAttribute("username", principal.getName());

    String loginUser = principal.getName();

    entry.addUser(loginUser);
    model.addAttribute("username", loginUser);
    model.addAttribute("users", entry.getUsers());

    ArrayList<User> users = userMapper.selectAll();
    model.addAttribute("users1", users);

    ArrayList<Match> matches = matchMapper.selectAllMatch();
    model.addAttribute("matches1", matches);
    // ArrayList<Match> matches = matchMapper.selectAllMatch();
    // System.out.println("試合結果の件数：" + matches.size());

    if (hand != null) {
      Janken janken = new Janken(hand, "グー");
      model.addAttribute("myHand", janken.getMyHand());
      model.addAttribute("cpuHand", janken.getCpuHand());
      model.addAttribute("result", janken.getResult());
    }

    return "janken.html";
  }

  @GetMapping("step8")
  public String sample38(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);

    return "sample37.html";
  }

  @PostMapping("step5")
  public String sample45(@RequestParam String userName, ModelMap model) {
    ArrayList<User> users = userMapper.selectAll();
    model.addAttribute("users1", users);
    return "janken.html";
  }

  @GetMapping("/match")
  public String match(
      @RequestParam("id") int id,
      Principal principal,
      Model model) {

    // ログインしているユーザー名を取得
    String loginUser = principal.getName();
    model.addAttribute("loginUser", loginUser);

    User opponent = userMapper.selectById(id);
    model.addAttribute("opponent", opponent);
    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(
      @RequestParam("id") int id,
      @RequestParam("hand") String hand,
      Principal principal,
      Model model) {
    String loginUser = principal.getName();
    model.addAttribute("loginUser", loginUser);
    User myUser = userMapper.selectByUserName(loginUser);

    User opponent = userMapper.selectById(id);
    model.addAttribute("opponent", opponent);

    String cpuHand = "Gu";
    String result;

    if (hand.equals(cpuHand)) {
      result = "引き分け";
    } else if ((hand.equals("Gu") && cpuHand.equals("Choki")) ||
        (hand.equals("Choki") && cpuHand.equals("Pa")) ||
        (hand.equals("Pa") && cpuHand.equals("Gu"))) {
      result = "勝ち";
    } else {
      result = "負け";
    }

    model.addAttribute("result", result);

    Match match = new Match();
    match.setUser1(myUser.getID());
    match.setUser2(opponent.getID());
    match.setUser1Hand(hand);
    match.setUser2Hand(cpuHand);

    matchMapper.insertMatch(match);

    // 画面表示用のデータを渡す
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);
    model.addAttribute("myHand", hand);
    model.addAttribute("cpuHand", cpuHand);

    return "match.html";
  }
}
