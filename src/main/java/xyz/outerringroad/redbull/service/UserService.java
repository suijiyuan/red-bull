package xyz.outerringroad.redbull.service;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.outerringroad.redbull.bean.bo.UserBO;
import xyz.outerringroad.redbull.bean.dto.UserDTO;
import xyz.outerringroad.redbull.bean.po.UserPO;
import xyz.outerringroad.redbull.convert.UserConverter;
import xyz.outerringroad.redbull.dao.UserMapper;
import xyz.outerringroad.redbull.exception.BizException;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    public void checkUser(UserDTO userDTO) {
        this.checkUserDTO(userDTO);
        UserPO userPO = userMapper.getUser(userDTO);
        if (Objects.isNull(userPO)) {
            throw BizException.createBizException("user info not found");
        }

        if (!passwordEncoder.matches(userDTO.getPassword(), userPO.getPassword())) {
            throw BizException.createBizException("username or password wrong");
        }
    }

    public UserDetailsService userDetailsService() {
        return username -> {
            UserDTO userDTO = UserDTO.createUserDTO(username);
            UserPO userPO = userMapper.getUser(userDTO);
            UserBO userBO = UserConverter.INSTANCE.convertUserPOToUserBO(userPO);
            return Optional.ofNullable(userBO).orElseThrow(() -> BizException.createBizException("user not found"));
        };
    }

    private void checkUserDTO(UserDTO userDTO) {
        if (Objects.isNull(userDTO) || Objects.isNull(userDTO.getUsername()) || Objects.isNull(userDTO.getPassword())) {
            throw BizException.createBizException("user info is required");
        }
    }

}
