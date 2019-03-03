package com.yuli.webflux.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class TestController {
    @GetMapping("get1")
    public String get1(){
        log.info("get1 start");

        String string = createString();
        log.info("get1 stop");
        return string;
    }

    private String createString() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some string";
    }

    @GetMapping("/get2")
    public Mono<String> get2(){
        log.info("get2 star");
        Mono<String> stringMono = Mono.fromSupplier(() -> createString());
        log.info("get2 end");
        return stringMono;
    }

    /**
     * 使用http5的服务器推送事件sse
     *
     * @return
     */
    @GetMapping(value = "/get3",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux(){
       return Flux.fromStream(IntStream.range(1,5).mapToObj(i->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data --" +i;
        }));
    }
}
