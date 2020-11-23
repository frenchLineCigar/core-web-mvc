package me.frenchline.corewebmvc;

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

    @RequestMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, //요청 Content-Type 헤더와 다르면 415 Unsupported Media Type (HttpMediaTypeNotSupportedException)
            produces = MediaType.TEXT_PLAIN_VALUE //요청에 Accept 헤더가 없으면 이 타입으로 반환, 다르면 406 Not Acceptable (HttpMediaTypeNotAcceptableException)
    )
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
