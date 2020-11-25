package me.frenchline.corewebmvc;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static me.frenchline.corewebmvc.BookJsonView.*;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@RestController
public class BookController {

    @GetMapping("/books")
//    @JsonView(BookSimpleInfo.class)
    @JsonView(BookDetailInfo.class)
    public List<Book> getBooks() {
        return books();
    }

    private List<Book> books() {

        Author frenchline = new Author();
        frenchline.setFirstName("frenchline");
        frenchline.setLastName("black");
        frenchline.setEmail("frenchline707@gmail.com");
        frenchline.setId(100l);
        frenchline.setAddress("aasdfjksd bskd asdfkd");
        frenchline.setJoinAt(new Date());

        Author keesun = new Author();
        keesun.setFirstName("keesun");
        keesun.setLastName("white");
        keesun.setEmail("keesun@email.com");
        keesun.setId(101l);
        keesun.setAddress("bbbk sadkfnsd asdfkn");
        keesun.setJoinAt(new Date());

        Book book = new Book();
        book.setId(1l);
        book.setTitle("spring Boot");
        book.setIsbn("aksdlfnalsdnksaskdfnasld");
        book.setPublished(new Date());
        book.addAuthor(frenchline);
        book.addAuthor(keesun);
        book.addAuthor(keesun);

        return Arrays.asList(book);
    }

}
