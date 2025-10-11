package br.com.antonio.bookstore.repositories;

import br.com.antonio.bookstore.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {
}
