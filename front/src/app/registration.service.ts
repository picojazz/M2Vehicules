import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from './services/client';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private baseUrl="http://localhost:9091/m2vehicule/api/client/*";


  constructor(private http:HttpClient) { }

 
  public inscription(client:Client):Observable<Object>{
    return this.http.post(`${this.baseUrl}`, client);

    
  }
}
 