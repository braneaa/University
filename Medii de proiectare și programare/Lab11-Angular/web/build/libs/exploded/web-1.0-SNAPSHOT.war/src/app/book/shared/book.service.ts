import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Book} from "./book.model";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private booksURL='http://localhost:8080/api/books';

  constructor(private httpClient: HttpClient) { }

  getBooks():Observable<Book[]>{
    return this.httpClient.get<Array<Book>>(this.booksURL);
  }

  getBook(id:number): Observable<Book>{
    return this.getBooks().pipe(map(books=>books.find(book=>book.id === id)));
  }

  update(book): Observable<Book>{
    const url = `${this.booksURL}/${book.id}`;
    return this.httpClient.put<Book>(url,book);
  }

  delete(book): Observable<{}>{
    const url = `${this.booksURL}/${book.id}`;
    return this.httpClient.delete(url,book);
  }

  save(serialnumber:string, name:string, author:string, genre:string):Observable<Book>{
    let book = {serialnumber,name,author,genre};
    return this.httpClient.post<Book>(this.booksURL,book);
  }


}
