package me.frenchline.corewebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@Controller
public class SampleController {

    @GetMapping("/events/id/{id}/name/{name}")
    @ResponseBody
    public Event getEvent(@PathVariable Integer id, @PathVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        return event;
    }
}
