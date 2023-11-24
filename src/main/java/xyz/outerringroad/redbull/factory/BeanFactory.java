package xyz.outerringroad.redbull.factory;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class BeanFactory {

    public static BCryptPasswordEncoder createBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static DaoAuthenticationProvider createDaoAuthenticationProvider() {
        return new DaoAuthenticationProvider();
    }

    public static Date createDate() {
        return new Date();
    }

    public static Date createDate(long currentTimeMillis) {
        return new Date(currentTimeMillis);
    }

    private BeanFactory() {
    }

}
