import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ListFormationComponent } from './list-formation/list-formation.component';
import { DataFormationComponent } from './data-formation/data-formation.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AddFormateurComponent } from './add-formateur/add-formateur.component';
import { ARegistrationComponent } from './a-registration/a-registration.component';
import { AddNewFormateurComponent } from './add-new-formateur/add-new-formateur.component';
import { AddAdminAssistantComponent } from './add-admin-assistant/add-admin-assistant.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ListFormationComponent,
    DataFormationComponent,
    AddFormateurComponent,
    ARegistrationComponent,
    AddNewFormateurComponent,
    AddAdminAssistantComponent
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
