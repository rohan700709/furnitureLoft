import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private httpClient: HttpClient) { }



  url1:string=" http://localhost:8081/api/image/uploadImage/";
  url2:string=" http://localhost:8081/api/image/getImage/";


  uploadImage(userEmail:string,file:File):Observable<any>
  {

    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${this.url1+userEmail}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.httpClient.request(req);  
  }

  url8084:string=" http://localhost:8084/api/furnitureImage/uploadFurnitureImage/";

  uploadFurnitureImage(furnitureName:string,file:File):Observable<any>
  {

    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${this.url8084+furnitureName}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.httpClient.request(req);  
  }


  getImage(userEmail:string):Observable<any>
  {
    return this.httpClient.get<any>(this.url2 + userEmail);
  }
}
