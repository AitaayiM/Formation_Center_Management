import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-list-company',
  templateUrl: './list-company.component.html',
  styleUrls: ['./list-company.component.css']
})
export class ListCompanyComponent implements OnInit {
  companies: any;
  formComapanyVisible: boolean = false;

  constructor(private shared: SharedService) { }

  ngOnInit(): void {
    this.shared.getAllCompanies().subscribe(
      (res: any) => {
        this.companies = res; // Affecter les données des entreprises à la variable companies
      },
      (err: any) => {
        console.error('Error loading companies:', err);
      }
    );
  }

  onClickAdd(){
    this.formComapanyVisible = true;
  }

}
