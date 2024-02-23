package com.niit.BillingService.service;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.niit.BillingService.domain.Furniture;
import com.niit.BillingService.domain.Order;
import com.niit.BillingService.domain.User;
import com.niit.BillingService.exception.FurnitureAlreadyExists;
import com.niit.BillingService.exception.FurnitureNotFoundException;
import com.niit.BillingService.exception.UserNotFoundException;
import com.niit.BillingService.repository.OrderRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository)
    {
        this.orderRepository=orderRepository;
    }


    @Override
    public User saveOrder(Furniture furniture, String emailId, String name,String mobile,int quantityFurniture) throws FurnitureAlreadyExists {
        if(orderRepository.findById(emailId).isEmpty())
        {
            Order order1=new Order();
            order1.setEmailId(emailId);
            orderRepository.save(order1);
        }
        Order order2=orderRepository.findById(emailId).get();

        if(order2.getUser()==null)
        {
            User user1=new User();
            user1.setUserName(name);
            user1.setMobileNumber(mobile);
            System.out.println("Furniture "+quantityFurniture);
            Furniture furniture1=new Furniture();
            furniture1.setFurnitureName(furniture.getFurnitureName());
            furniture1.setFurnitureId(furniture.getFurnitureId());
            furniture1.setFurniturePrice(furniture.getFurniturePrice());
            System.out.println("Furniture Quantity------>"+ quantityFurniture);
            if(quantityFurniture!=0)
            {
                furniture1.setFurnitureQuantity(quantityFurniture);

            }
            furniture1.setFurnitureDescription(furniture.getFurnitureDescription());


//            CurrentDate localDateTime=LocalDateTime.now();
//            Date date=new Date();
//            furniture1.setOrderDate(date.toLocaleDateString());
//            System.out.println("Date & Time Works");
//            furniture1.setOrderTime(localDateTime.toString());

            Date currentDate = new Date();
            // Format the date and time as strings
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
            String dateString = dateFormat.format(currentDate);
            String timeString = timeFormat.format(currentDate);
            furniture1.setOrderDate(dateString);
            furniture1.setOrderTime(timeString);


            List<Furniture> furnitureList=new ArrayList<>();
            furnitureList.add(furniture1);

            user1.setFurnitureList(furnitureList);
            order2.setUser(user1);
            orderRepository.save(order2);
            return user1;
        }
        else{
            System.out.println("Inside else block in service impl under add method");
            User user2 = order2.getUser();
            List<Furniture> allFurniture = user2.getFurnitureList();
            Date currentDate = new Date();
            // Format the date and time as strings
            Furniture furniture2=new Furniture();
            furniture2.setFurnitureName(furniture.getFurnitureName());
            furniture2.setFurnitureId(furniture.getFurnitureId());
            furniture2.setFurniturePrice(furniture.getFurniturePrice());
            furniture2.setFurnitureQuantity(quantityFurniture);
            furniture2.setFurnitureDescription(furniture.getFurnitureDescription());

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
            String dateString = dateFormat.format(currentDate);
            String timeString = timeFormat.format(currentDate);
            furniture2.setOrderDate(dateString);
            furniture2.setOrderTime(timeString);
            allFurniture.add(furniture2);
            user2.setFurnitureList(allFurniture);
            order2.setUser(user2);
            orderRepository.save(order2);
            return user2;
        }
    }

    @Override
    public List<Furniture> getOrderedFurniture(String emailId) throws UserNotFoundException {
        if(orderRepository.findById(emailId).isEmpty())
        {
            System.out.println("inside user not found if block");
            throw new UserNotFoundException();
        }
        System.out.println("inside service impl");

        Order order=orderRepository.findById(emailId).get();
        User user=order.getUser();
        System.out.println("fetched user"+user);

        List<Furniture> orders=new ArrayList<>();
        orders=user.getFurnitureList();
        System.out.println("returning list"+orders);

        return orders;
    }

    @Override
    public Furniture deleteOrder(Furniture furniture, String emailId) throws UserNotFoundException {
        if(orderRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        Order order=orderRepository.findById(emailId).get();
        User user=order.getUser();
        List<Furniture> existingFurniture=new ArrayList<>();
        existingFurniture=user.getFurnitureList();
        System.out.println("Check1");
        for(Furniture furniture1:existingFurniture)
        {
            System.out.println("Check2");
            if((furniture1.getFurnitureName()).equalsIgnoreCase(furniture.getFurnitureName()))
            {
                System.out.println("Check3");
                existingFurniture.remove(furniture1);
            }
        }
        System.out.println("Check4");
        user.setFurnitureList(existingFurniture);
        order.setUser(user);
        orderRepository.save(order);
        return furniture;
    }




    @Override
    public  byte[] getReceiptPdf(String emailId, String furnitureName) throws FurnitureNotFoundException,Exception {
        Order order=orderRepository.findById(emailId).get();
        User user=order.getUser();
        List<Furniture> furnitureList=user.getFurnitureList();
        Furniture furnitureToBeBilled=null;
        for(Furniture o1:furnitureList)
        {
            if((o1.getFurnitureName()).equalsIgnoreCase(furnitureName))
            {
                furnitureToBeBilled=o1;
            }
        }
        //PDF document object
        Document document = new Document();  //imported from itextpdf
        //output stream to write the PDF file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // create a PDF writer to write the document to the output stream
        PdfWriter pdfWriter=PdfWriter.getInstance(document, outputStream);
        document.open();
        //Cash memo colour
        Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 35, Font.BOLDITALIC, BaseColor.DARK_GRAY);
        //-------------------------------------------------
        Paragraph title = new Paragraph("Cash Memo",blueFont);
//        title.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 40, Font.BOLDITALIC));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        //************************BODY**********************
        title.setSpacingBefore(150f);
        Paragraph body = new Paragraph();
//        body.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
//        body.setPaddingTop(100);
        body.setSpacingBefore(60f);
        body.setFont( new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.LIGHT_GRAY));
