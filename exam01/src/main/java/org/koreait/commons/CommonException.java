package org.koreait.commons;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException{

    //공통적인 예외 클래스 만들기
    private HttpStatus status;

    public CommonException(String message){  //에러 기본값
        super(message);
        status = HttpStatus.INTERNAL_SERVER_ERROR; // 500 error
    }

    public CommonException(String message,HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){ //응답 처리 시 사용할 getter
        return status;
    }
}
