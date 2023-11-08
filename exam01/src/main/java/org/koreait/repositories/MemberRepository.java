package org.koreait.repositories;

import org.koreait.entities.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {
    //CrudRepository를 상속받음으로써 DAO를 대신한다.

    Member findByUserId(String userId);
    //쿼리 메서드. 쿼리를 수행하는 구현체. 단,메서드명은 패턴이 정해져있다.

    List<Member> findByRegDtBetween(LocalDateTime sdate, LocalDateTime edate, Pageable pageable);

    List<Member> findByUserNmContainingOrderByRegDtDesc(String userNm);

    //JOIN 등 복잡한 쿼리문은 어노테이션으로 작성해서 사용한다.
    @Query("SELECT SEQ_MEMBER.nextval FROM DUAL")
    long getSequence();

    @Query("SELECT * FROM MEMBER WHERE USER_NM LIKE :word ORDER BY REG_DT DESC")
    List<Member> getMembers(@Param("word") String keyword);
}
