import { Component, OnInit } from '@angular/core';
import { RawMaterialOrderService } from '../service/raw-material-order.service';

@Component({
  selector: 'app-raw-materials-name',
  templateUrl: './raw-materials-name.component.html',
  styleUrls: ['./raw-materials-name.component.css']
})
export class RawMaterialsNameComponent implements OnInit {

  rawMaterialNames:String[];
  service:RawMaterialOrderService;
  constructor(service:RawMaterialOrderService) {
    this.service=service;
    let result = this.service.getAllNames();
    result.subscribe((names:String[])=>{
      this.rawMaterialNames=names;
    },
    err=>{
      console.log("error:"+err);
    });
   }
  ngOnInit(): void {
  }

}
