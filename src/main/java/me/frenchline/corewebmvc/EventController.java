package me.frenchline.corewebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Locale.FRANCE;
import static java.util.Locale.ITALY;
import static java.util.Locale.KOREA;
import static java.util.Locale.UK;
import static java.util.Locale.US;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@Controller
@SessionAttributes("event")
public class EventController {

    /* 예외 핸들러 정의 */
    /* - 컨트롤러 내 특정 예외가 발생했을때 그 에러를 처리할 수 있는 요청 처리 핸들러 */
    /* 1. 처리하고 싶은 예외를 메서드 아규먼트로 선언하면 된다 */
    /* - 해당 예외가 발생하면 정의한 이 메서드의 아규먼트로 해당 예외가 들어오게 된다 */
    /* - 해당 예외가 발생하면 특정한 메세지와 함께 특정한 에러 페이지로 이동하도록 처리함 */
    /* 2. 가장 구체적인 타입의 예외로 맵핑 된다 */
    /* - EventException 의 상위 타입을 RuntimeException 로 정의했음 */
    /* - 자식 타입의 예외와 부모 타입의 예외 핸들러를 각각 정의했을 경우, 자식 예외 발생 시 부모 타입의 예외 핸들러는 호출되지 않는다 */
    /* 3. 여러 예외를 같은 예외 핸들러 내에서 처리하고 싶은 경우, @ExceptionHandler 애노테이션에 값을 정의한다 */
    /* - 이때 상위 타입으로 아규먼트를 정의해야 그 하위 타입의 예외까지 다 담을 수 있다 */
    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException ex, Model model, HandlerMethod method) {
        System.out.println("Exception Type : " + ex.getClass());
        System.out.println("Handler Method : " + method.getMethod());

        String message = (ex.getMessage() != null) ? ex.getMessage() : "runtime error";
        model.addAttribute("message", message);
        return "error";
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

    @GetMapping("/events/model-attributes")
    @ModelAttribute("newAttr")
    public Event eventsFormName() {
        return new Event();
    }

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        throw new EventException("event error"); //예외를 던지면 위의 @ExceptionHandler로 정의한 해당 예외 핸들러가 동작
//        throw new RuntimeException("runtime error");
//
//        model.addAttribute("event", new Event());
//        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //스프링이 기본으로 제공해주는 바인딩 에러와 Validation 에러를 검증
            return "/events/form-name";
        }
        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@Validated @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/events/form-limit";
        }
        sessionStatus.setComplete();
        attributes.addFlashAttribute("newEvent", event);
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model,
                            @SessionAttribute LocalDateTime visitTime) {
        System.out.println("visitTime = " + visitTime);

        //find
        Event spring = new Event();
        spring.setName("spring");
        spring.setLimit(10);

        Event newEvent = (Event) model.asMap().get("newEvent");

        List<Event> eventList = new ArrayList<>();
        eventList.add(spring);
        eventList.add(newEvent);

        model.addAttribute("eventList", eventList);
        return "/events/list";
    }

}
