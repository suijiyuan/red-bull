package xyz.outerringroad.redbull.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.outerringroad.redbull.bean.dto.UserDTO;
import xyz.outerringroad.redbull.bean.vo.ResponseVO;
import xyz.outerringroad.redbull.service.UserService;
import xyz.outerringroad.redbull.service.TokenService;

@RestController
public class HttpController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @GetMapping("/ping")
    public Object ping() {
        return new ResponseVO<>("pong");
    }

    @PostMapping("/login")
    public Object login(@RequestBody UserDTO userDTO) {
        userService.checkUser(userDTO);
        return new ResponseVO<>(tokenService.generateToken(userDTO));
    }

    @GetMapping("/validate")
    public Object validate(String token) {
        return new ResponseVO<>(tokenService.isTokenValid(token));
    }

}
