package org.koreait.controllers;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final MemberRepository repository; //service에서 구현하는 것이 좋다.

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/info/{userId}") //pathVariable
    @ResponseBody
    public Member info(@PathVariable String userId){
        Member member = repository.findByUserId(userId);
        return member; // 일반 컨트롤러에서는 문자열만 반환해야 하지만 @ResponseBody가 있으면 Rest형태로 응답 가능
    }
}
