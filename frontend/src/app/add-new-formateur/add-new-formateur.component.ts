import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';
import { ListFormationComponent } from '../list-formation/list-formation.component';

@Component({
  selector: 'app-add-new-formateur',
  templateUrl: './add-new-formateur.component.html',
  styleUrls: ['./add-new-formateur.component.css']
})
export class AddNewFormateurComponent implements OnInit {

  Message: any;
  errorSkill: any;
  errorNote: any;

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

  newRemarque = {
    "content": ""
  };

  remarques = [
      {
        "content": ""
      }
  ];

  coach = {
    "name":"",
    "username": "",
    "email": "",
    "password": "",
    "competences": [
      {
        "domain": "",
        "level": ""
      }
    ],
    "remarques":[
      {
        "content":""
      }
    ]
  }

  constructor(public shared: SharedService) { }

  ngOnInit(): void {
  }

  addCoach(){
    this.coach.competences = this.coach.competences.filter(c => c.domain.trim() !== '' && c.level.trim() !== '');
    this.coach.remarques = this.coach.remarques.filter(r => r.content.trim() !== '');
    if (this.validateFields()) {
      this.shared.addFormateur(this.coach).subscribe(
        res=>{
          this.coach = {
            "name":"",
            "username": "",
            "email": "",
            "password": "",
            "competences": [
              {
                "domain": "",
                "level": ""
              }
            ],
            "remarques":[
              {
                "content":""
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

  addNote(){
    if (this.newRemarque.content.trim()) {
      this.remarques.push({...this.newRemarque});
      this.coach.remarques.push({...this.newRemarque});
      this.newRemarque = {
        "content":""
      }
    }else {
      this.errorNote = 'Please complete the content field.';
    }
  }

  validateFields(): boolean {
    return (
      this.coach.name.trim() !== '' &&
      this.coach.username.trim() !== '' &&
      this.coach.email.trim() !== '' &&
      this.coach.password.trim() !== '' &&
      this.coach.competences.length > 0 &&
      this.coach.competences.every(c => c.domain.trim() !== '' && c.level.trim() !== '') &&
      this.coach.remarques.length > 0 &&
      this.coach.remarques.every(r => r.content.trim() !== '')
    );
  }

}
