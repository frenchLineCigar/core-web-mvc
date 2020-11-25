package me.frenchline.corewebmvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@Controller
@RequestMapping("/api/events")
public class EventApi {

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            //바인딩 에러가 발생할때 수행할 로직
            return ResponseEntity.badRequest().build();
        }

        // save event

        return ResponseEntity.ok(event);
//        return ResponseEntity.ok().build();
//        return new ResponseEntity<Event>(event, HttpStatus.CREATED); //원래 201 Created로 상태를 보낼때는 URI 정보도 Location 헤더에 담아서 보내줘야 한다.
    }
}

/**
 * 1. @ResponseBody
 * : 메서드에서 리턴하는 값(객체)을 HttpMessageConverter를 사용해서 응답 본문(Response Body)에 담아준다.
 * 1) 응답 본문에 담을 때 @RequestBody처럼 HttpMessageConverter를 사용한다.
 * 2) return하는 event를 HttpMessageConverter를 사용해 응답 본문 메세지로 변환한다.
 * 3) 이때도 마찬가지로 적절한 타입의 HttpMessageConverter를 선택해서 사용하게 된다.
 * 4) 그때 가장 참고하는 값이 Accept 헤더 값이다.
 *
 * 2. Accept
 * : 요청을 보낼 때 이 요청이 어떠한 응답을 원하는지 Accept 라는 헤더에 정의해줄 수 있다.
 * 1) 가령 웹 브라우저에서 요청을 보낼때 기본적으로 Accept 헤더에 html 또는 xml을 원한다는 값이 들어간다.
 * 2) 기본적으로 Spring Boot는 해당 타입의 응답을 보내주려고 한다.
 * 3) 해당 타입의 응답을 보낼 수 없는 경우에는 기계가 요청을 보냈다고 생각하고 기계한테 유리한 json 응답을 준다.
 * 4) 가령 콘솔에서 cURL을 쓰거나 Postman같은 툴을 사용하는 경우에는 json 등 Accept 값을 명기해서 해당 타입의 응답을 받을 수 있다.
 * 5) 지금 위 코드같은 경우에는 Accept 헤더를 주지 않더라도 html또는 xml로 보여줄 수 없는 상황이므로 (기본적으로 xml로 변환할 수 있는 컨버터가 등록되어 있지 않음)
 * 6) 기본적으로는 json으로 응답을 보내준다.
 *
 * 2. @RestController
 * : 이 애노테이션을 사용한 경우에는 @ResponseBody가 모든 핸들러 메서드에 붙어있는 것으로 간주한다.
 * 1) 따라서 메서드에 @ReponseBody를 생략해도 된다.
 * 2) 소스 코드를 보면 @ResponseBody 애노테이션을 메타 애노테이션으로 만들어진 컴포지트(Composite) 애노테이션임을 확인할 수 있다.
 *
 * 3. `ResponseEntity<T>`
 *
 * 1) @ResponseBody 대신에 응답 타입으로 ResponseEntity 라는 타입을 사용할 수 있다.
 * 2) 이때 컨트롤러의 애노테이션은 @RestController일 필요는 없다. @Controller를 사용해도 된다.
 * 3) 제네릭에 들어가는 타입 T가 응답 본문(Response Body)에 들어가는 객체가 되고
 * 4) 이 경우에는 return ResponseEntity.ok(event); 처럼 ok 메서드(200 status code) 안에 바로 Body에 해당하는 객체를 넣어줄 수 있다.
 * 5) 또는 다양한 스타일의 응답을 줄 수 있다.
 * // ResponseEntity responseEntity = new ResponseEntity(body, headers, status); //생성자에 바디(body), 헤더(headers), 상태(status) 정보를 줄 수 있다.
 *
 * 4. ReponseEntity가 제공하는 팩토리 메서드(Factory Method)
 * 1) 기본적으로 자주 쓰는 응답 같은 경우는 팩토리 메서드(Factory Method)로 ResponseEntity.ok()같은 static한 메소드를 제공해준다.
 * 2) static 메소드를 사용해서 응답을 만들어도 된다.
 * 3) ResponseEntity를 쓰면 좋은 이유
 * - 에러가 있는 경우에는 return ResponseEntity.badRequest().build(); 등으로 에러를 처리해서 보낼 수 있다.
 * - 에러가 없는 경우에는 return ResponseEntity.ok(event); 로 응답을 보낸다.
 * 4) ResponseEntity.badRequest() 사용 시 본문에 메세지를 적어줄 수 있다. (스프링 Rest API 참고)
 * 5) ReponseEntity가 제공하는 팩토리 메서드(Factory Method)들 중 약간 헷갈리는 점은 어떤 것들은 .build()를 해야 완성이 되는 객체들이 있다.
 * - return ResponseEntity.ok().build() : 바디 부분을 안채우고 상태 정보랑 빈 객체만 보낼 때는 .build()
 */