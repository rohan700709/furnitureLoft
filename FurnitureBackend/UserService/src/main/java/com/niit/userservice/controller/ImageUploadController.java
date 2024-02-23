package com.niit.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins="*")
@Controller
@RequestMapping("api/image/")
public class ImageUploadController {



    //    @Value("${upload.dir}")
    private String uploadDirectory="D:\\furnitureImages";
//    private String directory=uploadDirectory+"\"

    @PostMapping("uploadImage/{email}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String email) {
//        String fileName = file.getOriginalFilename();
//        String fileName = email;

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDirectory + email);
            Files.write(path, bytes);
            return ResponseEntity.ok().body("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }



    @GetMapping(value = "/getImage/{email}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String email) throws IOException {

            File imageFile = new File(uploadDirectory + email);
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(fileInputStream));

    }
//    @Value("${upload.dir}")
//    private String uploadDirectory;


//    @PostMapping("uploadImage")
//    public void uploadImage(@RequestParam("imageFile") MultipartFile file) {
//
//        try {
//            if (file.isEmpty()) {                      //to check if uploaded file is empty
//                System.out.println("File is empty");
//                throw new RuntimeException("empty image");
//            }
//            System.out.println("File is not empty");
//            String fileName = file.getOriginalFilename();
//            String extension = fileName.substring(fileName.lastIndexOf("."));
//            String filePath = uploadDirectory + "/" + fileName;
//            File directory = new File(uploadDirectory);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            file.transferTo(new File(filePath));
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//    }



}
