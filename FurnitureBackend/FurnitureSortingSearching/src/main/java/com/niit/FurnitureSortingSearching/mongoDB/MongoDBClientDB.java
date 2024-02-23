package com.niit.FurnitureSortingSearching.mongoDB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.niit.FurnitureSortingSearching.model.FurniturePOJO;
import org.bson.Document;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MongoDBClientDB {

    public MongoCollection<Document> getFurnitureHomeDataBase() {
        System.out.println("inside mongoDb client class ");
        String connectionString = "mongodb://127.0.0.1:27017/";
        MongoClient mongoClient = new MongoClient(new MongoClientURI(connectionString));
        System.out.println("just after mongo client"+mongoClient);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("furnitureHomeDb");
        System.out.println("just after mongo database"+ mongoDatabase);
        MongoCollection<Document> collection = mongoDatabase.getCollection("furniture");
        System.out.println("just after mongo collection");
//      Document document=new Document("furnitureId",102).append("furnitureName","Table").append("furniturePrice",29999);
//      collection.insertOne(document);
        System.out.println("Collection is : "+collection);
//      List<Document> data=new ArrayList<>();
//      collection.find().into(data);
//      mongoClient.close();
        return collection;
    }




    public List<FurniturePOJO> searchDocuments(String itemName) {
        int count=200;
        ModelMapper modelMapper=new ModelMapper();
//        Document query = new Document("furnitureName", itemName);
//        Document result=getFurnitureHomeDataBase().find(query).first();
//                .forEach((doc) -> {
//            System.out.println(doc.toJson());
//            return doc;
//        });
//        System.out.println(result.toJson());
//        return result;
        List<FurniturePOJO> allResults;
        List<FurniturePOJO> results =new ArrayList<FurniturePOJO>();
        List<Document> allDocuments=getFurnitureHomeDataBase().find().into(new ArrayList<>());
        allResults=allDocuments.stream().map(doc->modelMapper.map(doc,FurniturePOJO.class)).collect(Collectors.toList());
        System.out.println("All Results "+ allResults);
        for(FurniturePOJO m: allResults)
        {
            count++;
            m.setFurnitureId(count);
        }
        System.out.println("After 1st for");

        for(FurniturePOJO obj:allResults){
            System.out.println("Inside 2nd for loop");
            String itemToSearch=itemName.toUpperCase();
            if((obj.getFurnitureName()).contains(itemToSearch) ||  (obj.getFurnitureName()).startsWith(itemToSearch)
                    || (obj.getFurnitureName()).equalsIgnoreCase(itemToSearch) )
//            if((obj.getFurnitureName()).equalsIgnoreCase(itemName))
            {
                System.out.println("inside if statement");
                results.add(obj);
                System.out.println(results);
            }
            System.out.println("outside if and inside 2nd for loop");
        }
        System.out.println("List of Searched Items : "+ results);
//        Document> searchedItems = allResults.find(queryDocument)
//        List<Document> searchedDocuments=getFurnitureHomeDataBase().find(queryDocument).into(new ArrayList<>());
//        results=searchedDocuments.stream().map(d-> modelMapper.map(d , FurniturePOJO.class)).collect(Collectors.toList());
        return results;
    }
}
