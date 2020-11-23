package me.frenchline.corewebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE, //받는 데이터는 모두 JSON 타입
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class EventUpdateController {

    /**
     * 5. PUT /events/1 CONTENT-TYPE: application/json ACCEPT: application/json,
     * PUT /events/2 CONTENT-TYPE: application/json ACCEPT: application/json, ...
     */
    @PutMapping("/events")
    @ResponseBody
    public String updateEvent() {
        return "event";
    }

    /**
     * 3. POST /events CONTENT-TYPE: application/json ACCEPT: application/json
     */
    @PostMapping("/events")
    @ResponseBody
    public String createEvent() {
        return "event";
    }

}
