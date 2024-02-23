package com.niit.BillingService.controller;
import com.niit.BillingService.domain.Furniture;
import com.niit.BillingService.exception.FurnitureAlreadyExists;
import com.niit.BillingService.exception.UserNotFoundException;
import com.niit.BillingService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("api/v6/")
public class OrderController {

    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("saveOrder/{email}/{name}/{mobile}/{quantity}")
    public ResponseEntity<?> saveOrder(@RequestBody Furniture furniture,@PathVariable("name") String name,
                                       @PathVariable("mobile") String mobile,
                                       @PathVariable("email") String email,@PathVariable("quantity") int furnitureQuantity) throws FurnitureAlreadyExists {
        System.out.println("Inside Controller :"+furnitureQuantity);
        ResponseEntity<Object> responseEntity;
        responseEntity=new ResponseEntity<>(orderService.saveOrder(furniture,email,name,mobile,furnitureQuantity), HttpStatus.CREATED);
        System.out.println("Before Sending Data to Frontend");
        return responseEntity;
    }

    @DeleteMapping("deleteOrder/{email}")
    public ResponseEntity<?> deleteOrderedFurniture(@RequestBody Furniture furniture,@PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(orderService.deleteOrder(furniture,email),HttpStatus.GONE);
    }

    @GetMapping("getFurniture/{email}")
    public ResponseEntity<?> getAllOrderdeFurniture(@PathVariable String email) throws UserNotFoundException {
        System.out.println("inside controller");
        return new ResponseEntity<>(orderService.getOrderedFurniture(email),HttpStatus.FOUND);
    }

    @GetMapping("getPdf/{email}/{furnitureName}")
    public ResponseEntity<byte[]> getReceipt(@PathVariable("email") String email,
                                        @PathVariable("furnitureName") String furnitureName) throws Exception {
        // Set response headers
        // Set the response headers
        byte[] pdfContent=orderService.getReceiptPdf(email,furnitureName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("myfile.pdf").build());
        headers.setContentLength(pdfContent.length);

        // Return the response entity with the PDF content and headers
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);

    }
}
