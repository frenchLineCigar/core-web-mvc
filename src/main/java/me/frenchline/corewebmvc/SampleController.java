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

public class SampleController {

    /* URI 특정 패턴으로 맵핑 : 글자 하나 당 물음표 하나로 처리 */
    @RequestMapping("/hello/?")
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
