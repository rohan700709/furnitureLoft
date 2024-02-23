package com.niit.FurnitureSortingSearching.service;
import com.niit.FurnitureSortingSearching.comparator.HighToLowComparator;
import com.niit.FurnitureSortingSearching.comparator.HighToLowRating;
import com.niit.FurnitureSortingSearching.comparator.LowToHighComparator;
import com.niit.FurnitureSortingSearching.exception.FurnitureNotFoundExceptions;
import com.niit.FurnitureSortingSearching.model.FurniturePOJO;
import com.niit.FurnitureSortingSearching.mongoDB.MongoDBClientDB;
import org.bson.Document;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class FurnitureServiceImpl implements FurnitureService{


    MongoDBClientDB mongoDBClientDB =new MongoDBClientDB();


//    private FurnitureRepository furnitureRepository;
//    @Autowired
//    public FurnitureServiceImpl(FurnitureRepository furnitureRepository) {
//        this.furnitureRepository = furnitureRepository;
//    }

    @Override
    public  List<FurniturePOJO> lowToHighPrice() throws FurnitureNotFoundExceptions {
        System.out.println("inside low to high in serviceImpl");
        List<FurniturePOJO> mongoData;
       // List<org.bson.Document> data=new ArrayList<>();
        List<Document> documents=mongoDBClientDB.getFurnitureHomeDataBase().find().into(new ArrayList<>());
//        (List<FurniturePOJO>)data;
        ModelMapper modelMapper=new ModelMapper();
        mongoData=documents.stream().map(d-> modelMapper.map(d , FurniturePOJO.class)).collect(Collectors.toList());
        int count=200;
        for(FurniturePOJO obj: mongoData)
        {
            count++;
            obj.setFurnitureId(count);
        }
        System.out.println("~~~~~~~~~~~~~~~~Fetched Data From Home Database~~~~~~~~~~~~~~~~~~"+mongoData);
        LowToHighComparator lowToHighComparator=new LowToHighComparator();
        Collections.sort(mongoData, lowToHighComparator);
        System.out.println("******Before return of  low to high in serviceImpl*******"+mongoData);
        return mongoData;
    }

    @Override
    public List<FurniturePOJO> highToLowPrice() throws FurnitureNotFoundExceptions{
        List<FurniturePOJO> mongoData;
//        mongoData=(List<FurniturePOJO>) mongoDBClientDB.getFurnitureHomeDataBase();
        List<Document> documents=mongoDBClientDB.getFurnitureHomeDataBase().find().into(new ArrayList<>());
//        (List<FurniturePOJO>)data;
        ModelMapper modelMapper=new ModelMapper();
        mongoData=documents.stream().map(d-> modelMapper.map(d , FurniturePOJO.class)).collect(Collectors.toList());
        int count=200;
        for(FurniturePOJO obj: mongoData)
        {
            count++;
            obj.setFurnitureId(count);
        }
        HighToLowComparator highToLowComparator=new HighToLowComparator();
        Collections.sort(mongoData, highToLowComparator);
        return mongoData;
    }

    @Override
    public List<FurniturePOJO> highToLowRating() throws FurnitureNotFoundExceptions {
        List<FurniturePOJO> mongoRatedData;
        List<Document> documents=mongoDBClientDB.getFurnitureHomeDataBase().find().into(new ArrayList<>());
        ModelMapper modelMapper=new ModelMapper();
        mongoRatedData=documents.stream().map(d-> modelMapper.map(d , FurniturePOJO.class)).collect(Collectors.toList());
        int count=200;
        for(FurniturePOJO obj: mongoRatedData)
        {
                count++;
                obj.setFurnitureId(count);
        }
        HighToLowRating highToLowRating=new HighToLowRating();
        Collections.sort(mongoRatedData, highToLowRating);
        return mongoRatedData;
    }

    @Override
    public List<FurniturePOJO> searchFurnitureItems(String itemName) throws FurnitureNotFoundExceptions {

        List<FurniturePOJO> searchedItems;
        searchedItems= mongoDBClientDB.searchDocuments(itemName);
        return searchedItems;
    }
}
