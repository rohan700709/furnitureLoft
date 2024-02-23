import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Furniture } from '../furniture';
import { FurnitureService } from '../service/furniture.service';
import { SnackbarService } from '../service/snackbar.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  furnitureList!:Furniture[];

  constructor(private furnitureService:FurnitureService,private snackBar:SnackbarService,private router:Router) { }

  ngOnInit(): void {
  }
  @Input()
  furnitureMultiData!:Furniture[];

  p:any;
  userEmail:string = sessionStorage.getItem('userEmail');
  // email!:string;
  getAllCartFurnituree()
  {
    this.furnitureService.getAllCartFurniture(this.userEmail).subscribe(data=>{
          this.furnitureList=data;
          console.log(this.furnitureList);
    });
  }


  getLowToHigh()
  {
    this.furnitureService.getLowToHighPrice().subscribe(data=>{
        this.furnitureList=data;
        console.log(this.furnitureList);
    });
  }

  getHighToLow()
  {
    this.furnitureService.getHighToLowPrice().subscribe(data=>{
        this.furnitureList=data;
        console.log(this.furnitureList);
    });
  }

  getRatingBased()
  {
    this.furnitureService.getRatedFurniture().subscribe(data=>{
        this.furnitureList=data;
        console.log(this.furnitureList);
    });
  }


  
  addToCart(list:Furniture)
  {
    this.furnitureService.addToCartFurniture(list,this.userEmail).subscribe(response=>{
      console.log(response);
    });
    this.snackBar.showSnackbar();
    window.location.reload();
    // this.router.navigate(['bookmark']);
  }

  loggedIn() {
    return localStorage.getItem('token');
  }

}
