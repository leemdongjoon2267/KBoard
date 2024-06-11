package com.lec.spring.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        boolean result = Post.class.isAssignableFrom(clazz);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Post post = (Post) target;
        System.out.println("validate() 호출" + post);

//        String user = post.getUser(); // 작성자 정보 가져옴
//        if(user == null || user.trim().isEmpty()){
//            errors.rejectValue("user", "작성자는 필수입니다.");
//        }

//        String subject = post.getSubject();
//        if(subject == null || user.trim().isEmpty()){
//            errors.rejectValue("subject", "글 제목은 필수입니다.");
//        }

        // ValidationUtils 사용
        // 단순히 빈(empty) 폼 데이터를 처리할때는 아래 와 같이 사용 가능
        // 두번째 매개변수 "subject" 은 반드시 target 클래스의 필드명 이어야 함
        // 게다가 Errors 에 등록될때도 동일한 field 명으로 등록된다.

        // 같은 구문
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "글 제목은 필수입니다.");

    }
}
