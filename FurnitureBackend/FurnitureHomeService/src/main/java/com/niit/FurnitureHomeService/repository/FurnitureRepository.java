package com.niit.FurnitureHomeService.repository;
import com.niit.FurnitureHomeService.model.Furniture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends MongoRepository<Furniture,Integer> {

//    @Query("{'furnitureName':{$in:[?0]}}")
//    Furniture getByFurnitureName(String itemName);

}
