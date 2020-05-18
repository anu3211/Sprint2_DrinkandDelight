import { Component, OnInit } from '@angular/core';
import { RawMaterialOrder } from '../model/rawMaterialOrder';
import { RawMaterialOrderService } from '../service/raw-material-order.service';

@Component({
  selector: 'display-orders',
  templateUrl: './display-orders.component.html',
  styleUrls: ['./display-orders.component.css']
})
export class DisplayOrdersComponent implements OnInit {

  orders:RawMaterialOrder[];
  service:RawMaterialOrderService;
  constructor(service:RawMaterialOrderService) {
    this.service=service;
    let result = this.service.getAll();
    result.subscribe((orders:RawMaterialOrder[])=>{
      this.orders=orders;
    },
    err=>{
      console.log("error:"+err);
    });
   }
 
  ngOnInit(): void {
    }
  
}
