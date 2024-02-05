import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddNewFormateurComponent } from './add-new-formateur/add-new-formateur.component';
import { ListFormationComponent } from './list-formation/list-formation.component';
import { AddAdminAssistantComponent } from './add-admin-assistant/add-admin-assistant.component';

const routes: Routes = [
  {path:'', redirectTo:'/home', pathMatch:'full'},
  {path:'home', component: ListFormationComponent},
  {path:'addNewCoach', component: AddNewFormateurComponent},
  {path:'addAdminAssistant', component: AddAdminAssistantComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
