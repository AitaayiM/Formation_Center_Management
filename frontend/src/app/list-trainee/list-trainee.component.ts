import { Component, OnInit } from '@angular/core';
import 'datatables.net';
import { SharedService } from '../shared.service';
import { tick } from '@angular/core/testing';

@Component({
  selector: 'app-list-trainee',
  templateUrl: './list-trainee.component.html',
  styleUrls: ['./list-trainee.component.css']
})
export class ListTraineeComponent implements OnInit {

  constructor(public shared: SharedService) { }
  individus:  any;
  plans: any;
  Message: any;
  SelectedTrainee: number[] = [];
  group = {
    "nom": "",
    "formateur":{"id":""},
    "formation":{"id":""}
  }

  ngOnInit(): void {
    this.shared.getAllIndividus().subscribe(
      res=>{
        this.individus = res;
      },
      err=>{
        console.log(err);
      }
    )
    this.shared.getPlanificationsWithDetails().subscribe(
      res=>{
        this.plans= res;
      },
      err=>{
        console.log(err);
      }
    )
  }

  addToGroup() {

    if (!this.validateFields()) {
        this.Message = 'Champs non valides.';
        return;
    }
    this.shared.addToGroupe(this.group, this.SelectedTrainee).subscribe(
        res => {
            console.log('Ajout au groupe réussi:', res);
            // Réinitialiser les valeurs du groupe après l'ajout
            this.group = {
                "nom": "",
                "formateur": { "id": "" },
                "formation": { "id": "" }
            };
            // Réinitialiser les identifiants d'individus sélectionnés
            this.SelectedTrainee = [];
        },
        err => {
            this.Message = err;
        }
    );
}

isSelected(formationId: number): boolean {
    return this.SelectedTrainee.includes(formationId);
}

toggleSelection(formationId: number) {
    if (this.isSelected(formationId)) {
        this.SelectedTrainee = this.SelectedTrainee.filter(id => id !== formationId);
    } else {
        this.SelectedTrainee.push(formationId);
    }
}

validateFields(): boolean {
    return (
        this.group.nom.trim() !== '' &&
        this.group.formateur.id !== '' &&
        this.group.formation.id !== '' &&
        this.SelectedTrainee.length > 0
    );
}
}
