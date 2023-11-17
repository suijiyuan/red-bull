package xyz.outerringroad.redbull.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.outerringroad.redbull.bean.dto.UserDTO;

@Mapper
public interface UserMapper {

    int checkUser(UserDTO userDTO);
    int createUser(UserDTO userDTO);
    int deleteUser(UserDTO userDTO);

}
