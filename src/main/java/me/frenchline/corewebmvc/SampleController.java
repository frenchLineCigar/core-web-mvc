package me.frenchline.corewebmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE) //조회 시 모든 응답은 JSON 타입
public class SampleController {


    /**
     * 1. GET /events
     */
    @GetMapping("/events")
    @ResponseBody
    public String getEvents() {
        return "events";
    }

    /**
     * 2. GET /events/1, GET /events/2, GET /events/3, ...
     */
    @GetMapping("/events/{id}")
    @ResponseBody
    public String getAnEvent(@PathVariable Long id) {
        return "event";
    }

    /**
     * 4. DELETE /events/1, DELETE /events/2, DELETE /events/3, ...
     */
    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String removeAnEvent(@PathVariable Long id) {
        return "event";
    }

    @GetHelloMapping
    @ResponseBody
    public String hello() {
        return "hello";
    }


}
