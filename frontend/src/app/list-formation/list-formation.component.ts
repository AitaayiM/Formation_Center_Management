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
  ActualFormations: any;
  date: string[] = [];
  city: string[] = [];
  category: string[] = [];
  dataFormationComponent: boolean = false;
  planificationVisible: boolean = false;
  blur: String = "";

  ngOnInit(): void {
    this.displayFormations();
  }

  displayFormations(){
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
          if (this.date.length ==0 && this.city.length ==0 && this.category.length ==0) {
            this.formationFilter();
          }
          this.ActualFormations = JSON.parse(JSON.stringify(this.shared.formations));
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

  onPlanClick(formation: any): void{
    this.planificationVisible = true;
    this.blur="filter : blur(50px)";
    this.selectedFormation= formation;
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }

  formationFilter() {
    const uniqueCities = new Set<string>();
    const uniqueCategories = new Set<string>();
    const uniqueDates = new Set<string>();
  
    this.shared.formations.forEach((formation: any )=> {
      uniqueCities.add(formation.ville);
      uniqueCategories.add(formation.categorie);
  
      formation.planifications.forEach((planification: any)=> {
        const planificationDate = new Date(planification?.dateDebut).toLocaleDateString();
        uniqueDates.add(planificationDate);
        console.log(new Date(planification?.dateDebut).toString());
      });
    });
  
    // Convertir les ensembles en tableaux pour les utiliser dans le template
    this.city = Array.from(uniqueCities);
    this.category = Array.from(uniqueCategories);
    this.date = Array.from(uniqueDates);
  }
  

  // Méthode pour filtrer les formations par date
  filterByDate(date: string): void {
    // Réinitialiser la liste des formations
    this.shared.filteredFormations = [];
    
    // Filtrer les formations selon la date spécifiée
    this.shared.formations.forEach((formation: any) => {
      formation.planifications.forEach((planification: any) => {
        const planificationDate = new Date(planification.dateDebut).toLocaleDateString();
        if (planificationDate === date) {
          this.shared.filteredFormations.push(formation);
        }
      });
    });
    this.ActualFormations = JSON.parse(JSON.stringify(this.shared.filteredFormations));
  }

  // Méthode pour filtrer les formations par ville
  filterByCity(city: string): void {
    this.shared.filteredFormations = this.shared.formations.filter((formation: any) => {
      return formation.ville === city;
    });
    this.ActualFormations = JSON.parse(JSON.stringify(this.shared.filteredFormations));
  }

  // Méthode pour filtrer les formations par catégorie
  filterByCategory(category: string): void {
    this.shared.filteredFormations = this.shared.formations.filter((formation: any) => {
      return formation.categorie === category;
    });
    this.ActualFormations = JSON.parse(JSON.stringify(this.shared.filteredFormations));
  }

}
