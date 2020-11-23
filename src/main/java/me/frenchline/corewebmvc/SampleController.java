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
@RequestMapping("/hello") //클래스 레벨 URI
public class SampleController {

    @RequestMapping("/**") //메서드 레벨 URI
    @ResponseBody
    public String hello() {
        return "hello";

    }

}
