import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";

import { HttpClientModule } from '@angular/common/http';

import {Client} from "./client.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable()
export class ClientService {

  private clientsURL ='http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) { }

  getClients():Observable<Client[]>{
    return this.httpClient.get<Array<Client>>(this.clientsURL);
  }

  getClient(id:number): Observable<Client>{
    return this.getClients().pipe(map(clients => clients.find(client => client.id === id)));
  }

  update(client): Observable<Client>{
    const url = `${this.clientsURL}/${client.id}`;
    return this.httpClient.put<Client>(url,client);
  }

  delete(client) : Observable<{}>{
    const url = `${this.clientsURL}/${client.id}`;
    return this.httpClient.delete(url,client);
  }

  save(id2: number, name: string, phoneNumber: string) : Observable<Client>{
    let client = {id2, name, phoneNumber};
    return this.httpClient.post<Client>(this.clientsURL,client)
  }

}
