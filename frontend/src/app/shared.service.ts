import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { throwError } from 'rxjs/internal/observable/throwError';
import { catchError } from 'rxjs/internal/operators/catchError';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  url = 'http://localhost:8081';
  emailPattern = '^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$';
  formations: any[] = [];
  filteredFormations: any[] = [];
  private isAuthenticated = false;
  private isAdmin = false;
  private isAssistant = false;
  private isFormateur = false;
  constructor(private http: HttpClient, private router: Router) { }

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

  login(loginDTO: any){
    return this.http.post(this.url+"/auth/signin", loginDTO)
    .pipe(
      
      catchError((error: HttpErrorResponse)=>{
        let errorMessage = 'An error occurred while loging.';
        switch (error.status) {
          case 200:
            errorMessage = 'User signed-in successfully!';
            const token = JSON.parse(JSON.stringify(Object.values(error.error)));
            this.refresh((token+"").substring(16));
            this.router.navigate(['/home']);
            break;
          case 400:
            if (error.error) {
              errorMessage = Object.values(error.error).join('\n');
            }
            break;
          case 401:
            errorMessage = 'Authentication failed.';
            break;
          default:
            errorMessage ='An error occurred while loging.';
            break;
        }
        return throwError(errorMessage);
      })
    );
  }

  refresh(token: any){
    this.getRole(token).subscribe(res => {
    },
    err=>{
      this.isAuthenticated = true;
      switch (err+"") {
        case "ADMIN":
          this.isAdmin = true;
          break;
        case "FORMATEUR":
          this.isFormateur = true;
          break;
        case "ASSISTANT":
          this.isAssistant = true;
          break;
        default:
          break;
      }
    }
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.isAuthenticated = false;
    this.isAdmin = false;
    this.isAssistant = false;
    this.isFormateur = false;
  }

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }

  isAdminUser(): boolean {
    return this.isAdmin;
  }

  isFormateurUser(): boolean {
    return this.isFormateur;
  }

  isAssistantUser(): boolean {
    return this.isAssistant;
  }

  getRole(token: any): Observable<String>{
    return this.http.get<String>(this.url+"/auth/role?token="+token, {})
    .pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';
        errorMessage = (JSON.parse(JSON.stringify(Object.values(error.error).join('\n')))+"").substring(87);
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
