import { Component, OnInit } from '@angular/core';
import { RawMaterialOrderService } from '../service/raw-material-order.service';

@Component({
  selector: 'app-warehouses-id',
  templateUrl: './warehouses-id.component.html',
  styleUrls: ['./warehouses-id.component.css']
})
export class WarehousesIdComponent implements OnInit {

  warehouseIds:String[];
  service:RawMaterialOrderService;
  constructor(service:RawMaterialOrderService) {
    this.service=service;
    let result = this.service.getAllWarehouses();
    result.subscribe((ids:String[])=>{
      this.warehouseIds=ids;
    },
    err=>{
      console.log("error:"+err);
    });
   }
  ngOnInit(): void {
  }

}
