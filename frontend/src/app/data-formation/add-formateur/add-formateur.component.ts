import { Component, OnInit } from '@angular/core';
import { ListFormationComponent } from '../../list-formation/list-formation.component';
import { SharedService } from '../../shared.service';

@Component({
  selector: 'app-add-formateur',
  templateUrl: './add-formateur.component.html',
  styleUrls: ['./add-formateur.component.css']
})
export class AddFormateurComponent implements OnInit {

  Message: any;
  errorSkill: any;

  newCompetence = {
    "domain": "",
    "level": ""
  };

  competences = [
      {
        "domain": "",
        "level": ""
      }
  ];

  coach = {
    "usernameOrEmail": "",
    "password": "",
    "competences": [
      {
        "domain": "",
        "level": ""
      }
    ]
  }

  constructor(public shared: SharedService, public list: ListFormationComponent) { }

  ngOnInit(): void {
  }

  addCoach(){
    this.coach.competences = this.coach.competences.filter(c => c.domain.trim() !== '' && c.level.trim() !== '');
    if(this.validateFields()){
      this.shared.addFormateurExterne(this.coach, this.list.selectedFormation?.id).subscribe(
        res=>{
          this.coach = {
            "usernameOrEmail": "",
            "password": "",
            "competences": [
              {
                "domain": "",
                "level": ""
              }
            ]
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

  addSkill(){
    if (this.newCompetence.domain.trim() && this.newCompetence.level.trim() != "Level") {
      this.competences.push({...this.newCompetence});
      this.coach.competences.push({...this.newCompetence});
      this.newCompetence = {
        "domain":"",
        "level":""
      }
    }else {
      this.errorSkill = 'Please complete the Domain and Level fields.';
    }
  }

  validateFields(): boolean {
    return (
      this.coach.usernameOrEmail.trim() !== '' &&
      this.coach.password.trim() !== '' &&
      this.coach.competences.length > 0 &&
      this.coach.competences.every(c => c.domain.trim() !== '' && c.level.trim() !== '')
    );
  }

}
