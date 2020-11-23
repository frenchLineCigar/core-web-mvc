package me.frenchline.corewebmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

    // "/hello?name=frenchline

    @RequestMapping(value = "/hello", params = "name=frenchline") //정확하게 파라미터 값이 일치할 경우 요청을 처리한다
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
