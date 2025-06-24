package com.example.BookStoreFinalProject.services;

import com.example.BookStoreFinalProject.dtos.BookDTO;
import com.example.BookStoreFinalProject.dtos.Response;
import com.example.BookStoreFinalProject.entities.Book;
import com.example.BookStoreFinalProject.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book saved = bookRepository.save(book);
        return Response.builder()
                .status(201)
                .message("Book added successfully")
                .book(modelMapper.map(saved, BookDTO.class))
                .build();
    }

    @Override
    public Response getAllBooks() {
        List<BookDTO> books = bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .message("Books fetched successfully")
                .books(books)
                .build();
    }

    @Override
    public Response getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        return Response.builder()
                .status(200)
                .book(modelMapper.map(book, BookDTO.class))
                .message("Book found")
                .build();
    }

    @Override
    public Response updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setGenre(bookDTO.getGenre());
        book.setPrice(bookDTO.getPrice());
        book.setDescription(bookDTO.getDescription());
        book.setCoverImageUrl(bookDTO.getCoverImageUrl());

        Book updated = bookRepository.save(book);

        return Response.builder()
                .status(200)
                .message("Book updated successfully")
                .book(modelMapper.map(updated, BookDTO.class))
                .build();
    }

    @Override
    public Response deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        bookRepository.delete(book);

        return Response.builder()
                .status(200)
                .message("Book deleted successfully")
                .build();
    }
}
