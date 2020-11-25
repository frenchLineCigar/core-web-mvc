package me.frenchline.corewebmvc;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Locale.*;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@Controller
@SessionAttributes("event")
public class EventController {

    /**
     * @ModelAttribute
     * 1. Model이나 ModelMap 이란 메서드 아규먼트를 사용해서 모델 정보를 담아줄 수 있고, 그 모델 정보를 뷰에서 참조해서 렌더링할 수 있다.
     * 2. 그런데 메서드의 아규먼트로 들어왔을 때 모델 정보 속성을 추가하는 것이 아닌, 모든 핸들러에서 필요한 어떤 공통적인 정보들의 경우라면 가령 카테고리 정보가 있다.
     * 3. 현재 컨트롤러 안에서 공통적으로 참고해야하는 모델 정보가 있는 경우라면 매번 모든 핸들러에서 그 모델 정보를 아규먼트 받아서 일일히 넣어주기에는 불편하다.
     * 4. 그래서 다음과 같이 사용할 수 있다
     * 1) @ModelAttribute를 메서드 위에 따로 만들어 붙이고 어떤 종류의 이벤트인지 알아보기 쉽게 메서드 이름을 정의한다.
     * 2) 카테고리 정보를 매번 모든 요청에 다 전달하기 위해 모델 정보에 넣어주는 코드를 작성하자.
     * 3) 카테고리 정보를 기본적으로 모든 뷰에서 다 참조를 하는 경우 다음과 같이 모델 어트리뷰트를 정의하는 메소드를 다음처럼 정의해놓으면 된다.
     * 4) 이 메서드는 다른 핸들러가 실행하지 않아도 미리 실행을 해서 모델 정보에 넣어주므로 코드 중복을 제거할 수 있는 방법 중 하나기도 하다.
     */
    /* 방법 1 */
    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", List.of("study", "seminar", "hobby", "social"));
    }

    /* 방법 2 : 리턴을 해도 되고, 애트리뷰트의 이름을 지정할 수 있다 */
    @ModelAttribute("countries")
    public List<Locale> countries(Model model) {
        return List.of(KOREA, US, FRANCE, ITALY, UK);
    }

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }
    /**
     * 인터페이스와 클래스의 차이일 뿐 기능은 같다. 아무거나 써도 된다
     * Model - 인터페이스
     * ModelMap - 클래스
     */

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
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
