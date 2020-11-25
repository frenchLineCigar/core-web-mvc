package me.frenchline.corewebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

    /**
     * DataBinder : @InitBinder
     * 데이터들을 바인딩할 때 사용하는 데이터 바인드를 커스터마이징 할 수 있다
     * 특정 컨트롤러 안에서 사용하는 데이터 바인드 설정 : @InitBinder 애노테이션을 붙인 메서드를 정의해서 바인딩 설정을 변경할 수 있다
     * - 리턴 타입은 반드시 void 이어야 하고, 메서드명 제약은 없다.
     * - WebDataBinder 아규먼트는 반드시 있어야 한다
     * setDisallowedFields : 받고 싶지 않은 필드값을 걸러낼 수 있다. (블랙 리스트)
     * setAllowedFields : 받고 싶은 필드값을 명기한다. (화이트 리스트)
     * - id는 이벤트를 저장할 때 생성하고 싶기 때문에 id값을 폼이나 쿼리 패스나 쿼리 파라미터 등으로 받아오고 싶지 않다면
     * - id를 폼에서 보내더라도 걸러내고 바인딩 하지 않는다
     */
    /* 모든 요청 전에 호출 */
    @InitBinder
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

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
