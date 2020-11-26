package me.frenchline.corewebmvc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.Locale;

import static java.util.Locale.FRANCE;
import static java.util.Locale.ITALY;
import static java.util.Locale.KOREA;
import static java.util.Locale.UK;
import static java.util.Locale.US;

/**
 * 전역 컨트롤러 : @ControllerAdvice, @RestControllerAdvice
 *
 * 전역적으로 적용이 되는 컨트롤러로 여러 컨트롤러에 간섭하는 컨트롤러가 된다
 * 특정 컨트롤러 내에서만 사용하지 않고, 여러 컨트롤러에 걸쳐서 사용
 *
 * 1. 적용 범위 지정이 가능 (애노테이션의 속성 들은 범위를 지정하는 필드들이다)
 * 1) basePackage, basePackageClasses : 특정 패키지 이하의 컨트롤러에만 적용
 * 2) assignableTypes : 특정 클래스 타입의 컨트롤러에만 적용
 *  Ex) @ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
 * 3) annotations : 특정 애노테이션을 가지고 있는 컨트롤러에만 적용
 *  Ex) @ControllerAdvice(annotations = {Controller.class, RestController.class})
 *
 * 2. @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * : `@RestControllerAdvice`는 @ControllerAdvice와 같은데 소스코드 열어보면 알겠지만 @ResponseBody가 추가되있을 뿐이다.
 */
@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
public class BaseController {

    /* 예외 핸들러 정의 */
    /* - 컨트롤러 내 특정 예외가 발생했을때 그 에러를 처리할 수 있는 요청 처리 핸들러 */
    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException ex, Model model, HandlerMethod method) {
        System.out.println("Exception Type : " + ex.getClass());
        System.out.println("Handler Method : " + method.getMethod());

        String message = (ex.getMessage() != null) ? ex.getMessage() : "runtime error";
        model.addAttribute("message", message);
        return "error";
    }

    /* 예외 핸들러 정의 (REST API) */
    @ExceptionHandler({EventApiException.class})
    public ResponseEntity errorHandler(EventApiException ex, HandlerMethod method) {
        System.out.println("className : " + method.getMethod().getDeclaringClass().getSimpleName());
        String message = (ex.getMessage() != null) ? ex.getMessage() : null;
        return ResponseEntity.badRequest().body("can't create event as ... " + message); //에러의 이유를 표시할 수 있도록 적절한 메세지
    }

    /* 데이터 바인더 설정 */
    /* - 컨트롤러 내 핸들러들이 특정 값을 바인딩할때 Validator를 추가하거나 커스텀한 바인딩 로직을 추가 */
    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder) {
        /* 바인딩(Binding) 설정 */
        webDataBinder.setDisallowedFields("id");
        /* Validator 설정 */
        webDataBinder.addValidators(new EventValidator());
    }

    /* 모델 어트리뷰트 정의 */
    /* - 컨트롤러 내 모든 핸들러들이 공통적으로 사용할 모델 객체를 정의 */
    /* - 모든 핸들러들의 모델에 담기게 된다 */
    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", List.of("study", "seminar", "hobby", "social"));
    }

    @ModelAttribute("locales")
    public List<Locale> localeList(Model model) {
        return List.of(KOREA, US, FRANCE, ITALY, UK);
    }

}
