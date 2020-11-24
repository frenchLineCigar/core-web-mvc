package me.frenchline.corewebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@Controller
@SessionAttributes("event") //모델의 애트리뷰트 중 event를 세션에도 자동으로 넣어줌
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        Event newEvent = new Event();
        newEvent.setLimit(50);
        model.addAttribute("event", newEvent); //세션에도 같이 저장됨
        return "/events/form";
    }

    @PostMapping("/events")
    public String createEvent(@Validated @ModelAttribute Event event,
                              BindingResult bindingResult,
                              SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "/events/form";
        }

        //save

        //특정한 폼 처리가 끝났을 때 세션이 만료되도록 처리
        sessionStatus.setComplete();

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {

        //find
        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        model.addAttribute(eventList);

        return "/events/list";
    }

}
