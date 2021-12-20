import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../../registration.service';
import { Client } from '../../services/client';

@Component({
  selector: 'app-inscriptionclient',
  templateUrl: './inscriptionclient.component.html',
  styleUrls: ['./inscriptionclient.component.scss']
})
export class InscriptionclientComponent implements OnInit {
  
  client = new Client();

  constructor(private registrationservice: RegistrationService ,private router:Router) { }

  ngOnInit(): void {

  }
 
  saveClient(){

    this.registrationservice.inscription(this.client).subscribe(data=>{
      console.log(data)
    },error =>console.log(error));
    this.gotoconnexion();
  }

  gotoconnexion(){
    this.router.navigate(['/connexion']);
  }
  inscriptClient(){
    this.saveClient();
    console.log(this.client);
  }





}
