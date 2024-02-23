
import { Component, Inject, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Furniture } from '../furniture';
import { OrderedFurniture } from '../OrderedFurniture';
import { FurnitureService } from '../service/furniture.service';
import { OrderService } from '../service/order.service';
import { SnackbarService } from '../service/snackbar.service';

@Component({
  selector: 'app-bookmark',
  templateUrl: './bookmark.component.html',
  styleUrls: ['./bookmark.component.css']
})
export class BookmarkComponent implements OnInit {

 

  
  constructor(private furnitureService: FurnitureService,private snackBar:SnackbarService,private orderService:OrderService, private router: Router)
   { }

  @Input() 
  furnitureValue!:Furniture[];


  p:any;
  ngOnInit(): void {
    console.log("P-C works "+this.furnitureValue);
    this.getFurnitureCartList();
     
  }
  
  

  

  furnitureCartList!:Furniture[];
  getFurnitureCartList() {

    const cartName = "MyCart";
    const userEmail = sessionStorage.getItem('userEmail');

    this.furnitureService.getAllCartFurniture(cartName).subscribe(
      response => {
        // this.furnitureCartList = false;
        this.furnitureCartList = response;
        console.log(response);
        console.log(this.furnitureCartList);
      },
      error => {
        console.log(error);
      }
    )

  }

  deletedSuccess() {
   
  }

  furniture!:Furniture;
  deleteCartFurniture(i: any) {

    this.furniture = this.furnitureValue[i];
    console.log(this.furnitureValue);
    const id = this.furniture.furnitureId;
    const userEmail = sessionStorage.getItem('userEmail');
    console.log(userEmail);
    this.furnitureService.deleteFurnitureFromCart(userEmail,id).subscribe(
      () => {
        console.log("Deleted");
      },
      error => {
        console.log(error);
      }
    )
    window.location.reload();
    // this.router.navigate(['/bookmark']);

  }


  loggedIn() {
    return localStorage.getItem('token');
  }

  placeOrder()
  {
    // this.showQuantityBox();
    alert(this.quantityValue);
    var name="No Name";
    var phone="7017074900";
    const userEmail = sessionStorage.getItem('userEmail');
    alert("Before method calling"+this.quantityValue);
    this.orderService.addOrder(this.orderList,userEmail,name,phone, this.quantityValue).subscribe(response=>{
      console.log(response);
    });
    this.closeQuantityBox();
    this.snackBar.showSnackbar();
  }

 //---------------------Quantity Code-------------------------
  quantityValue!:number;
  orderList!:Furniture;
  recordQuantity(value:number)
  {
   this.quantityValue=value;
  }

  takeList(list:Furniture)
  {
    this.orderList=list;
    this.showQuantityBox();
  }

  showQuantityBox() {
  var quantity = document.getElementById("quantity");
  quantity.className = "show";
  // setTimeout(function(){ snackbar.className = snackbar.className.replace("show", "");},4000);
  }
  closeQuantityBox() {
    var quantity = document.getElementById("quantity");
    quantity.className = "";
    // setTimeout(function(){ snackbar.className = snackbar.className.replace("show", "");},4000);
    }

}
