package com.example.bookcrud.repository;

import com.example.bookcrud.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book,Integer> {

    List<Book> findByName(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByIsbn(String isbn);

//    @Query("FROM Book B ORDER BY B.name")
    @Query(value = "select * from book order by name",nativeQuery = true)
    List<Book> orderBy();

}
