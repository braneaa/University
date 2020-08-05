import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.css']
})
export class ClientAddComponent implements OnInit {

  constructor(private clientService: ClientService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  goBack() : void{
    this.location.back();
  }

  save(id2, name, phoneNumber) :void{
    if (!this.isValid(id2, name, phoneNumber)) {
      console.log("all fields are required ");
      alert("all fields are required");
      return;
    }
    this.clientService.save(id2, name, phoneNumber)
      .subscribe(_ => this.goBack());

  }

  private isValid(id2, name, phonenNumber) {
    if (!id2 || !name || !phonenNumber) {
      console.log("all fields are required");
      return false;
    }
    return true;
  }

}
