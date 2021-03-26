package me.js.async.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncThreadConfiguration extends AsyncConfigurerSupport {

    // TODO: 설정값은 다시 리서칭이 필요
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); // 미리 만들어두는 스레드 개수
        executor.setMaxPoolSize(10); // 미리 만들어둔 스레드가 꽉차면 max만큼 생성가능
        executor.setQueueCapacity(500); // max가 꽉차면 큐에 등록하여 순차적 수행, 큐도 꽉차면 예외발생
        executor.setThreadNamePrefix("spring-asyncThread-"); // 스프링이 생성하는 쓰레드의 접두사를 지정
        executor.initialize();
        return executor;
    }

}
