import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Furniture } from '../furniture';

@Injectable({
  providedIn: 'root'
})
export class FurnitureService {

  url:string="http://localhost:8084/api/v3/furniture"  //8084furHome
  url2:string="http://localhost:8084/api/v3/furniture/save"
  url3:string="http://localhost:8084/api/v3/furniture/delete"
  url4:string="http://localhost:8084/api/v3/furniture/update"
  url5:string="http://localhost:8084/api/v3/furniture/one"
  url6:string="http://localhost:8083/api/v4/cartService/cart/"
  url7:string="http://localhost:8085/api/v5/furnitureL2H"
  url8:string="http://localhost:8085/api/v5/furnitureH2L"
  constructor(private httpclient:HttpClient) { }

  getAllFurniture():Observable<Furniture[]>
  {
      return this.httpclient.get<Furniture[]>(this.url);
  }
  addFurniture(data:Furniture):Observable<any>
  {
      return this.httpclient.post(this.url2,data);
  }
  getOneFurniture(id:any):Observable<any>{
    return this.httpclient.get(this.url5+"/"+id)
  }
  editFurniture(id:any,note:any)
  {
    return this.httpclient.put(this.url4+"/"+id,note);
  }
  deleteFurniture(id:any)
  {
    return this.httpclient.delete(this.url3+"/"+id);
  }


 
 // email:string= sessionStorage.getItem("userEmail");
  getAllCartFurniture(userEmail:string):Observable<Furniture[]>
  {
    return this.httpclient.get<Furniture[]>(this.url6+userEmail+"/MyCart");
  }


  getLowToHighPrice():Observable<Furniture[]>
  {
    return this.httpclient.get<Furniture[]>(this.url7);
  }

  getHighToLowPrice():Observable<Furniture[]>
  {
    return this.httpclient.get<Furniture[]>(this.url8);
  }

  url10:string="http://localhost:8083/api/v4/cartService/"
  addToCartFurniture(list:Furniture,email:string):Observable<Furniture>
  {
    return this.httpclient.post<Furniture>(this.url10+`${email}`, list);
  }



  url11:string="http://localhost:8085/api/v5/searchFurniture/"
  getSearchedFurniture(itemToSearch:string):Observable<Furniture[]>
  {
       return this.httpclient.get<Furniture[]>(this.url11+itemToSearch);
  }

  url12:string="http://localhost:8083/api/v4/cartService/cart/"
  deleteFurnitureFromCart(userEmail:string,id:any):Observable<Furniture>
  {
    return this.httpclient.delete<Furniture>(this.url12+userEmail+"/"+id);
  }

  url13:string="http://localhost:8085/api/v5/furnitureH2LRating"
  getRatedFurniture():Observable<Furniture[]>
  {
    return this.httpclient.get<Furniture[]>(this.url13);
  }

}
