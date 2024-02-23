import { Component, Input, OnInit } from '@angular/core';
import * as saveAs from 'file-saver';
import { OrderService } from '../service/order.service';
import { SnackbarService } from '../service/snackbar.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(private orderService:OrderService,private snackBar:SnackbarService) { }

  ngOnInit(): void {
    this.email;
    this.furnitureName;
  }


  @Input()
  importOrder!:any;

  furnitureName!:string;
  email:string=sessionStorage.getItem('userEmail');
  pdfLink:string="http://localhost:8086/api/v6/getPdf/"+this.email+"/"+this.furnitureName;

  getFurnitureName(furniture:string)
  {
    console.log(furniture);
    alert(furniture);
    this.furnitureName=furniture;
    this.downloadPdf();
  }

  downloadPdf()
  {
    this.orderService.getPdf(this.email,this.furnitureName).subscribe((data: any) => {
      const pdfBlob = new Blob([data], { type: 'application/pdf' });
      alert(pdfBlob);
      console.log(pdfBlob);
      const filename = 'myfile.pdf';
      // saveAs.init();
      saveAs(pdfBlob, filename);
    });
    
    alert("downloadPdf works");
  }

}


