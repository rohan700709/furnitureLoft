package com.niit.BillingService.repository;
import com.niit.BillingService.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
}
