import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  token: any;
  email: any;
  isValid: boolean = false;
  count = 0;
  buttonText = "Next";
  Message: any;

  formationId: any;
  review = {
    "noteQualitePedagogique":"",
    "noteRythme":"",
    "noteSupportCours":"",
    "noteTP":"",
    "noteMaitriseSujet":""
  }

  constructor(private route: ActivatedRoute, private shared: SharedService) { }

  ngOnInit(): void {
  }
  
  onValid(){
    if(this.email.trim() !== '' &&
      this.email.match(this.shared.emailPattern)? true:false){
      this.route.queryParams.subscribe(params => {
        this.token = params['token'];
        this.formationId = params['formationId'];
        this.shared.validateToken(this.token, this.email).subscribe(result => {
          if(result){
            this.isValid = true;
            this.Message = "";
          }else{
            this.Message = 'Invalid email';
          }
        });
      });
    }else{
      this.Message = 'Invalid email';
    }
  }

  onNext(){
    if(this.count <3){
      this.count++;
    }else if(this.count == 3){
      this.count++;
      this.buttonText = "Send review";
    }else{
      this.shared.sendReview(this.review, this.formationId).subscribe(
        res=>{
          this.review = {
            "noteQualitePedagogique":"",
            "noteRythme":"",
            "noteSupportCours":"",
            "noteTP":"",
            "noteMaitriseSujet":""
          }
        },
        err=>{
          this.Message = err;
        }
      )
    }
  }

  validateFields(): boolean {
    return (
      this.review.noteMaitriseSujet.trim() !== '' &&
      this.review.noteQualitePedagogique.trim() !== '' &&
      this.review.noteRythme.trim() !== '' &&
      this.review.noteSupportCours.trim() !== '' &&
      this.review.noteTP.trim() !== ''
    );
  }

}
