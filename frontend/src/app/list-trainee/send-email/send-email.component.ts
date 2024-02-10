import { Component, OnInit } from '@angular/core';
import { ListTraineeComponent } from '../list-trainee.component';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-send-email',
  templateUrl: './send-email.component.html',
  styleUrls: ['./send-email.component.css']
})
export class SendEmailComponent implements OnInit {
  emailsList: string[] = [];
  formation: any;
  Message: any;

  constructor(private shared: SharedService, public listT: ListTraineeComponent) { }

  ngOnInit(): void {
  }

  sendEmail(){
    this.collectExpiredEmails(this.emailsList);
    this.shared.sendEmail(this.emailsList, this.formation).subscribe(
      res=>{
        this.Message = res;
      },
      err=>{
        this.Message = err;
      }
    );
  }

  isDateExpired(date: string): boolean {
    const currentDate = new Date();
    const endDate = new Date(date);
    return endDate < currentDate;
  }

  collectExpiredEmails(listT: any): void {
    this.listT.individus.forEach((individu: any) => {
      if (this.isDateExpired(individu?.formations?.planifications?.dateFin)) {
        this.emailsList.push(individu?.email);
        for (let formation of individu?.formations) {
          this.formation = formation?.id;
        }
      }
    });
  }

  onClose(){
    this.listT.sendEmailVisible = false;
    this.listT.blur="";
  }
}
