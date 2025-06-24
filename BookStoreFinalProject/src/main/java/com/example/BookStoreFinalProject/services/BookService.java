package com.example.BookStoreFinalProject.services;

import com.example.BookStoreFinalProject.dtos.BookDTO;
import com.example.BookStoreFinalProject.dtos.Response;

public interface BookService {

    Response addBook(BookDTO bookDTO);

    Response getAllBooks();

    Response getBookById(Long id);

    Response updateBook(Long id, BookDTO bookDTO);

    Response deleteBook(Long id);
}
