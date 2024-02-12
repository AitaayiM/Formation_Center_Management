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
  }

}
