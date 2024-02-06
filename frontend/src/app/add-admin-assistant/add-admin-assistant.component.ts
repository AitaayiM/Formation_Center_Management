import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-add-admin-assistant',
  templateUrl: './add-admin-assistant.component.html',
  styleUrls: ['./add-admin-assistant.component.css']
})
export class AddAdminAssistantComponent implements OnInit {
  Message: any;
  userType: any;
  user = {
    "name":"",
    "username": "",
    "email": "",
    "password": ""
  }

  constructor(public shared: SharedService) { }

  ngOnInit(): void {
  }

  addUser(){
    if (this.validateFields()) {
      this.shared.addUser(this.user, this.userType).subscribe(
        res=>{
          this.user = {
            "name":"",
            "username": "",
            "email": "",
            "password": ""
          }
        },
        err=>{
          this.Message=err;
        }
      )
    }else{
      this.Message='Please fill out all fields with valid data.';
    }
  }
  validateFields(): boolean {
    return (
      this.user.name.trim() !== '' &&
      this.user.username.trim() !== '' &&
      this.user.email.trim() !== '' &&
      this.user.email.match(this.shared.emailPattern)? true:false &&
      this.user.password.trim() !== '' &&
      this.userType.trim() !== ''
    );
  }
}
