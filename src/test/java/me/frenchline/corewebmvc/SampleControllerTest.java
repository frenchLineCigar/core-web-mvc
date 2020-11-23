package me.frenchline.corewebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    /**
     * 1. GET /events
     */
    @Test
    public void getEvents() throws Exception {
        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 2. GET /events/1, GET /events/2, GET /events/3, ...
     */
    @Test
    public void getEventsWithId() throws Exception {
        mockMvc.perform(get("/events/1"))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(get("/events/2"))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(get("/events/3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 3. POST /events CONTENT-TYPE: application/json ACCEPT: application/json
     */
    @Test
    public void createEvent() throws Exception {
        mockMvc.perform(
                post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    /**
     * 4. DELETE /events/1, DELETE /events/2, DELETE /events/3, ...
     */
    @Test
    public void deleteEvent() throws Exception {
        mockMvc.perform(delete("/events/1"))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(delete("/events/2"))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(delete("/events/3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 5. PUT /events/1 CONTENT-TYPE: application/json ACCEPT: application/json,
     * PUT /events/2 CONTENT-TYPE: application/json ACCEPT: application/json, ...
     */
    @Test
    public void updateEvent() throws Exception {
        mockMvc.perform(
                put("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}