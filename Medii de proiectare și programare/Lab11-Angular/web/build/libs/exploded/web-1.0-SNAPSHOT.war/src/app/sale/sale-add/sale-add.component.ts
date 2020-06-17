import { Component, OnInit } from '@angular/core';
import {SaleService} from "../shared/sale.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-sale-add',
  templateUrl: './sale-add.component.html',
  styleUrls: ['./sale-add.component.css']
})
export class SaleAddComponent implements OnInit {

  constructor(private saleService: SaleService,
              private route:ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  goBack(): void{
    this.location.back();
  }

  save(bookId, clientId) : void{
    this.saleService.save(bookId,clientId).subscribe(_ => this.goBack());
  }


}
