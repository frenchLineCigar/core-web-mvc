package me.frenchline.corewebmvc;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static me.frenchline.corewebmvc.BookJsonView.*;

public class Book {

    @JsonView({BookSimpleInfo.class})
    private Long id;

    @JsonView({BookSimpleInfo.class})
    private String isbn;

    @JsonView(BookDetailInfo.class)
    private Date published;

    @JsonView(BookDetailInfo.class)
    private Set<Author> authors = new LinkedHashSet<>();

    @JsonView({BookSimpleInfo.class})
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    protected Set<Author> getAuthorsInternal() {
        if (this.authors == null) {
            this.authors = new HashSet<>();
        }
        return this.authors;
    }

    protected void setAuthorsInternal(Collection<Author> authors) {
        this.authors = new LinkedHashSet<>(authors);
    }

    public List<Author> getAuthors() {
        List<Author> sortedAuthors = new ArrayList<>(getAuthorsInternal());
        System.out.println("sortedAuthors = " + sortedAuthors);
        PropertyComparator.sort(sortedAuthors, new MutableSortDefinition("firstName", false, true));
        List<Author> authors = Collections.unmodifiableList(sortedAuthors);
        System.out.println("authors = " + authors);
        return authors;
    }

    public void addAuthor(Author author) {
        getAuthorsInternal().add(author);
        //author.setBookId(this.getId());
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                ", published=" + published +
                '}';
    }

}