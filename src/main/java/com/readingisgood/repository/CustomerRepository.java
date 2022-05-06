package com.readingisgood.repository;

import com.readingisgood.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Umut Ismet Erdolu
 */
@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
    CustomerEntity findBy(String idNumber);
}
