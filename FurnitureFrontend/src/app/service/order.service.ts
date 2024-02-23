import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Furniture } from '../furniture';
import { OrderedFurniture } from '../OrderedFurniture';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient:HttpClient) { }

  url:string=" http://localhost:8086/api/v6/getFurniture/";
  url1:string=" http://localhost:8086/api/v6/saveOrder/";
  url2:string=" http://localhost:8086/api/v6/getPdf/";



  getAllData(emailId:string):Observable<any>
  {
    return this.httpClient.get<any>(this.url+emailId);
  }

  addOrder(data:Furniture,email:string,name:string,phone:string,quantity:number):Observable<any>
  {
    return this.httpClient.post<OrderedFurniture>(this.url1+email+"/"+name+"/"+phone+"/"+quantity,data);
  }

  getPdf(email:string,furnitureName:string):Observable<any>
  {
    return this.httpClient.get<any>(this.url2+email+"/"+furnitureName, { responseType : 'blob' as 'json'});
  }
}
