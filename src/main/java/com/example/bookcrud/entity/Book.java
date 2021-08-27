package com.example.bookcrud.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;


//lombok so that we dont have to create getter and setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue
    int id;

    @NotNull
    String name;
    @NotNull
    String author;
    @NotNull
    String isbn;
    @NotNull
    int copies;
    @NotNull
    int userId;
}
