package org.koreait.commons;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class JSONData<T> { //통일성 있는 예상 가능한 필드
    private HttpStatus status = HttpStatus.OK; //응답코드와 상태코드 고정
    private boolean success = true; //true이면 요청 성공

    @NonNull
    private T data;
    private String message; //에러 메세지
}
