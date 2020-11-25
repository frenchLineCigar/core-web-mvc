package me.frenchline.corewebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/api/events")
public class EventApi {

    @PostMapping
    public Event createEvent(@RequestBody @Valid Event event) {
        // save event

        return event;
    }
}

/**
 * 1. BindingResult bindingResult
 * : BindingResult 타입의 아규먼트를 사용하지 않으면 바인딩 에러가 발생할 때 기본적으로 익셉션이 발생하면서 400 Bad Request 상태 코드로 응답이 나가게 된다.
 *
 * 그런데 우리가 코드에서 조금 더 처리를 하고 싶다면 BidingResult를 선언해 좀 더 커스텀한 처리를 할 수 있다.
 * 그러면 바인딩 에러가 발생하더라도 400 에러가 발생하진 않는다. 바인딩 시 발생한 에러정보를 bindingResult 변수에 담아주고 끝이다.
 * 그 변수에 담긴 내용을 가지고 우리가 처리하면 된다.
 * 에러가 있는 경우에는 400에러를 보내긴 보낼 건데 그 에러 본문에 원하는 응답을 넣거나 또는 상태 값을 좀 더 구체적으로 바꿔주는 식으로
 * 원하는 처리를 할 수 있다.*
 */

