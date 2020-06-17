import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sale} from "./sale.model";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  private salesURL ='http://localhost:8080/api/sales';

  constructor(private httpClient:HttpClient) { }

  getSales():Observable<Sale[]> {
    return this.httpClient.get<Array<Sale>>(this.salesURL);
  }

  getSale(id:number):Observable<Sale>{
    return this.getSales().pipe(map(sales => sales.find(sale => sale.id === id)));
  }

  delete(sale): Observable<{}>{
    const url = `${this.salesURL}/${sale.id}`;
    return this.httpClient.delete(url,sale);
  }

  save(bookId: number, clientId: number) : Observable<Sale>{
    let sale = {bookId, clientId};
    return this.httpClient.post<Sale>(this.salesURL,sale);
  }

}
