package org.koreait.restcontrollers;

import org.koreait.commons.CommonException;
import org.koreait.commons.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("org.koreait.restcontrollers") //restcontroller로 범위 한정해서 공통적인 에러 처리(json)를 하도록
public class CommonRestController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONData<Object>> errorHandler(Exception e){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        if(e instanceof CommonException){ //CommonException의 인스턴스인지 판별
            CommonException commonException = (CommonException)e; //CommonException에서 정의한 예외만 가져오기
            status = commonException.getStatus();
        }
        JSONData<Object> data = new JSONData<>();
        data.setSuccess(false);
        data.setStatus(status); //응답 코드
        data.setMessage(e.getMessage());
        return ResponseEntity.status(data.getStatus()).body(data);
    }
}
