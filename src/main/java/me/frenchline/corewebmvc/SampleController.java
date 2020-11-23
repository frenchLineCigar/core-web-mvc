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

    @RequestMapping(value = "/hello", headers = "!" + HttpHeaders.FROM) //FROM 헤더가 들어있는 요청만 처리 (주의: CONTENT_TYPE, ACCEPT은 조건 적용이 X)
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
