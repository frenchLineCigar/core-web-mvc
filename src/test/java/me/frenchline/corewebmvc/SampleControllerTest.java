package me.frenchline.corewebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@RunWith(SpringRunner.class) //@RunWith는 JUnit용 애노테이션, SpringRunner.class는 스프링에서 제공해주는 JUnit용 러너
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(get("/hello")) //perform의 인자값은 MockHttpServletRequest 만들때 사용
                .andDo(print())
                .andExpect(status().isOk()) //Status = 200
                .andExpect(content().string("hello"));

        mockMvc.perform(put("/hello"))
                .andDo(print())
                .andExpect(status().isOk()); //Status = 200

        mockMvc.perform(post("/hello"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed()); //Status = 405
    }
}