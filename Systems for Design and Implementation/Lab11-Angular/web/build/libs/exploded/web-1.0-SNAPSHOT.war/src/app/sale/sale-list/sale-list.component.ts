import { Component, OnInit } from '@angular/core';
import {Client} from "../../client/shared/client.model";
import {Sale} from "../shared/sale.model";
import {SaleService} from "../shared/sale.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sale-list',
  templateUrl: './sale-list.component.html',
  styleUrls: ['./sale-list.component.css']
})
export class SaleListComponent implements OnInit {

  errorMessage: string;
  sales: Array<Sale>;
  selectedSale: Sale;

  constructor(private saleService: SaleService, private router:Router) { }

  ngOnInit(): void {
    this.getSales();
  }

  private getSales() {
    this.saleService.getSales().subscribe(sales => this.sales = sales, error => this.errorMessage = <any>error);
  }

  delete(): void{
    this.saleService.delete(this.selectedSale).subscribe();
    window.location.reload();
  }

  onSelect(sale: Sale): void {
    this.selectedSale = sale;
  }

  goToAdd():void{
    this.router.navigate(['/sale/add']);
  }

}
