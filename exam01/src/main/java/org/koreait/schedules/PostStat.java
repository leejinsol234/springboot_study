package org.koreait.schedules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j //log 변수 추가(log.info, log.error, log.warn)
@Service
public class PostStat {
    // @Scheduled(cron="0 0 1 * * *") 새벽 1시마다
    //@Scheduled(cron = "*/5 * * * * *") //5초마다
    //@Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS) //5초 마다 (고정 간격으로)실행. 실행 완료와 상관없이 정해진 간격마다 실행됨.
    //@Scheduled(initialDelay = 5, timeUnit = TimeUnit.SECONDS) //작업 시작 전 5초 대기
    public void process(){
        //System.out.println("게시글 통계 수행");
        log.info("게시글 통계 작업 수행");
    }
}
