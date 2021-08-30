package com.example.bookcrud.service;

import com.example.bookcrud.entity.Book;
import com.example.bookcrud.exceptions.EmptyInputException;
import com.example.bookcrud.exceptions.NotFoundException;
import com.example.bookcrud.repository.BooksRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    public Book saveBook(Book book) {
        if (book.getName().isEmpty() || book.getAuthor().isEmpty() || book.getIsbn().isEmpty())
            throw new EmptyInputException(601, "One or More Input Fields are Empty");
        return booksRepository.save(book);
    }

    public List<Book> saveBooks(List<Book> books) {
        return booksRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        List<Book> books = booksRepository.findAll();
        if (books.isEmpty()) {
            throw new NotFoundException(603, "No Books Found in the Database");
        }
        return books;
    }

    public Book getBookById(int id) {
        Book bookFound = booksRepository.findById(id).orElse(null);
        if (bookFound == null)
            throw new NotFoundException(602, "No Book Found at ID: " + id);
        return bookFound;
    }


    public List<Book> getBookByUserId(int id) {
        List<Book> bookFound = booksRepository.findByUserId(id);
        if (bookFound == null)
            throw new NotFoundException(602, "No Book Found with UserID: " + id);
        return bookFound;
    }


    public List<Book> getBookByName(String name) {
        List<Book> booksFound = booksRepository.findByName(name);
        if (booksFound.isEmpty()||name ==null)
            throw new NotFoundException(602, "No Book Found with Name: " + name);
        return booksFound;
    }

    public List<Book> getBookByAuthor(String author) {
        List<Book> booksFound = booksRepository.findByAuthor(author);
        if (booksFound.isEmpty()||author==null)
            throw new NotFoundException(602, "No Book Found with Author: " + author);
        return booksFound;
    }

    public List<Book> getBookByIsbn(String isbn) {
        List<Book> booksFound = booksRepository.findByIsbn(isbn);
        if (booksFound.isEmpty()||isbn == null)
            throw new NotFoundException(602, "No Book Found with Isbn: " + isbn);
        return booksFound;
    }

    public List<Book> orderByName() {
        return booksRepository.orderBy();
    }

    public String removeBook(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book == null)
            throw new NotFoundException(602, "No Book Found with ID : " + id);
        booksRepository.deleteById(id);
        return "Book Removed at id " + id;
    }

    public String removeBookByUserID(int userId) {
        List<Book> booksFound = booksRepository.findByUserId(userId);
        if (booksFound.isEmpty())
            throw new NotFoundException(602, "No Book Found with ID : " + userId);
        booksRepository.deleteById(userId);
        return "Book Removed at id " + userId;
    }

    public Book updateBook(Book book, int id) {
        Book existingBook = booksRepository.findById(id).orElse(null);
        if (existingBook == null)
            throw new NotFoundException(602, "No Book Found at ID: " + id);
        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setCopies(book.getCopies());
        existingBook.setUserId(book.getUserId());
        return booksRepository.save(existingBook);
    }
}
