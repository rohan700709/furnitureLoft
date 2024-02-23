package com.niit.FurnitureHomeService.service;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.niit.FurnitureHomeService.exception.FurnitureAlreadyExistsException;
import com.niit.FurnitureHomeService.exception.FurnitureNotFoundException;
import com.niit.FurnitureHomeService.model.Furniture;
import com.niit.FurnitureHomeService.repository.FurnitureRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class FurnitureServiceImpl implements FurnitureService {

    private FurnitureRepository furnitureRepository;
    @Autowired
    public FurnitureServiceImpl(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public Furniture saveFurniture(Furniture furniture) throws FurnitureAlreadyExistsException {
    if(furnitureRepository.findById(furniture.getFurnitureId()).isPresent()){
        throw new FurnitureAlreadyExistsException();
    }
    else
        return furnitureRepository.save(furniture);
    }

    @Override
    public List<Furniture> getAllFurniture() throws FurnitureNotFoundException {
        return furnitureRepository.findAll();
    }

    @Override
    public boolean deleteFurniture(int id) throws FurnitureNotFoundException {
        if(furnitureRepository.findById(id).isPresent())
        {
            furnitureRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Furniture updateFurniture(Furniture furniture, int id) throws FurnitureNotFoundException{
        Optional<Furniture> findFurniture=furnitureRepository.findById(id);
        if (findFurniture.isEmpty()){
            return null;
        }
        Furniture existFurniture=findFurniture.get();

        if (furniture.getFurnitureId()!=0){
            existFurniture.setFurnitureId(furniture.getFurnitureId());
        }
        if (furniture.getFurnitureName()!=null){
            existFurniture.setFurnitureName(furniture.getFurnitureName());
        }
        if (furniture.getFurniturePrice()!=0){
            existFurniture.setFurniturePrice(furniture.getFurniturePrice());
        }
        if (furniture.getFurnitureRating()!=0){
            existFurniture.setFurnitureRating(furniture.getFurnitureRating());
        }
        if (furniture.getFurnitureDescription()!=null){
            existFurniture.setFurnitureDescription(furniture.getFurnitureDescription());
        }
        return furnitureRepository.save(existFurniture);
    }

    @Override
    public Furniture getOneFurniture(int furnitureId) throws FurnitureNotFoundException {
        return furnitureRepository.findById(furnitureId).get();
    }

    @Override
    public BufferedImage byteArrayToImage(byte[] imageData) throws IOException {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageData);
        BufferedImage image= ImageIO.read(byteArrayInputStream);
        byteArrayInputStream.close();
        System.out.println("Byte Array To Image Works Inside Service Impl");
        return image;
    }
}
