package com.niit.FurnitureHomeService.controller;


import com.niit.FurnitureHomeService.exception.FurnitureAlreadyExistsException;
import com.niit.FurnitureHomeService.exception.FurnitureNotFoundException;
import com.niit.FurnitureHomeService.model.Furniture;
import com.niit.FurnitureHomeService.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v3/")
@CrossOrigin(origins = "*")
public class FurnitureController {

    private ResponseEntity responseEntity;
    private FurnitureService furnitureService;
    @Autowired
    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @PostMapping("furniture/save")
    ResponseEntity<?> saveFurniture(@RequestBody Furniture furniture) throws FurnitureAlreadyExistsException {
        try{
            furnitureService.saveFurniture(furniture);
            responseEntity= new ResponseEntity<>("Successfully Saved!!",HttpStatus.ACCEPTED);
        }
        catch (FurnitureAlreadyExistsException e){
            throw  new FurnitureAlreadyExistsException();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("furniture")
    ResponseEntity<?> getFurniture() throws FurnitureNotFoundException {
        try{
            responseEntity= new ResponseEntity<>(furnitureService.getAllFurniture(),HttpStatus.OK);
        }
        catch (FurnitureNotFoundException e){
            throw  new FurnitureNotFoundException();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("furniture/delete/{furnitureId}")
    ResponseEntity<?> deleteFurniture(@PathVariable int furnitureId) throws FurnitureNotFoundException{
        try{
            furnitureService.deleteFurniture(furnitureId);
            responseEntity= new ResponseEntity<>("Successfully Deleted!!",HttpStatus.GONE);
        }
        catch (FurnitureNotFoundException e){
            throw  new FurnitureNotFoundException();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("furniture/update/{furnitureId}")
    public ResponseEntity<?> updateFurniture(@RequestBody Furniture furniture, @PathVariable int furnitureId) throws FurnitureNotFoundException {
        try{
            furnitureService.updateFurniture(furniture,furnitureId);
            responseEntity= new ResponseEntity<>("Successfully Updated!!",HttpStatus.OK);
        }
        catch (FurnitureNotFoundException e){
            throw  new FurnitureNotFoundException();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("furniture/one/{furnitureId}")
    ResponseEntity<?> getOneFurniture(@PathVariable int furnitureId) throws FurnitureNotFoundException{
        try{
            responseEntity= new ResponseEntity<>(furnitureService.getOneFurniture(furnitureId),HttpStatus.OK);
        }
        catch (FurnitureNotFoundException e){
            throw  new FurnitureNotFoundException();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
