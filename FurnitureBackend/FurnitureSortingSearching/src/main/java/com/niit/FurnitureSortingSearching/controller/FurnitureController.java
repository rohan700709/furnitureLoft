package com.niit.FurnitureSortingSearching.controller;
import com.niit.FurnitureSortingSearching.exception.FurnitureNotFoundExceptions;
import com.niit.FurnitureSortingSearching.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v5/")
@CrossOrigin(origins = "*")
public class FurnitureController {

    private ResponseEntity responseEntity;
    private FurnitureService furnitureService;
    @Autowired
    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping("furnitureL2H")
    ResponseEntity<?> getFurnitureLowToHigh() throws FurnitureNotFoundExceptions {
        try{
            responseEntity= new ResponseEntity<>(furnitureService.lowToHighPrice(), HttpStatus.OK);
        }
        catch (FurnitureNotFoundExceptions e){
            throw  new FurnitureNotFoundExceptions();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }



    @GetMapping("furnitureH2L")
    ResponseEntity<?> getFurnitureHighToLow() throws FurnitureNotFoundExceptions {
        try{
            responseEntity= new ResponseEntity<>(furnitureService.highToLowPrice(), HttpStatus.OK);
        }
        catch (FurnitureNotFoundExceptions e){
            throw  new FurnitureNotFoundExceptions();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("furnitureH2LRating")
    ResponseEntity<?> getFurnitureRatingHighToLow() throws FurnitureNotFoundExceptions {
        try{
            responseEntity= new ResponseEntity<>(furnitureService.highToLowRating(), HttpStatus.OK);
        }
        catch (FurnitureNotFoundExceptions e){
            throw  new FurnitureNotFoundExceptions();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @GetMapping("searchFurniture/{itemName}")
    ResponseEntity<?> getFurnitureItemsByName(@PathVariable String itemName) throws FurnitureNotFoundExceptions {
        try{
            responseEntity= new ResponseEntity<>(furnitureService.searchFurnitureItems(itemName), HttpStatus.OK);
        }
        catch (FurnitureNotFoundExceptions e){
            throw  new FurnitureNotFoundExceptions();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
