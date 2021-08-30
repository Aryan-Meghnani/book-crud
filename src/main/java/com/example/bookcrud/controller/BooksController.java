package com.example.bookcrud.controller;

import com.example.bookcrud.entity.Book;
import com.example.bookcrud.entity.BookWithUser;
import com.example.bookcrud.entity.Users;
import com.example.bookcrud.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    // request body so that json can be passed
    // path variable to get value

    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody Book book) throws UnsupportedEncodingException {

        Book bookSaved = booksService.saveBook(encodeBook(book));
        return new ResponseEntity<>(decodeBook(bookSaved),HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books) throws UnsupportedEncodingException{
        List<Book> booksSaved = booksService.saveBooks(encodeBooks(books));
        return new ResponseEntity<>(decodeBooks(booksSaved),HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getBooks() throws UnsupportedEncodingException {
        List<Book> books = booksService.getBooks();
        return new ResponseEntity<>(decodeBooks(books), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id) throws UnsupportedEncodingException {
        Book bookRetrieved = booksService.getBookById(id);
        return new ResponseEntity<>(decodeBook(bookRetrieved),HttpStatus.OK);
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<List<BookWithUser>> findBookByUserId(@PathVariable int userid) throws UnsupportedEncodingException {

        RestTemplate restTemplate = new RestTemplate();
//        Users users = restTemplate.getForObject("http://localhost:3000/users", Users.class);

        List<Book> booksRetrieved = booksService.getBookByUserId(userid);
        List<BookWithUser> bookWithUserName = booksRetrieved.stream().map(book->{
            Users user =restTemplate.getForObject("http://localhost:3000/users/"+book.getUserId(), Users.class);
            System.out.println(user+""+book.getUserId());
            try {
                return new BookWithUser(decodeBook(book),user);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(bookWithUserName,HttpStatus.OK);
    }

    //get mapping for getting the value
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Book>> findBookByName(@PathVariable String name) throws UnsupportedEncodingException {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.name());
        List<Book> booksRetrieved = booksService.getBookByName(encodedName);
        return new ResponseEntity<>(decodeBooks(booksRetrieved),HttpStatus.OK);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> findBookByAuthor(@PathVariable String author) throws UnsupportedEncodingException {
        String encodedAuthor = URLEncoder.encode(author, StandardCharsets.UTF_8.name());
        List<Book> booksRetrieved = booksService.getBookByAuthor(encodedAuthor);
        return new ResponseEntity<>(decodeBooks(booksRetrieved),HttpStatus.OK);
    }

    @GetMapping("/namesOrder")
    public ResponseEntity<List<Book>> orderByNameMethod() throws UnsupportedEncodingException {
        List<Book> booksRetrieved = booksService.orderByName();
        return new ResponseEntity<>(decodeBooks(booksRetrieved),HttpStatus.OK);
    }

    // for update there is put mapping
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable int id) throws UnsupportedEncodingException {
        Book bookRetrived = booksService.updateBook(encodeBook(book),id);
        return new ResponseEntity<>(decodeBook(bookRetrived),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeBook(@PathVariable int id){
        String status = booksService.removeBookByUserID(id);
        return new ResponseEntity<>(status, HttpStatus.ACCEPTED);
    }


    List<Book> decodeBooks(List<Book> books) throws UnsupportedEncodingException {
        List<Book> decodedBooks = new ArrayList<>();
        for(Book b: books){
            decodedBooks.add(decodeBook(b));
        }
        return decodedBooks;
    }

    List<Book> encodeBooks(List<Book> books) throws UnsupportedEncodingException {
        List<Book> encodedBooks = new ArrayList<>();
        for(Book b: books){
            encodedBooks.add(encodeBook(b));
        }
        return encodedBooks;
    }

    Book decodeBook(Book book) throws UnsupportedEncodingException {
        int id = book.getId();
        String name = URLDecoder.decode(book.getName(), StandardCharsets.UTF_8.name());
        String author = URLDecoder.decode(book.getAuthor(), StandardCharsets.UTF_8.name());
        String isbn = URLDecoder.decode(book.getIsbn(), StandardCharsets.UTF_8.name());
        int copies = book.getCopies();
        int userId = book.getUserId();
        return new Book(id,name,author,isbn,copies,userId);
    }

    Book encodeBook(Book book) throws UnsupportedEncodingException {
        int id= book.getId();
        String name = URLEncoder.encode(book.getName(), StandardCharsets.UTF_8.name());
        String author = URLEncoder.encode(book.getAuthor(), StandardCharsets.UTF_8.name());
        String isbn = URLEncoder.encode(book.getIsbn(), StandardCharsets.UTF_8.name());
        int copies = book.getCopies();
        int userId = book.getUserId();
        return new Book(id,name,author,isbn,copies,userId);
    }


}
