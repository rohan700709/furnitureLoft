import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Furniture } from '../furniture';
import { OrderedFurniture } from '../OrderedFurniture';
import { FurnitureService } from '../service/furniture.service';
import { ImageService } from '../service/image.service';
import { OrderService } from '../service/order.service';
import { SnackbarService } from '../service/snackbar.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
   
  days:any=194;
  hours:number=22;
  mins:number=14;
  secs:number=4;

  x= setInterval(()=>{
    var validTill= new Date("Mar 9, 2024 15:34:24").getTime(); //coverts date to ms
    var today =new Date().getTime();
    var difference= validTill - today;
    this.days=Math.floor(difference/(1000*60*60*24));
    this.hours=Math.floor((difference%(1000*60*60*24))/(1000*60*60));
    this.mins=Math.floor((difference%(1000*60*60))/(1000*60));
    this.secs=Math.floor((difference%(1000*60))/(1000));
    if(difference<0){
      clearInterval(this.x);
      this.days="Offer Expired!";
    }
  },1000)

 
  

  helper = new JwtHelperService();
  role: string = '';
  constructor(private furnitureService:FurnitureService,private router:Router,private orderService:OrderService,private imageService:ImageService,private snackBarService:SnackbarService) { }
  // cartFurnitureList!:Furniture[];
   


 
  
  userEmail:string = sessionStorage.getItem('userEmail');
  furnitureData!:Furniture[];
  furnitureList!:Furniture[];

  flag1: boolean=false;
  flag2: boolean=false;
  ngOnInit(): void {
    this.userEmail;
    this.getAllFurniture();
    this.imageUrl;
    this.furnitureService.getAllCartFurniture(this.userEmail).subscribe((data: any)=>{
      this.furnitureData=data;
      });
    this.roles();
  }

  loggedIn() {
    return localStorage.getItem('token');
  }

  logOut() {
    return localStorage.removeItem('token');
  }

  roles() {
    const token = localStorage.getItem('token')
    //console.log(token);
    const decodedToken = this.helper.decodeToken(token);
    //console.log(decodedToken);
    this.role = decodedToken.sub;
    console.log(this.role);
  }

  getAllFurniture()
  {
    this.flag1=true;
    this.flag2=false;
    this.flag3=false;

    this.furnitureService.getAllFurniture().subscribe(data=>{
      this.furnitureList=data;
      console.log(this.furnitureList);
    });
  }

  // email!:string;
  getAllCartFurnituree()
  {
    this.flag2=true;
    this.flag1=false;
    this.flag3=false;

    this.furnitureService.getAllCartFurniture(this.userEmail).subscribe(data=>{
          this.furnitureList=data;
          console.log(this.furnitureList);
    });
  }


  getLowToHigh()
  {
    this.flag1=true;
    this.flag2=false;
    this.flag3=false;

    this.furnitureService.getLowToHighPrice().subscribe(data=>{
        this.furnitureList=data;
        console.log(this.furnitureList);
    });
  }

  getHighToLow()
  {
    this.flag1=true;
    this.flag2=false;
    this.flag3=false;
    this.furnitureService.getHighToLowPrice().subscribe(data=>{
        this.furnitureList=data;
        console.log(this.furnitureList);
    });
  }

  getRatingBased()
  {
    this.flag1=true;
    this.flag2=false;
    this.flag3=false;

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
  }




  searchFurniture!:string;
  searchFurnitureItem() {
    alert(this.searchFurniture);
    this.furnitureService.getSearchedFurniture(this.searchFurniture).subscribe(data => {
      this.furnitureList = data;
      console.log(this.furnitureList)
    }, (error) => {
      console.log(error);

    })
  };

  deleteFromCart(id:number)
  {
    this.furnitureService.deleteFurnitureFromCart(this.userEmail,id).subscribe(response=>{
       console.log(response);
    })
  };


  fileData!:File;
  selectedFile!: File;   //binded to html input
  imageUrl:string="http://localhost:8081/api/image/getImage/"+this.userEmail;

  onFileSelected(event:any) {
    this.selectedFile = <File>event.target.files[0];
    this.sendImage();
  }

  sendImage()
  {
    this.imageService.uploadImage(this.userEmail,this.selectedFile).subscribe(response=>{
    this.fileData=response;
    console.log("Image Data "+this.fileData);
    });
    window.location.reload();
  };


  //*********************SnackBar***************************

  displayNotification()
  {
    this.snackBarService.showSnackbar();
  }
  //*********************Orders*****************************
  placedOrders!:any;
  emailId:string = sessionStorage.getItem('userEmail');
  exportOrder!:any;
  flag3:boolean=false;
  getAllPlacedOrders()
  {

    this.flag3=true;
    this.flag2=false;
    this.flag1=false;
    // userEmail!:string = sessionStorage.getItem('userEmail');
    this.orderService.getAllData(this.userEmail).subscribe(response=>{
    this.placedOrders=response;
    console.log("Response is"+response);
    this.exportOrder=response;
    console.log(this.placedOrders);
    },
    (error)=>{
    if(error.status === 302)
    {
       this.exportOrder=error.error;
    }
    }
    );
  }

  
}
