package me.frenchline.corewebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Method;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    public void getEventOldPattern() throws Exception {
        mockMvc.perform(get("/events/id/1/name/frenchline"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("frenchline"))
        ;
    }

    @Test
    public void getEvent() throws Exception {
        mockMvc.perform(get("/events/1;name=frenchline"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("frenchline"))
        ;
    }

    @Test
    public void findPet() throws Exception {
        mockMvc.perform(get("/pets/42;q=11;r=22"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(SampleController.class))
                .andExpect(handler().methodName("findPet"))
        ;
    }

    @Test
    public void findUser() throws Exception {
        mockMvc.perform(get("/users/42"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(SampleController.class))
                .andExpect(handler().methodName("findUser"))
        ;
    }

    @Test
    public void findPet2() throws Exception {
        mockMvc.perform(get("/owners/42;q=11/pets/21;q=22"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(SampleController.class))
                .andExpect(handler().methodName("findPet"))
        ;
    }

    @Test
    public void findPet_MultiValueMap() throws Exception {
        mockMvc.perform(get("/products/42;q=11;r=12/productItems/21;q=22;s=23"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(SampleController.class))
                .andExpect(handler().methodName("findProductItem"))
        ;
    }

}