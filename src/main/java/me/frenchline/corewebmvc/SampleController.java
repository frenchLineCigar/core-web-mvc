package me.frenchline.corewebmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
//@SessionAttributes("event") //이 클래스 안에서만, 이 이름에 해당하는 모델 애트리뷰트를 세션에 넣어준다.
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
        //model.addAttribute("event", event);
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

        //save 후 세션 비우기
        sessionStatus.setComplete();

        /* Flash Attributes 사용 : redirect 시 객체도 전달할 수 있다 */
        //1. 전달하려는 객체는 HTTP 세션에 들어간다.
        //2. 리다이렉트 된 요청이 처리가 되고 데이터가 사용이 되면 세션에서 제거된다.(일회성)
        //3. 이름 그대로 일회성이라 Flash라는 키워드가 붙어있다.
        //4. 세션을 통해 전달되기 때문에 URI에 데이터가 노출되지 않는다.
        attributes.addFlashAttribute("newEvent", event);

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model, //Flash Attributes 로 넘겨주는 값들은 사실 Model에 바로 들어온다
                            @SessionAttribute LocalDateTime visitTime) {

        //@SessionAttribute 사용해 HTTP 세션에 들어있는 값 참조
        System.out.println("visitTime = " + visitTime);

        //find 조회한 데이터로 가정
        Event spring = new Event();
        spring.setName("spring");
        spring.setLimit(10);

        //Flash Attributes 로 넘겨주는 값들은
        //@ModelAttribute로 받을 수도 있지만, 사실 Model에 바로 들어온다. (Debug 모드로 확인)
        //메서드에 Model 파라미터를 선언만 해놔도 Model에 들어와 있기 때문에
        //따로 선언해서 바인딩 받을 필요 없이 Model에 있는 것을 꺼내 쓰면 된다. 
        Event newEvent = (Event) model.asMap().get("newEvent");
        System.out.println("newEvent = " + newEvent);

        List<Event> eventList = new ArrayList<>();
        eventList.add(spring);
        eventList.add(newEvent);

        System.out.println("eventList = " + eventList);

        model.addAttribute("eventList", eventList);

        return "/events/list";
    }


    /* @SessionAttributes("event") 주석 처리 후, 영향 없는 상태로 Flash Attributes 테스트 */

    @Autowired
    @Qualifier("tempEvent")
    Event tempEvent;

    @GetMapping("/events/empty")
    public String empty(RedirectAttributes attributes, SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        attributes.addFlashAttribute("newEvent", tempEvent);

        return "redirect:/events/list";
    }

}
