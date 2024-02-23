package com.niit.FurnitureHomeService.controller;

import com.niit.FurnitureHomeService.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins="*")
@Controller
@RequestMapping("api/furnitureImage/")
public class FurnitureImageUploader {
    private FurnitureService furnitureService;
    @Autowired
    public FurnitureImageUploader(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }


    private String uploadDirectory="D:/CapstoneProject/FurnitureTeam10/src/assets/img/";


    @PostMapping("uploadFurnitureImage/{furnitureName}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String furnitureName) {

        System.out.println("Furniture Name :"+furnitureName);
        try {
            byte[] bytes = file.getBytes();
            BufferedImage bi=furnitureService.byteArrayToImage(bytes);
//            Path path = Paths.get(uploadDirectory + furnitureName);
            System.out.println("Inside Controller After Sevice Call");

            File outputDestNameExt=new File(uploadDirectory+furnitureName+".png");
            System.out.println("File Output :"+outputDestNameExt);
            ImageIO.write(bi,"png",outputDestNameExt);
            System.out.println("After image.io write method");
            return ResponseEntity.ok().body("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }
}
