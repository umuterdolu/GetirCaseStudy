package com.readingisgood.repository;

import com.readingisgood.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Umut Ismet Erdolu
 */
@Repository
public interface BookRepository extends MongoRepository<BookEntity, String> {
    BookEntity findBy(String bookName);
}
