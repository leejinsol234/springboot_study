package org.koreait.restcontrollers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.koreait.commons.BadRequestException;
import org.koreait.commons.CommonException;
import org.koreait.commons.JSONData;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class ApiMemberController {

    private final MemberRepository repository;

    @GetMapping("/{userId}")
    public ResponseEntity<JSONData<Member>> info(@PathVariable String userId){ //경로 변수
        Member member = repository.findByUserId(userId); //service에서 구현하는 것이 바람직하다.
        //예상 가능한 형식으로 맞춰주기

        JSONData<Member> data= new JSONData<>(member);
        boolean isError = true;
        if(isError){
            throw new BadRequestException("에러 발생");
            //throw new RuntimeException("에러 발생!"); //500
        }

        return ResponseEntity.status(data.getStatus()).body(data);
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

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid RequestLogin form, Errors errors){ //@RequestBody : Body 데이터 형식을 JSON으로 인식
        //커맨드 객체에서 검증 후 발생한 오류는 errors에 담아준다.

        if(errors.hasErrors()){
            String message = errors.getAllErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.joining(","));

            throw new RuntimeException(message);
        }
        log.info(form.toString());
        /*
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("TestHeader","Test")
                .build(); //출력 데이터가 없을 때는 build()로 작성
        */

        return ResponseEntity.ok().build();
    }

}
