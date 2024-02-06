import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';
import { ListCompanyComponent } from '../list-company.component';

@Component({
  selector: 'app-add-new-entreprise',
  templateUrl: './add-new-entreprise.component.html',
  styleUrls: ['./add-new-entreprise.component.css']
})
export class AddNewEntrepriseComponent implements OnInit {
  Message: any;
  entreprise = {
    nom: '',
    adresse: '',
    telephone: '',
    url: '',
    email: ''
  };
  constructor(private list: ListCompanyComponent, private sharedService: SharedService) { }

  ngOnInit(): void {
  }
  addCompany() {
    if (this.validateFields()) {
      this.sharedService.addNewCompany(this.entreprise).subscribe(
        (res: any) => {
          console.log('Entreprise added successfully:', res);
          this.Message = 'Entreprise added successfully';
          this.clearFields();
        },
        (err: any) => {
          this.Message = err;
        }
      );
    } else {
      this.Message = 'Validation failed. Please fill all required fields.';
    }
  }

  validateFields(): boolean {
    return (
      this.entreprise.nom.trim() !== '' &&
      this.entreprise.adresse.trim() !== '' &&
      this.entreprise.email.trim() !== '' &&
      this.isValidEmail(this.entreprise.email)
    );
  }

  isValidEmail(email: string): boolean {
    return email.match(this.sharedService.emailPattern)? true:false;
  }

  clearFields() {
    this.entreprise = {
      nom: '',
      adresse: '',
      telephone: '',
      url: '',
      email: ''
    };
  }

  onClose(){
    this.list.formComapanyVisible = false;
  }
}
