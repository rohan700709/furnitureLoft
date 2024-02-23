import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Credential } from 'src/app/credential';
import { User } from '../../User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  
  constructor(private httpClient : HttpClient) { }
  private registerURL : string = "http://localhost:8081/api/v2/user";  //userService8081
  private loginURL : string = "http://localhost:8082/api/v1/login";  //Auth8082

  


  register(user : User) : Observable<Object>{
    return this.httpClient.post<User>(this.registerURL, user);
  }
 

  generateToken(credentials : any): Observable<Object>{
    alert(credentials.userEmail);
    alert(credentials.password);
    return this.httpClient.post<any>(this.loginURL, credentials);
  } 

  loginUser(token: any){   
    localStorage.setItem("token", token)
    return true;
  }


  logout(){
    localStorage.removeItem('token')
    return true;
  }


}

