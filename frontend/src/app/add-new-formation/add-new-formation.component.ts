import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-add-new-formation',
  templateUrl: './add-new-formation.component.html',
  styleUrls: ['./add-new-formation.component.css']
})
export class AddNewFormationComponent implements OnInit {

  Message: any;
  errorObjectif: any;
  errorSection: any;
  errorCours: any;

  formation = {
    "titre": "",
    "nombreHeures": "",
    "cout": "",
    "description": "",
    "categorie": "",
    "ville": "",
    "objectifs": [
      {    
      "description":""
      }
    ],
    "sections": [
      {
        "title": "",
        "cours":[
          {
              "title":""
          }
        ]
      }
    ]
  }

  objectifs = [
    {    
    "description":""
    }
  ]

  newObjectif = {    
    "description":""
  }

  sections = [
    {
      "title": "",
      "cours": [
        {
          "title": ""
        }
      ]
    }
  ]

  newSection = {
    "title": "",
    "cours": [
      {
        "title": ""
      }
    ]
  }

  cours = [
    {
      "title": ""
    }
  ]

  newCours = {
    "title": ""
  }

  constructor(private shared: SharedService) { }

  ngOnInit(): void {
  }

  addFormation() {
    this.formation.objectifs = this.formation.objectifs.filter(o => o.description.trim() !== '');
    this.formation.sections = this.formation.sections.filter(s => s.cours.filter(c => c.title.trim() !== ''));
    this.formation.sections = this.formation.sections.filter(s => s.title.trim() !== '');

    if (this.validateFields()) {
      this.shared.addNewFormation(this.formation).subscribe(
        res => {
          this.formation = {
            "titre": "",
            "nombreHeures": "",
            "cout": "",
            "description": "",
            "categorie": "",
            "ville": "",
            "objectifs": [
              {    
                "description":""
              }
            ],
            "sections": [
              {
                "title": "",
                "cours":[
                  {
                      "title":""
                  }
                ]
              }
            ]
          }
        },
        err => {
          this.Message = err;
        }
      );
    } else {
      this.Message = 'Validation failed. Please fill all required fields.';
    }
  }

  addSection() {
    if (this.newSection.title.trim()) {
      this.formation.sections.push({
        "title": this.newSection.title.trim(),
        "cours": []
      });
      this.newSection.title = '';
    } else {
      this.errorSection = 'Please enter a section title.';
    }
  }
  
  addObjectif() {
    if (this.newObjectif.description.trim()) {
      this.formation.objectifs.push({
        "description": this.newObjectif.description.trim()
      });
      this.newObjectif.description = '';
    } else {
      this.errorObjectif= 'Please enter an objective description.';
    }
  }
  
  addCours(sectionIndex: number) {
    const section = this.formation.sections[sectionIndex];
    if (this.newCours.title.trim()) {
      section.cours.push({
        "title": this.newCours.title.trim()
      });
      this.cours.push({"title":this.newCours.title.trim()})
      this.newCours.title = '';
    } else {
      this.errorCours = 'Please enter a course title.';
    }
  }
  
  

  validateFields(): boolean {
    return (
      this.formation.titre.trim() !== '' &&
      this.formation.nombreHeures.trim() !== '' &&
      this.formation.cout.trim() !== '' &&
      this.formation.description.trim() !== '' &&
      this.formation.categorie.trim() !== '' &&
      this.formation.ville.trim() !== '' &&
      this.formation.objectifs.length > 0 &&
      this.formation.objectifs.every(c => c.description.trim() !== '') &&
      this.formation.sections.length > 0 &&
      this.formation.sections.every(r => r.title.trim() !== '' && r.cours.every(c=> c.title.trim() !== ''))
    );
  }
}
