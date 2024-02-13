import { Component, OnInit } from '@angular/core';
import { ListFormationComponent } from '../list-formation.component';
import { required } from '@rxweb/reactive-form-validators/decorators/required.decorator';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-planification',
  templateUrl: './planification.component.html',
  styleUrls: ['./planification.component.css']
})
export class PlanificationComponent implements OnInit {
  Message: any;
  formateurs: any;
  companies: any;
  planification = {
    "formation": {"id": ""},
    "formateur": {"id": ""},
    "entreprise": {"id": ""},
    "dateDebut": "",
    "dateFin": ""
  }

  constructor(public list: ListFormationComponent, private shared: SharedService) { }

  ngOnInit(): void {
    this.planification.formation.id = this.list.selectedFormation.id;
    this.shared.getAllFormateurs().subscribe(
      res=>{
        this.formateurs = res;
      },
      err=>{
        console.log(err);
      }
    );
    this.shared.getAllCompanies().subscribe(
      res=>{
        this.companies = res;
      },
      err=>{
        console.log(err);
      }
    );
  }

  addPlanification(){
    if(this.validateFields()){
    this.shared.addPlanification(this.planification).subscribe(
      res=>{
        this.planification = {
          "formation": {"id": ""},
          "formateur": {"id": ""},
          "entreprise": {"id": ""},
          "dateDebut": "",
          "dateFin": ""
        }
      },
      err=>{
        this.Message = err;
      }
    );
    }else{
      this.Message='Please fill out all fields with valid data.';
    }
  }

  validateFields(): boolean {
    return (
      this.planification.formation.id !== '' &&
      this.planification.formateur.id !== '' &&
      this.planification.entreprise.id !== '' &&
      this.planification.dateDebut.trim() !=='' &&
      this.planification.dateFin.trim() !== ''
    );
  }

  onClose(){
    this.list.planificationVisible=false;
    this.list.blur="";
    this.list.ngOnInit();
  }

}
