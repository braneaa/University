import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientComponent } from './client/client.component';
import { ClientDetailComponent } from './client/client-detail/client-detail.component';
import { ClientListComponent } from './client/client-list/client-list.component';
import {FormsModule} from "@angular/forms";
import {ClientService} from "./client/shared/client.service";
import { ClientAddComponent } from './client/client-add/client-add.component';
import { BookComponent } from './book/book.component';
import { BookAddComponent } from './book/book-add/book-add.component';
import { BookDetailComponent } from './book/book-detail/book-detail.component';
import { BookListComponent } from './book/book-list/book-list.component';
import { SaleComponent } from './sale/sale.component';
import { SaleListComponent } from './sale/sale-list/sale-list.component';
import { SaleAddComponent } from './sale/sale-add/sale-add.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    ClientDetailComponent,
    ClientListComponent,
    ClientAddComponent,
    BookComponent,
    BookAddComponent,
    BookDetailComponent,
    BookListComponent,
    SaleComponent,
    SaleListComponent,
    SaleAddComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
