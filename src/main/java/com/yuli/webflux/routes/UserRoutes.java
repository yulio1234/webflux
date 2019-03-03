package com.yuli.webflux.routes;

import com.yuli.webflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRoutes {
    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler){
        return RouterFunctions
                //设置基本路径
                .nest(RequestPredicates.path("/user2")
                //得到所有用户
                ,RouterFunctions.route(RequestPredicates.GET("/"),handler::getAll)
                                //创建用户
                                .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),handler::create)
                //删除用户
                .andRoute(RequestPredicates.DELETE("/{id}"),handler::delete));

    }
}
