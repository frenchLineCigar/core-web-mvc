package me.frenchline.corewebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/api/events")
public class EventApi {

    @PostMapping
    public Event createEvent(HttpEntity<Event> request) { //HttpEntity<본문 타입>로 감싸면 요청 본문 뿐만 아니라, 요청 헤더(Request Headers) 정보를 추가적으로 받을 수 있다
        // save event

        //@RequestBody를 사용할 때와 다르게 헤더 정보(Response Headers)에도 접근할 수 있다
        MediaType contentType = request.getHeaders().getContentType();
        System.out.println("contentType = " + contentType);
        System.out.println("request = " + request);
        System.out.println("request.getHeaders() = " + request.getHeaders());
        System.out.println("request.getBody() = " + request.getBody());
        System.out.println("request.getClass() = " + request.getClass());

        return request.getBody();
    }
}


