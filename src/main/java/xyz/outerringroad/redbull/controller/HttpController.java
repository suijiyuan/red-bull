package xyz.outerringroad.redbull.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.outerringroad.redbull.bean.vo.ResponseVO;
import xyz.outerringroad.redbull.constant.CodeEnum;

@RestController
public class HttpController {

    @GetMapping("/ping")
    public Object ping() {
        return new ResponseVO<>(CodeEnum.SUCCESS, "pong");
    }

}
