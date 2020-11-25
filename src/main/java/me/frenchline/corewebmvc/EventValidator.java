package me.frenchline.corewebmvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/* Validator 설정 */
// Event 객체의 name 에 특정한 값이 들어오지 않도록 설정 가능
// Spring MVC가 지원하는 Validator 인터페이스를 구현하는 클래스

public class EventValidator implements Validator {

    /* supports 메서드: Validation 타겟 지정 (어떤 도메인 클래스(타입)에 대해 지원하는가를 판단한다) */
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    /* validate 메서드: target에 실제 검증을 해야하는 객체가 들어오고, 메서드 바디에 검증 로직을 작성 */
    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        if (event.getName().equalsIgnoreCase("aaa")) { //aaa 라는 특정한 값이 들어있으면
            errors.rejectValue("name", "wrongValue", "the value is not allowed"); //값을 거부한다
        }
    }
}
