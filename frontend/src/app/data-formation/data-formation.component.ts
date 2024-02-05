import { Component, OnInit } from '@angular/core';
import { ListFormationComponent } from '../list-formation/list-formation.component';

@Component({
  selector: 'app-data-formation',
  templateUrl: './data-formation.component.html',
  styleUrls: ['./data-formation.component.css']
})
export class DataFormationComponent implements OnInit {

  coachRegistration: boolean = false;
  traineeRegistration: Boolean = false;

  constructor(public list: ListFormationComponent) { }

  ngOnInit(): void {
  }

  onClose(){
    this.list.dataFormationComponent=false;
    this.coachRegistration=false;
    this.traineeRegistration=false;
    this.list.blur="";
  }

  onCoachClick(){
    this.coachRegistration = true;
    this.traineeRegistration = false;
  }

  onTraineeClick(){
    this.traineeRegistration = true;
    this.coachRegistration = false;
  }

}
