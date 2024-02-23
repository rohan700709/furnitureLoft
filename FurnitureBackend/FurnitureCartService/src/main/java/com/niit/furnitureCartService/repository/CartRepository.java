package com.niit.furnitureCartService.repository;
import com.niit.furnitureCartService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<User, String> {
}
