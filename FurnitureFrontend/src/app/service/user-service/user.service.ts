import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userURL : string = "http://localhost:8084/api/v1/user/";
  constructor(private httpClient: HttpClient) { }

  getUser(userEmail : any) : Observable<User>{
    return this.httpClient.get<User>(this.userURL+`${userEmail}`);
  }

  updateUser(user : User, userEmail : any) : Observable<Object>{
    return this.httpClient.put(this.userURL+`${userEmail}`, user);
  }

  deleteUser(userEmail : any) : Observable<Object>{
    return this.httpClient.delete(this.userURL+`${userEmail}`);
  }

  getAllUser() : Observable<User[]>{
    return this.httpClient.get<User[]>(this.userURL+"all");
  }

  getUserCount() : Observable<number>{
    return this.httpClient.get<number>(this.userURL+"count");
  }
}
