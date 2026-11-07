package oit.is.z3183.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("SELECT * from matches")
  ArrayList<Match> selectAllMatch();

  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) " +
      "VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{isActive})")
  int insertMatch(Match match);

    @Select("SELECT * FROM matches WHERE isActive = TRUE AND user1 = #{userId}")
  ArrayList<Match> selectActiveByUser1(@Param("userId") int userId);

  @Update("UPDATE matches SET isActive = FALSE WHERE id = #{id}")
  int deactivate(int id);

}
