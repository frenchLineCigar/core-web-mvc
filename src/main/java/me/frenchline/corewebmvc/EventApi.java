package me.frenchline.corewebmvc;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Event createEvent(@RequestBody Event event) { //요청 본문(Request Body)에 들어오는 데이터를 Event 객체로 변환(Conversion)해서 바인딩 받는다.
        // save event
        return event;
    }
}

/**
 * 1. (@RequestBody Event event)
 * 요청 본문(Request Body)에 들어오는 데이터를 Event 객체로 변환(Conversion)해서 바인딩 받는다.
 * 변환할 때 사용하는 것이 HttpMessageConverter이다.
 * 1) HttpMessageConverter는 기본으로 여러 개가 등록이 되있다
 * : WebMvcConfigurationSupport.addDefaultHttpMessageConverters 설정 확인
 * 이 메세지 컨버터들을 핸들러 어댑터(HandlerAdapter)가 메서드의 아규먼트를 Resolving할 때 사용한다.
 * HandlerAdapter에 등록되어있는 여러 HttpMessageConverter 중에 현재 요청에 들어있는 본문(RequestBody)을 변환할 수 있는 컨버터를 선택해서 사용한다
 * 가령 요청 본문(Request Body)이 JSON 타입으로 들어왔다면 요청 헤더(Response Headers)에 Content-Type이 application/json으로 들어있을 것이다.
 * 이렇게 요청 헤더 정보에 "내가 보내는 이 본문의 데이터는 JSON이다." 라고 Content-Type을 알려준다.
 * 그러면 컨텐츠 타입을 본 핸들러 어댑터는 "JSON을 컨버팅할 수 있는 컨버터를 써야겠다." 해서 등록돼있는 컨버터들 중에서 JSON을 변환할 수 있는
 * HttpMessageConverter를 선택해서 JSON 문자열을 Event 객체로 변환해준다.
 *
 * 스프링 부트를 사용하면
 * org.springframework.boot:spring-boot-starter-web 을 넣는 순간
 * Jackson 2 ObjectMapper가 기본으로 디펜던시에 들어가 있다.
 * 그래서 JSON 컨버터가 메세지 컨버터에 자동으로 등록이 되는 것이다.
 * ObjectMapper를 사용하면 객체를 JSON 문자열로 바꿀 수 있고, JSON 문자열을 객체로 변환할 수도 있다.
 * 스프링 부트 내부에서 자동설정(JacksonAutoConfiguration)을 통해 이미 이 객체를 빈으로 등록해주기 때문에 우리는 그저 가져다가(주입받아) 쓰기만 하면 된다.
 * : JacksonAutoConfiguration.JacksonObjectMapperConfiguration#jacksonObjectMapper
 *
 * org.springframework.boot:spring-boot-starter-json
 *  com.fasterxml.jackson.core:jackson-databind
 *  com.fasterxml.jackson.datatype:jackson-datatype-jdk8
 *  com.fasterxml.jackson.datatype:jackson-datatype-jsr310
 *  com.fasterxml.jackson.module:jackson-module-parameter-names
 *
 */
