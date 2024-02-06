import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddAdminAssistantComponent } from './add-admin-assistant/add-admin-assistant.component';
import { AddNewFormateurComponent } from './add-new-formateur/add-new-formateur.component';
import { AddNewFormationComponent } from './add-new-formation/add-new-formation.component';
import { AppRoutingModule } from './app-routing.module';
import { ARegistrationComponent } from './data-formation/a-registration/a-registration.component';
import { DataFormationComponent } from './data-formation/data-formation.component';
import { ListFormationComponent } from './list-formation/list-formation.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddFormateurComponent } from './data-formation/add-formateur/add-formateur.component';
import { AppComponent } from './app.component';
import { AddNewEntrepriseComponent } from './add-new-entreprise/add-new-entreprise.component';
import { ListCompanyComponent } from './list-company/list-company.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ListFormationComponent,
    DataFormationComponent,
    AddFormateurComponent,
    ARegistrationComponent,
    AddNewFormateurComponent,
    AddAdminAssistantComponent,
    AddNewFormationComponent,
    AddNewEntrepriseComponent,
    ListCompanyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
