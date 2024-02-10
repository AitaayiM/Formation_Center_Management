import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddAdminAssistantComponent } from './add-admin-assistant/add-admin-assistant.component';
import { AddNewFormateurComponent } from './add-new-formateur/add-new-formateur.component';
import { AddNewFormationComponent } from './add-new-formation/add-new-formation.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddNewEntrepriseComponent } from './list-company/add-new-entreprise/add-new-entreprise.component';
import { ListCompanyComponent } from './list-company/list-company.component';
import { ARegistrationComponent } from './list-formation/data-formation/a-registration/a-registration.component';
import { AddFormateurComponent } from './list-formation/data-formation/add-formateur/add-formateur.component';
import { DataFormationComponent } from './list-formation/data-formation/data-formation.component';
import { ListFormationComponent } from './list-formation/list-formation.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PlanificationComponent } from './list-formation/planification/planification.component';
import { ListTraineeComponent } from './list-trainee/list-trainee.component';
import { SendEmailComponent } from './list-trainee/send-email/send-email.component';
import { ReviewComponent } from './review/review.component';

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
    ListCompanyComponent,
    PlanificationComponent,
    ListTraineeComponent,
    SendEmailComponent,
    ReviewComponent
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
