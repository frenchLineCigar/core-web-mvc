package me.frenchline.corewebmvc;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
public class Event {

    private Integer id;

    private String name;

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
