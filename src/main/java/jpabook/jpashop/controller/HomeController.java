package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j // slf4j 로그 어노테이션
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }
}
