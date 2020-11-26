package me.frenchline.corewebmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventApiTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createEventStatus201Created() throws Exception {
        Event event = new Event();
        event.setName("frenchline");
        event.setLimit(20); //마이너스 값

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON_UTF8) //Accept Header 값 정의
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated()) //201 Created
                .andExpect(jsonPath("$.name").value("frenchline"))
                .andExpect(jsonPath("$.limit").value(20))
        ;
    }

    @Test
    public void createEvent() throws Exception {
        Event event = new Event();
        event.setName("frenchline");
        event.setLimit(-20); //마이너스 값

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON_UTF8) //Accept Header 값 정의
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest()) //400 Bad Request
        ;
    }

}