import { Component, OnInit } from '@angular/core';
import { RawMaterialOrderService } from '../service/raw-material-order.service';

@Component({
  selector: 'app-suppliers-id',
  templateUrl: './suppliers-id.component.html',
  styleUrls: ['./suppliers-id.component.css']
})
export class SuppliersIdComponent implements OnInit {

  supplierIds:String[];
  service:RawMaterialOrderService;
  constructor(service:RawMaterialOrderService) {
    this.service=service;
    let result = this.service.getAllSuppliers();
    result.subscribe((ids:String[])=>{
      this.supplierIds=ids;
    },
    err=>{
      console.log("error:"+err);
    });
   }
  ngOnInit(): void {
  }

}
