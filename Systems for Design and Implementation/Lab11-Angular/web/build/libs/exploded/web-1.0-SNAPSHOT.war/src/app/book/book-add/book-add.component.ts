import { Component, OnInit } from '@angular/core';
import {BookService} from "../shared/book.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-book-add',
  templateUrl: './book-add.component.html',
  styleUrls: ['./book-add.component.css']
})
export class BookAddComponent implements OnInit {

  constructor(private bookService:BookService,
              private route:ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  goBack():void{
    this.location.back();
  }

  save(serialnumber,name,author,genre):void{
    if(!this.isValid(serialnumber,name,author,genre)){
      console.log("all fields are required!");
      alert("all fields are required");
      return;
    }
    this.bookService.save(serialnumber,name,author,genre).subscribe(_=> this.goBack());
  }

  private isValid(serialnumber: any, name: any, author: any, genre: any) {
    if(!serialnumber || !name || !author || !genre){
      console.log("all fields are required!");
      return false;
    }
    return true
  }
}
