package com.example.gdkim.web;

import com.example.gdkim.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //예전방식 @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto") // @RequestParam 외부에서 API로 넘김 파리미터를 가져오는 어노테이션
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    }


}
