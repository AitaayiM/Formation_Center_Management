import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddNewFormateurComponent } from './add-new-formateur/add-new-formateur.component';
import { ListFormationComponent } from './list-formation/list-formation.component';
import { AddAdminAssistantComponent } from './add-admin-assistant/add-admin-assistant.component';
import { AddNewFormationComponent } from './add-new-formation/add-new-formation.component';
import { ListCompanyComponent } from './list-company/list-company.component';
import { DataFormationComponent } from './list-formation/data-formation/data-formation.component';
import { PlanificationComponent } from './list-formation/planification/planification.component';
import { ListTraineeComponent } from './list-trainee/list-trainee.component';
import { ReviewComponent } from './review/review.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path:'', redirectTo:'/home', pathMatch:'full'},
  {path:'home', component: ListFormationComponent},
  {path:'addNewCoach', component: AddNewFormateurComponent},
  {path:'addAdminAssistant', component: AddAdminAssistantComponent},
  {path:'addNewFormation', component: AddNewFormationComponent},
  {path: 'listCompany', component: ListCompanyComponent},
  {path: 'listTrainee', component: ListTraineeComponent},
  {path: 'review', component: ReviewComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
