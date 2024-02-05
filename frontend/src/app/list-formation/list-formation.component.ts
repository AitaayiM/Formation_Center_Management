import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-list-formation',
  templateUrl: './list-formation.component.html',
  styleUrls: ['./list-formation.component.css']
})
export class ListFormationComponent implements OnInit {
  constructor(public shared: SharedService) {}

  selectedFormation: any;
  dataFormationComponent: boolean = false;
  blur: String = "";

  ngOnInit(): void {
    this.shared.getAllFormations().subscribe(
      (res: any) => {
        this.shared.formations = res.map((formation: any) => {
              return {
                  ...formation,
                  planifications: formation.planifications?.map((planification: any) => {
                      return {
                          ...planification,
                          dateDebut: new Date(planification?.dateDebut)
                      };
                  })
              };
          });
          console.log("Formations chargées avec succès :", this.shared.formations);
      },
      err => {
          console.log("Erreur lors du chargement des formations :", err);
      }
    );

  }

  onSeeMoreClick(formation: any): void {
    this.dataFormationComponent = true;
    this.blur="filter : blur(50px)";
    this.selectedFormation= formation;
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }
}
