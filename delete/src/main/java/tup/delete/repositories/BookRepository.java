package tup.delete.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tup.delete.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}