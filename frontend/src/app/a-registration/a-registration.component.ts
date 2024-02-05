import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';
import { ListFormationComponent } from '../list-formation/list-formation.component';

@Component({
  selector: 'app-a-registration',
  templateUrl: './a-registration.component.html',
  styleUrls: ['./a-registration.component.css']
})
export class ARegistrationComponent implements OnInit {

  Message: any;
  individu = {
    "nom": "",
    "prenom": "",
    "dateNaissance": "",
    "ville": "",
    "email": "",
    "telephone": ""
  }

  constructor(public shared: SharedService, public list: ListFormationComponent) { }

  ngOnInit(): void {
  }

  addTrainee(){
    if (this.validateFields()) {
      this.shared.addIndividu(this.list.selectedFormation?.id, this.individu).subscribe(
        res=>{
          this.individu = {
            "nom": "",
            "prenom": "",
            "dateNaissance": "",
            "ville": "",
            "email": "",
            "telephone": ""
        };
        },
        err=>{
          this.Message=err;
        }
      );
    }else{
      this.Message='Please fill out all fields.';
    }
  }

  validateFields(): boolean {
    return (
      this.individu.nom.trim() !== '' &&
      this.individu.prenom.trim() !== '' &&
      this.individu.dateNaissance.trim() !== '' &&
      this.individu.ville.trim() !=='' &&
      this.individu.email !== '' &&
      this.individu.telephone !== ''
    );
  }
}
