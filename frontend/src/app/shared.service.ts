import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { throwError } from 'rxjs/internal/observable/throwError';
import { catchError } from 'rxjs/internal/operators/catchError';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  url = 'http://localhost:8081';
  formations : any;
  constructor(private http: HttpClient) { }

  getAllFormations(){
    return this.http.get(this.url+'/formations/all');
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
}