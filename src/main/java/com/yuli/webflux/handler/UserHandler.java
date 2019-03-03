package com.yuli.webflux.handler;

import com.yuli.webflux.domain.User;
import com.yuli.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 使用handler风格编写webflux代码
 */
@Component
public class UserHandler {
    @Autowired
    private UserRepository repository;
    public Mono<ServerResponse> getAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repository.findAll(), User.class);
    }
    public Mono<ServerResponse> create(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repository.saveAll(userMono),User.class);
    }
    public Mono<ServerResponse>  delete(ServerRequest request){
        String id = request.pathVariable("id");
        return repository.findById(id).flatMap(user->
            //没有body用build
            repository.delete(user).then(ServerResponse.ok().build())
        ).switchIfEmpty(ServerResponse.notFound().build());
    }
}
