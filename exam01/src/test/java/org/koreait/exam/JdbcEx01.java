package org.koreait.exam;

import org.junit.jupiter.api.Test;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class JdbcEx01 {
    @Autowired
    private MemberRepository repository;

    @Test
    void test1(){
        List<Member> members = (List<Member>) repository.findAll(); //전체 조회하기
        members.stream().forEach(System.out::println);
    }

    @Test
    void test2(){
        Member member = repository.findById(2L).orElse(null); //기본키로 한 개의 데이터 조회하기
        System.out.println(member);

        //save로 데이터 수정하기
        member.setUserNm("(수정)사용자05");
        member.setModDt(LocalDateTime.now());
        repository.save(member);
    }

    @Test
    void test3(){ //새로 만들어지는 시퀀스 번호 가져오기
        //System.out.println(repository.getSequence());
        //(기존 데이터에 없는) 레코드 추가하기
        Member member = Member.builder()
                .userNo(repository.getSequence())
                .userId("user08")
                .userPw("123456")
                .userNm("사용자08")
                .email("user08@test.org")
                .mobile("01011111111")
                .regDt(LocalDateTime.now())
                .build();

        repository.save(member);
    }

    @Test
    void test4(){
        repository.deleteById(7L); //기본키로 삭제하기
    }

    @Test
    void test5(){ //기본키로 특정 레코드 조회하기
        Member member = repository.findByUserId("user07");
        System.out.println(member);
    }

    @Test
    void test6(){
        LocalDateTime edate = LocalDateTime.now();
        LocalDateTime sdate = edate.minusDays(7);

        //Pageable pageable = PageRequest.of(0,3); //0페이지에 3개의 레코드를 가져온다.
        Pageable pageable = PageRequest.of(0,3, Sort.by(desc("regDt"),asc("userId")));
        List<Member> members = repository.findByRegDtBetween(sdate,edate,pageable);
        members.stream().forEach(System.out::println);
    }

    @Test
    void test7(){
        List<Member> members = repository.findByUserNmContainingOrderByRegDtDesc("용");
        members.stream().forEach(System.out::println);
    }

    @Test
    void test8(){
        List<Member> members = repository.getMembers("%용%");
        members.stream().forEach(System.out::println);
    }
}
