package oit.is.z3183.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {
  @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) " +
      "VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive})")
  int insertMatchInfo(MatchInfo matchInfo);

  @Select("SELECT * FROM matchinfo WHERE isActive = TRUE")
  ArrayList<MatchInfo> selectActive();

    @Select("SELECT * FROM matchinfo WHERE isActive = TRUE AND user1 = #{opponentId} AND user2 = #{myId} ORDER BY id LIMIT 1")
  MatchInfo selectWaiting(@Param("opponentId") int opponentId, @Param("myId") int myId);

  @Update("UPDATE matchinfo SET isActive = FALSE WHERE id = #{id}")
  int deactivate(int id);
}
