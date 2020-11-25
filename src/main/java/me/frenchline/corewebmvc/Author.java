package me.frenchline.corewebmvc;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;

import static me.frenchline.corewebmvc.BookJsonView.*;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
public class Author {

    @JsonView(BookDetailInfo.class)
    private Long id;

    @JsonView(BookDetailInfo.class)
    private String firstName;

    @JsonView(BookDetailInfo.class)
    private String lastName;

    private String email;

    private String address;

    private Date joinAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJoinAt() {
        return joinAt;
    }

    public void setJoinAt(Date joinAt) {
        this.joinAt = joinAt;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", joinAt=" + joinAt +
                '}';
    }
}
