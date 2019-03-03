package com.yuli.webflux.Controller;

import com.yuli.webflux.domain.User;
import com.yuli.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 使用原始风格编写weblux代码
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get1")
    public Flux<User> getAll(){
        return userRepository.findAll();
    }
    @GetMapping(value = "get2",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllStream(){
        return userRepository.findAll();
    }
    @PostMapping("/save")
    public Mono<User> save(@RequestBody User user){
        return userRepository.save(user);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        //当要操作数据并返回一个mono使用flatMap处理数据
        //当不操作数据，只转换数据使用map
        return userRepository.findById(id).flatMap(user -> userRepository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                //如果为空就返回没有找到
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> put(@PathVariable String id,@RequestBody User user){
        return userRepository.findById(id).flatMap(u->{
            u.setPassword(user.getPassword());
            u.setUsername(user.getUsername());
            return userRepository.save(u);
        }).map(u->new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> get(@PathVariable String id){
        return userRepository.findById(id).map(user -> new ResponseEntity<>(user,HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
