import { Component, OnInit } from '@angular/core';
import { RawMaterialOrder } from '../model/rawMaterialOrder';
import { RawMaterialOrderService } from '../service/raw-material-order.service';

@Component({
  selector: 'update-order',
  templateUrl: './update-order.component.html',
  styleUrls: ['./update-order.component.css']
})
export class UpdateOrderComponent implements OnInit {

  order:RawMaterialOrder=null;
  service:RawMaterialOrderService;
  constructor(service:RawMaterialOrderService) {
    this.service=service;
   }

  rawMaterialOrder:RawMaterialOrder = null;
  ngOnInit(): void {
  }

  update(form:any){
    this.order=null;
    let details=form.value;
    let id=details.id;
    let status=details.status;
    let updateOrder:RawMaterialOrder=new RawMaterialOrder();
    updateOrder.orderId=id;
    updateOrder.deliveryStatus=status;
    let result = this.service.updateStatus(updateOrder);
    result.subscribe((order:RawMaterialOrder)=>{
      this.order=order;
    },
    err=>{
      console.log("error:"+err);
    }
    );
    form.reset();
 
  }
  
}
