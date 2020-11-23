package me.frenchline.corewebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@Controller
public class SampleController {

    @GetMapping("/events/id/{id}/name/{name}")
    @ResponseBody
    public Event getEventOldPattern(@PathVariable Integer id, @PathVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        return event;
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Integer id, @MatrixVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        return event;
    }

    // GET /pets/42;q=11;r=22
    @GetMapping("/pets/{petId}")
    @ResponseBody
    public void findPet(@PathVariable String petId, @MatrixVariable int q) {

        // petId == 42
        // q == 11

        System.out.println("petId = " + petId);
        System.out.println("q = " + q);
    }

    // GET /users/42
    @GetMapping("/users/{userId}")
    @ResponseBody
    public void findUser(@MatrixVariable(required=false, defaultValue="1") int q) {

        // q == 1

        System.out.println("q = " + q);
    }

    // GET /owners/42;q=11/pets/21;q=22
    @GetMapping("/owners/{ownerId}/pets/{petId}")
    @ResponseBody
    public void findPet(
            @MatrixVariable(name="q", pathVar="ownerId") int q1,
            @MatrixVariable(name="q", pathVar="petId") int q2) {

        // q1 == 11
        // q2 == 22

        System.out.println("q1 = " + q1);
        System.out.println("q2 = " + q2);
    }

    // MultiValueMap 사용해 모든 행렬 변수 얻기
    // GET /products/42;q=11;r=12/productItems/21;q=22;s=23
    @GetMapping("/products/{productId}/productItems/{productItemId}")
    @ResponseBody
    public void findProductItem(
            @MatrixVariable MultiValueMap<String, String> matrixVars,
            @MatrixVariable(pathVar="productItemId") MultiValueMap<String, String> petMatrixVars) {

        // matrixVars: ["q" : [11,22], "r" : 12, "s" : 23]
        // petMatrixVars: ["q" : 22, "s" : 23]

        System.out.println("matrixVars = " + matrixVars);
        System.out.println("petMatrixVars = " + petMatrixVars);
    }

}
