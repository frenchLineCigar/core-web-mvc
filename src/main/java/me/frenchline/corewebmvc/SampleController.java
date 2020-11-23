package me.frenchline.corewebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@Controller
@RequestMapping("/hello")
public class SampleController {

    /* 확장자 맵핑은 스프링 부트에서 기본적으로 지원하지 않음 */
    @RequestMapping({"/frenchline"})
    @ResponseBody
    public String helloFrenchline() {
        return "hello frenchline";

    }

}
