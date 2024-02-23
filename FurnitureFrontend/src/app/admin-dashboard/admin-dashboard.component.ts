import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FurnitureService } from '../service/furniture.service';
import { Furniture } from '../furniture';
import { HttpClient } from '@angular/common/http';
import { ImageService } from '../service/image.service';
import { SnackbarService } from '../service/snackbar.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {


  // furnitureFg!:FormGroup;
  constructor(private fb: FormBuilder, private furnitureService: FurnitureService, private router: Router
    ,private imageService:ImageService ,private ar: ActivatedRoute,private http:HttpClient,private snackBar:SnackbarService) { }


  furnitureFg = this.fb.group({
    furnitureId: [null,[Validators.required]],
    furnitureName: [null,[Validators.required]],
    furniturePrice: [null,[Validators.required]],
    furnitureRating:[null,[Validators.required]],
    furnitureDescription:[null,[Validators.required]]
 });
 
  ngOnInit(): void {
   
  }

 
  furnitureSuccess() {
  
  }
 


  onSave() {
    
    this.furnitureService.addFurniture(this.furnitureFg.value).subscribe(
      data => {
        console.log(data);
        this.furnitureSuccess();
        this.ngOnInit();
      }
    );

  }
// ************************************************FurnitureImage**********************************************
  selectedFile: File = null;

  onFileSelected(event:any) {
    this.selectedFile = <File>event.target.files[0];
  }
 
  fileData!:File;
  onUpload() {

    const furnitureName1=this.furnitureFg.value.furnitureName;
    this.imageService.uploadFurnitureImage(furnitureName1,this.selectedFile).subscribe(response=>{
        this.fileData=response;
        console.log("Image Data "+this.fileData);
        });
      this.snackBar.showSnackbar();  
        window.location.reload();
  }

  
}
