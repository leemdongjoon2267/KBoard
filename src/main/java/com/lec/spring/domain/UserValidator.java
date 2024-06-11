package com.lec.spring.domain;

import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component // bean 으로 등록, service 주입 받기 위해서 사용
public class UserValidator implements Validator {

    @Autowired
    UserService userService; // service 를 주입 받으려면 bean 객체만 가능

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
        // true 를 리턴해야지 validator 실행
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;

        // username 필수, 중복된 username 여부
        String username = user.getUsername();
        if(username == null || username.trim().isEmpty()){
            errors.rejectValue("username", "username 은 필수입니다");
        }else if (userService.isExist(username)){
            errors.rejectValue("username", "이미 존재하는 아이디(username) 입니다");

        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name 은 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password 는 필수입니다");


        // email
        // 입력이 되어 있으면 정규표현식 패턴 체크


        if(!user.getPassword().equals(user.getRe_password())){
            errors.rejectValue("re_password", "비밀번호와 비밀번호확인 입력값은 같아야 합니다");
        }


        // 입력 password, re_password 가 동일한지 비교


    }
}
