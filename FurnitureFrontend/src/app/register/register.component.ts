import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/User';
import { AuthService } from '../service/auth-service/auth.service';
import { SnackbarService } from '../service/snackbar.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  successMessage: string = ""

  // regForm!: FormGroup

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router,private snackService:SnackbarService) { }

  ngOnInit(): void {
    

  }

  regForm = this.fb.group({
    'userName': new FormControl(null, Validators.required),
    // 'mobileNumber': new FormControl(null, [Validators.required, Validators.min(1000000000), Validators.max(9999999999)]),
    'mobileNumber': new FormControl(null, [Validators.required, Validators.pattern(/^[789]\d{9,9}$/)]),
    'userEmail': new FormControl(null, [Validators.required, Validators.email]),
    'userPassword': new FormControl(null, [Validators.required, Validators.minLength(8)])
  })

  // registerSuccess() {
    
  // }

  onSubmit() {
    // alert(this.regForm.value.userPassword);
    // alert(this.regForm.value.userEmail);
    // alert(this.regForm.value.userName);
    // alert(this.regForm.value.mobileNumber);
    this.snackService.showSnackbar();     
    this.authService.register(this.regForm.value).subscribe(
      data => {
        console.log(data);
        // this.registerSuccess();
        // alert('Successfully Registered');
        // this.router.navigate(['auth/login']);
        location.reload();
        this.ngOnInit();
      }
    );

  }

}
