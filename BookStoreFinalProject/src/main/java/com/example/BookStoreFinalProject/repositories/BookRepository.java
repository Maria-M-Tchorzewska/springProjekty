package com.example.BookStoreFinalProject.repositories;

import com.example.BookStoreFinalProject.entities.Book;
import com.example.BookStoreFinalProject.enums.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
           SELECT b FROM Book b
           WHERE 
               (:genre IS NULL OR b.genre = :genre)
           """)
    List<Book> findByGenre(@Param("genre") BookGenre genre);

    @Query("""
           SELECT b FROM Book b
           WHERE 
               LOWER(b.title) LIKE LOWER(CONCAT('%', :searchParam, '%'))
            OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchParam, '%'))
            OR LOWER(b.genre) LIKE LOWER(CONCAT('%', :searchParam, '%'))
            OR CAST(b.price AS string) LIKE %:searchParam%
            OR LOWER(b.description) LIKE LOWER(CONCAT('%', :searchParam, '%'))
           """)
    List<Book> searchBooks(@Param("searchParam") String searchParam);
}
