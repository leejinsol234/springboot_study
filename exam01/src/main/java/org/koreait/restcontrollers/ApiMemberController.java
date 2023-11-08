package org.koreait.restcontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class ApiMemberController {

    private final MemberRepository repository;

    @GetMapping("/{userId}")
    public Member info(@PathVariable String userId){ //경로 변수
        Member member = repository.findByUserId(userId); //service에서 구현하는 것이 바람직하다.
        return member;
    }

    @GetMapping("/list")
    public List<Member> list() {
        List<Member> members = (List<Member>) repository.findAll();
        return members;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello"; //문자열 그 자체로 출력됨.
    }
    
    @GetMapping("/test")
    public void test(){
        //반환값이 없으면 내부적인 처리만 한다.
        log.info("테스트");
    }

    public void login(RequestLogin form){
        log.info(form.toString());
    }
}
