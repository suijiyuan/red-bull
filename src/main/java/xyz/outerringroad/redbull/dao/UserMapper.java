package xyz.outerringroad.redbull.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.outerringroad.redbull.bean.dto.UserDTO;
import xyz.outerringroad.redbull.bean.po.UserPO;

@Mapper
public interface UserMapper {

    UserPO getUser(UserDTO userDTO);
    Integer createUser(UserDTO userDTO);
    Integer deleteUser(UserDTO userDTO);

}
