import { Component, OnInit } from '@angular/core';
import { RawMaterialOrder } from '../model/rawMaterialOrder';
import { RawMaterialOrderService } from '../service/raw-material-order.service';
import { JsonPipe } from '@angular/common';


@Component({
  selector: 'add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {

  order:RawMaterialOrder=null;
  service:RawMaterialOrderService;
  constructor(service:RawMaterialOrderService) {
    this.service=service;
   }
   errorMsg=null;
   orderCreated:boolean=false;
  ngOnInit(): void {
  }
  check:boolean;
  addOrder(addForm :any)
  {
    this.orderCreated=false;
    let details = addForm.value;
    let sid = details.sid;
    let name = details.name;
    let price_unit = details.price_unit;
    let quantityValue = details.quantityValue;
    let quantityUnit = details.quantityUnit;
    let wid = details.wid;
    let deliveryDate=details.deliveryDate;
    this.order=new RawMaterialOrder();
    this.order.supplierId=sid;
    this.order.name=name;
    this.order.pricePerUnit=price_unit;
    this.order.quantityValue=quantityValue;
    this.order.quantityUnit=quantityUnit;
    this.order.warehouseId=wid;
    this.order.dateOfDelivery=deliveryDate;
    let result = this.service.addOrder(this.order);
    result.subscribe((order:RawMaterialOrder)=>{
      this.order=order;
      this.errorMsg=null;
      this.orderCreated=true;
    },
    err=>{
      let x=JSON.stringify(err);
      console.log("error:"+x);
      this.errorMsg=err.error;
      console.log("error msg:"+this.errorMsg);
    }

    );
    addForm.reset();
  }

}
