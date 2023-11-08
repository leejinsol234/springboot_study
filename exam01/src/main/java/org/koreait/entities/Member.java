package org.koreait.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id //userNo가 기본키임을 알려주는 어노테이션
    private long userNo; // sql에서 user_no와 동일하게 인식한다.

    private String userId;

    @JsonIgnore //JSON 문자열 변환 배제
    private String userPw;

    private String userNm;
    private String email;
    private String mobile;

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm") //날짜 형식화
    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
