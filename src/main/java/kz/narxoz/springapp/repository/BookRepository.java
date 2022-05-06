package kz.narxoz.springapp.repository;

import kz.narxoz.springapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}