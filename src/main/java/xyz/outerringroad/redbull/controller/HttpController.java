package xyz.outerringroad.redbull.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.outerringroad.redbull.bean.dto.UserDTO;
import xyz.outerringroad.redbull.bean.vo.ResponseVO;
import xyz.outerringroad.redbull.dao.UserMapper;
import xyz.outerringroad.redbull.exception.BizException;
import xyz.outerringroad.redbull.utils.JwtTokenUtil;

import java.util.Objects;

@RestController
public class HttpController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/ping")
    public Object ping() {
        return new ResponseVO<>("pong");
    }

    @PostMapping("/login")
    public Object login(@RequestBody UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            throw new BizException("user info is required");
        }

        int count = userMapper.checkUser(userDTO);
        if (count != 1) {
            throw new BizException("user info not found");
        }

        String token = jwtTokenUtil.generateToken(userDTO);
        return new ResponseVO<>(token);
    }

}
