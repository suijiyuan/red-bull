package xyz.outerringroad.redbull.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.outerringroad.redbull.bean.bo.UserBO;
import xyz.outerringroad.redbull.bean.po.UserPO;

@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserBO convertUserPOToUserBO(UserPO userPO);

}
