package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.view.AuthorView;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            Iterable<AuthorView> authors = bookstoreService.fetchAuthors();

            authors.forEach(a -> System.out.println("Author name: "
                    + a.getName() + ", age: " + a.getAge()));
        };
    }
}

/*
 * How To Fetch DTO Via Blaze-Persistence Entity Views

Description: Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on Blaze-Persistence entity views.

Key points:

for Maven, add in pom.xml the dependencies specific to Blaze-Persistence
configure Blaze-Persistence via CriteriaBuilderFactory and EntityViewManager
write an entity view via an interface in Blaze-Persistence fashion
write a Spring-centric repository by extending EntityViewRepository
call method of this repository such as, findAll(), findOne(), etc
for using Spring Data Projections check this item

 * 
 */
