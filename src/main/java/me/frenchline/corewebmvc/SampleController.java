package me.frenchline.corewebmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@Controller
@SessionAttributes("event") //이 클래스 안에서만, 이 이름에 해당하는 모델 애트리뷰트를 세션에 넣어준다.
public class SampleController {

    @GetMapping("/events/form/name") //name만 받는 폼
    public String eventsFormName(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/events/form-name";
        }
        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit") //limit만 받는 폼
    public String eventsFormLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@Validated @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        RedirectAttributes attributes) { //RedirectAttribute 사용

        if (bindingResult.hasErrors()) {
            return "/events/form-limit";
        }

        //save 후 세션 비우기
        sessionStatus.setComplete();

        // RedirectAttributes 사용 (스프링 부트 기본 설정 유지)
        // : 리다이렉트 시 URI 쿼리 파라미터로 전달하고자 하는 데이터를 명시할 수 있는 기능이다.
        // Ex) /events/list?name=spring&limit=10
        attributes.addAttribute("name", event.getName());
        attributes.addAttribute("limit", event.getLimit());

        return "redirect:/events/list";
    }


    /**
     * `@ModelAttribute` 사용해 복합 객체(composite object)로 바인딩 시 주의점
     * -> `@SessionAttributes` 에서 키로 사용한 "이름"과 같이 쓰면 안된다. @SessionAttributes("event")
     * -> 복합 객체로 바인딩 시, 세션에서 일단 해당 속성의 키로 사용한 "이름"으로 값을 찾아보려고 시도하지만, 앞서 세션이 완료되고, 없기 때문에 에러가 난다. -> 해당 에러 확인!
     *
     * 해결: @ModelAttribute의 이름을 @SessionAttributes에 선언한 이름과 다르게 주면 된다. : @ModelAttribute("newEvent")
     */
    @GetMapping("/events/list")
    public String getEvents(@ModelAttribute("newEvent") Event event,
                            Model model,
                            @SessionAttribute LocalDateTime visitTime) {

        //@SessionAttribute 사용해 HTTP 세션에 들어있는 값 참조
        System.out.println("visitTime = " + visitTime);

        //find 조회한 데이터로 가정
        Event spring = new Event();
        spring.setName("spring");
        spring.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(spring);
        eventList.add(event);

        model.addAttribute("eventList", eventList);

        return "/events/list";
    }

}
