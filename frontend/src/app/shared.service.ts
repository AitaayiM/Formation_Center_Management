import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { error } from 'jquery';
import { Observable } from 'rxjs/internal/Observable';
import { throwError } from 'rxjs/internal/observable/throwError';
import { catchError } from 'rxjs/internal/operators/catchError';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  url = 'http://localhost:8081';
  emailPattern = '^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$';
  formations : any;
  constructor(private http: HttpClient) { }

  getAllFormations(){
    return this.http.get(this.url+'/formations/all');
  }

  sendEmail(emails: any, formationId: any): Observable<any>{
    let emailsParams: String = "emails="+emails[0];
    for (let index = 1; index < emails.length; index++) {
      let element = emails[index];
      emailsParams = emailsParams+"&emails="+element;
    }

    return this.http.post(this.url+"/admin/sendemail?formationId="+formationId+"&"+emailsParams+"", {})
    .pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';
        switch (error.status) {
          case 200:
            errorMessage = 'The evaluation email has been sent successfully.';
            break;
          case 500:
            errorMessage = 'An error occurred while sending the review email.';
            break;
          default:
            errorMessage ='An error occurred while sending the review email.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  validateToken(token: string, email: String): Observable<boolean> {
    return this.http.post<boolean>(this.url+"/evaluation/validate?email="+email+"&token="+token+"", {});
  }

  sendReview(review: any, fomationId: any){
    return this.http.post(this.url+"/review?formationId="+fomationId+"", review)
    .pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';
        switch (error.status) {
          case 200:
            errorMessage = 'Review successfully added.';
            break;
          case 400:
            if (error.error) {
              // Récupérer les messages d'erreur renvoyés par le backend
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          case 500:
            errorMessage = 'An error occurred while adding the review.';
            break;
          default:
            errorMessage ='An error occurred while adding the review.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  addPlanification(planification: any): Observable<any>{
    return this.http.post(this.url+"/admin/planifications", planification)
    .pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';
        switch (error.status) {
          case 201:
            errorMessage = 'Planification successfully added.';
            break;
          case 400:
            if (error.error) {
              // Récupérer les messages d'erreur renvoyés par le backend
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          default:
            errorMessage ='An error occurred while adding the planification.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  getPlanificationsWithDetails(){
    return this.http.get(this.url+"/admin/planifications/details");
  }

  addToGroupe(groupe: any, individuIds: number[]): Observable<any>{
    let indParams: String = "individuIds="+individuIds[0];
    for (let index = 1; index < individuIds.length; index++) {
      let element = individuIds[index];
      indParams = indParams+"&individuIds="+element;
    }
    return this.http.post(this.url+"/admin/groupe/affecter-formateur?"+indParams+"", groupe)
    .pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';
        switch (error.status) {
          case 200:
            errorMessage = 'Group successfully added.';
            break;
          case 500:
            errorMessage = 'An error occurred while adding the group.';
            break;
          case 404:
            if (error.error) {
              // Récupérer les messages d'erreur renvoyés par le backend
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          default:
            errorMessage ='An error occurred while adding the group.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  getAllIndividus(){
    return this.http.get(this.url+"/inscriptions/allindividu");
  }

  addIndividu(formationId: any, individu: any): Observable<any>{
    return this.http.post(this.url+"/inscriptions/individu?formationId="+formationId+"", individu)
    .pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';
        switch (error.status) {
          case 201:
            errorMessage = 'Individual successfully added.';
            break;
          case 500:
            errorMessage = 'An error occurred while adding the individual.';
            break;
          case 400:
            if (error.error) {
              // Récupérer les messages d'erreur renvoyés par le backend
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          default:
            errorMessage ='An error occurred while adding the individual.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  addFormateurExterne(formateurExterne: any, formationId: any): Observable<any>{
    return this.http.post(this.url+"/formateurs/formations-interessees?formationId="+formationId+"", formateurExterne)
    .pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = 'An error occurred while adding the coach.';
        switch (error.status) {
          case 201:
            errorMessage = 'coach successfully added.';
            break;
          case 500:
            errorMessage = 'An error occurred while adding the coach.';
            break;
          case 400:
            if (error.error) {
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          case 401:
            errorMessage = 'Authentication failed';
            break;
          default:
            errorMessage ='An error occurred while adding the coach.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  addFormateur(formateur: any): Observable<any>{
    return this.http.post(this.url+"/auth/admin/signup?userType=FORMATEUR", formateur)
    .pipe(
      catchError((error: HttpErrorResponse)=>{
        let errorMessage = 'An error occurred while adding the coach.';
        switch (error.status) {
          case 200:
            errorMessage = 'coach successfully added.';
            break;
          case 400:
            if (error.error) {
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          default:
            errorMessage ='An error occurred while adding the coach.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  getAllFormateurs(){
    return this.http.get(this.url+"/formateurs/all");
  }

  addUser(user: any, userType: any): Observable<any>{
    return this.http.post(this.url+"/auth/admin/signup?userType="+userType+"", user)
    .pipe(
      catchError((error: HttpErrorResponse)=>{
        let errorMessage = 'An error occurred while adding the User.';
        switch (error.status) {
          case 200:
            errorMessage = 'User successfully added.';
            break;
          case 400:
            if (error.error) {
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          case 409:
            errorMessage = 'Username or Email already exists.';
            break;
          default:
            errorMessage ='An error occurred while adding the User.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  addNewFormation(formation: any){
    return this.http.post(this.url+"/formations/admin", formation)
    .pipe(
      catchError((error: HttpErrorResponse)=>{
        let errorMessage = 'An error occurred while adding the formation.';
        switch (error.status) {
          case 201:
            errorMessage = 'Formation successfully added';
            break;
          case 400:
            if (error.error) {
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          default:
            errorMessage ='An error occurred while adding the formation.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  getAllCompanies(){
    return this.http.get(this.url+'/admin/entreprises');
  }

  addNewCompany(company: any){
    return this.http.post(this.url+"/admin/entreprises", company)
    .pipe(
      catchError((error: HttpErrorResponse)=>{
        let errorMessage = 'An error occurred while adding the company.';
        switch (error.status) {
          case 201:
            errorMessage = 'Company successfully added';
            break;
          case 400:
            if (error.error) {
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          default:
            errorMessage ='An error occurred while adding the company.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }
}
