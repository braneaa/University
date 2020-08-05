import { Component, OnInit } from '@angular/core';
import {Client} from "../shared/client.model";
import{ClientService} from "../shared/client.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  errorMessage: string;
  clients: Array<Client>;
  selectedClient: Client;
  constructor(private clientService: ClientService, private router: Router) { }

  ngOnInit(): void {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients()
      .subscribe(
        clients => this.clients = clients,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
  }

  delete(): void{
    this.clientService.delete(this.selectedClient).subscribe();
    window.location.reload();
  }

  gotoDetail(): void {
    this.router.navigate(['/client/detail', this.selectedClient.id]);
  }

  goToAdd():void{
    this.router.navigate(['client/add']);
  }

}
