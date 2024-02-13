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
import { AuthGuardGuard } from './auth-guard.guard';

const routes: Routes = [
  {path:'', redirectTo:'/home', pathMatch:'full'},
  {path:'home', component: ListFormationComponent, canActivate: [AuthGuardGuard]},
  {path:'addNewCoach', component: AddNewFormateurComponent, canActivate: [AuthGuardGuard]},
  {path:'addAdminAssistant', component: AddAdminAssistantComponent, canActivate: [AuthGuardGuard]},
  {path:'addNewFormation', component: AddNewFormationComponent, canActivate: [AuthGuardGuard]},
  {path: 'listCompany', component: ListCompanyComponent, canActivate: [AuthGuardGuard]},
  {path: 'listTrainee', component: ListTraineeComponent, canActivate: [AuthGuardGuard]},
  {path: 'review', component: ReviewComponent, canActivate: [AuthGuardGuard]},
  {path: 'login', component: LoginComponent, canActivate: [AuthGuardGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