//        body.add("Customer Name"+"      :       "+user.getUserName()+"\n");
//        body.add("Customer Mobile"+"    :       "+user.getMobileNumber()+"\n");
        body.add("Item Purchased"+"         :       "+furnitureToBeBilled.getFurnitureName()+"\n");
        body.add("Item Quantity"+"           :       "+furnitureToBeBilled.getFurnitureQuantity()+"\n");
//        body.add(furnitureToBeBilled.getFurnitureName()+"      :       "+furnitureToBeBilled.getFurniturePrice()+"\n");
        body.add("Item Price"+"                 :       "+furnitureToBeBilled.getFurniturePrice()+"\n");
        body.add("Total Price"+"                :       "+furnitureToBeBilled.getFurniturePrice()*
                furnitureToBeBilled.getFurnitureQuantity()+"\n");

        body.add("Product Description"+"  :       "+furnitureToBeBilled.getFurnitureDescription()+"\n");
        body.add("Date & Time"+"            :       "+furnitureToBeBilled.getOrderDate()+" "+furnitureToBeBilled.getOrderTime()+"\n");
//        body.add(furnitureToBeBilled.getFurnitureName()+"  :  "+furnitureToBeBilled.getFurniturePrice());
        document.add(body);
        //THANKYOU=========================================================
//        body.setSpacingAfter(20f);
        Paragraph footer=new Paragraph();
        footer.setSpacingBefore(100f);
        footer.setAlignment(Element.ALIGN_CENTER);
//        footer.setPaddingTop(250);
        footer.setFont( new Font(Font.FontFamily.TIMES_ROMAN, 35, Font.BOLDITALIC, BaseColor.DARK_GRAY));
        footer.add("ThankYou...");
        document.add(footer);

        // Add a watermark to the document
        PdfContentByte canvas = pdfWriter.getDirectContentUnder();
        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
        canvas.beginText();
        canvas.setFontAndSize(baseFont, 60);
        canvas.setTextMatrix(30, 30);
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.1f);
        canvas.setGState(gs);
        canvas.showTextAligned(Element.ALIGN_CENTER, "furnitureLOFT", 300, 400, 45);
        canvas.endText();


        document.close();
        outputStream.close();
        return outputStream.toByteArray();
    }
}
