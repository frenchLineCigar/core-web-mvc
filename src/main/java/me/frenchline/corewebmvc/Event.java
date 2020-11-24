package me.frenchline.corewebmvc;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
public class Event {

    private Integer id;

    @NotBlank(message = "필수 입력 정보입니다")
    private String name;

    @Min(value = 0, message = "0 이상의 값을 입력해주세요")
    private Integer limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
