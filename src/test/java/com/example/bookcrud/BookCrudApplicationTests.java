package com.example.bookcrud;

import com.example.bookcrud.controller.BooksController;
import com.example.bookcrud.entity.Book;
import com.example.bookcrud.service.BooksService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookCrudApplicationTests {

    @Autowired
    BooksService booksService;

    @Before
    void testBookServerDelete(){
        System.out.printf("THE TEST BEFORE");
    }

    @Test
    void testBooksServiceSave() {
        Book book = booksService.saveBook(new Book(6,"H","J","3",3,1));

        assertEquals(new Book(6,"H","J","3",3,1),book);
    }

}
