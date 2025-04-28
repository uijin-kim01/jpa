package com.jjang051.jpa.repository;

import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    // query method
    Optional<Member> findByUserID(String userID);
    // ex) select * from member where userID = ?

    Optional<Member> findByUserEmail(String userEmail);
    // ex) select * from member where userEmail = ?

    Optional<Member> findByUserIDAndUserEmail(String userID, String userEmail);
    // ex) select * from member where userID = ? and userEmail = ?

    Optional<Member> findByUserIDOrUserEmail(String userName, String userEmail);
    // ex) select * from member where userID = ? or userEmail = ?

    List<Member> findByUserNameLike(String userName);
    // ex) select * from member where userID is like ?

    List<Member> findByIdBetween(int min, int max);
    // ex) select * from member where age between a and b

//    @Query("select CM from Member CM where CM.userName =:userName")
//    List<Member> findByCustomMember(@Param("userName")String userName);
//
//    @Query(value = "select CM from entity_Member CM where CM.userName =:userName",nativeQuery = true)
//    List<Member> findByNativeMember(@Param("userName")String userName);

}
