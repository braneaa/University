import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ClientComponent} from "./client/client.component";
import {ClientDetailComponent} from "./client/client-detail/client-detail.component";
import {ClientAddComponent} from "./client/client-add/client-add.component";
import {BookComponent} from "./book/book.component";
import {BookDetailComponent} from "./book/book-detail/book-detail.component";
import {BookAddComponent} from "./book/book-add/book-add.component";
import {SaleComponent} from "./sale/sale.component";
import {SaleAddComponent} from "./sale/sale-add/sale-add.component";

const routes: Routes = [
  {path: 'clients', component: ClientComponent},
  {path: 'client/detail/:id', component: ClientDetailComponent},
  {path: 'client/add', component: ClientAddComponent},
  {path: 'books', component: BookComponent},
  {path: 'book/detail/:id', component: BookDetailComponent},
  {path: 'book/add', component: BookAddComponent},
  {path: 'sales', component: SaleComponent},
  {path: 'sale/add', component: SaleAddComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
