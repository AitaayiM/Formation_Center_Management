import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  Message: any;
  login = {
    "usernameOrEmail": "",
    "password": ""
  }
  constructor(private shared: SharedService) { }

  ngOnInit(): void {
  }

  onLogin(){
    if(this.validateFields()){
      this.shared.login(this.login).subscribe(
        res=>{
          this.login = {
            "usernameOrEmail": "",
            "password": ""
          }
        },
        err=>{
          this.Message = err;
        }
      );
    }else{
      this.Message = 'Validation failed. Please fill all required fields.';
    }
  }

  validateFields(): boolean {
    return (
      this.login.usernameOrEmail.trim() !== '' &&
      this.login.password.trim() !== '' &&
      this.login.password.length > 7
    );
  }

}
