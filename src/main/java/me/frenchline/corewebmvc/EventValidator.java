package me.frenchline.corewebmvc;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/* Validator 설정 */
// Bean 으로 등록하면 Validator 인터페이스를 굳이 구현할 필요도 없다
@Component //Bean 등록
public class EventValidator {

    /* Event 객체의 name 에 특정한 값이 들어오지 않도록 설정 */
    public void validate(Event event, Errors errors) { //Errors는 BidingResult의 상위 타입
        if (event.getName().equalsIgnoreCase("aaa")) { //aaa 라는 특정한 값이 들어있으면
            errors.rejectValue("name", "wrongValue", "the value is not allowed"); //값을 거부한다
        }
    }
}
