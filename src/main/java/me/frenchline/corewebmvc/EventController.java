package me.frenchline.corewebmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
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
     * `@InitBinder`에 값(value)을 줄 수 있다
     * : 특정 모델 객체에만 Binding 또는 Validator 설정을 적용하고 싶은 경우
     * Ex) @InitBinder("event") //event란 이름의 모델 애트리뷰트를 바인딩 받을때만 적용
     */
    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder) {
        /* 바인딩(Binding) 설정 */
        webDataBinder.setDisallowedFields("id");
        /* Validator 설정 */
        webDataBinder.addValidators(new EventValidator());
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
