package me.frenchline.corewebmvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Composed Annotation Customized
 * 상황 : 가령 [GET] /hello 라는 요청이 많을 경우, 커스텀 한 애노테이션을 만들어 해당 요청 처리에 사용할 수 있다
 */

@Documented //해당 애노테이션을 사용한 쪽의 코드 문서화(JavaDoc)에 애노테이션의 정보를 표기
@Target(ElementType.METHOD) //해당 애노테이션을 메서드에 사용
@Retention(RetentionPolicy.RUNTIME) //해당 애노테이션 정보를 런타임 시까지 유지
@RequestMapping(method = RequestMethod.GET, value = "/hello")
public @interface GetHelloMapping {
}
