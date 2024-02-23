import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { error } from 'protractor';
import { AuthService } from '../service/auth-service/auth.service';
import { SnackbarService } from '../service/snackbar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // successMessage: string = "";
  loginForm!: FormGroup;
  userRole: string = "";
  helper = new JwtHelperService();
  email: string = "";

  alertify: any;
  constructor( private fb: FormBuilder, private route: ActivatedRoute, private authService: AuthService,
     private router: Router,private snackBarService:SnackbarService) { }

  ngOnInit(): void {

    this.loginForm = this.fb.group({
      'userEmail': new FormControl(null, [Validators.required, Validators.email]),
      'userPassword': new FormControl(null, [Validators.required])
    })
  }


  credentials = {

    userEmail: '',
    userPassword: ''
  }


  // loginSuccess() {
  // }
  onSubmit() {

    this.credentials.userEmail = this.loginForm.value.userEmail;
    this.credentials.userPassword = this.loginForm.value.userPassword;
    alert(this.credentials.userEmail);
    alert(this.credentials.userPassword);
    sessionStorage.setItem('userEmail', this.credentials.userEmail);
    alert("Before token generation");
    this.authService.generateToken(this.credentials).subscribe((response: any) => {
      alert("After calling authSer generation method");
      console.log("*******************************************************************************");
      alert(response);
      console.log("*******************************************************************************");
      if (response.token) {
        // alert('Login successful');
        // this.loginSuccess();
        console.log(response.token);
        this.authService.loginUser(response.token);
        const decodedToken = this.helper.decodeToken(response.token);
        this.userRole = decodedToken.sub;
        console.log(this.userRole);
        this.email = decodedToken.jti;     // for user email
        console.log(this.email);
        if (this.userRole == 'User') {
          console.log("In user page");
          this.snackBarService.showSnackbar();
          // this.router.navigate(['']);

          // window.location.reload();
          this.delayedHomeRouting();

          // this.snackBarService.showSnackbar();
        }
        else {
          console.log("admin");
          this.router.navigate(['admin-profile'])
            .then(() => {
              this.snackBarService.showSnackbar();
              // window.location.reload();
            })
        }
      }
    },
      error => {
        alert("Please check your credential");
        this.ngOnInit();
      })
  };

  // showSnackbar() {
  //   var snackbar = document.getElementById("snackbar");
  //   snackbar.className = "show";
  //   setTimeout(function(){ snackbar.className = snackbar.className.replace("show", "");},30000);
  // }
    delayedHomeRouting()
    {
      setTimeout(()=>{
        this.router.navigate(['']);
      },8000);
    };

}
