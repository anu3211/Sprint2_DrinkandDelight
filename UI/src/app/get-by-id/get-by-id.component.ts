import { Component, OnInit } from '@angular/core';
import { RawMaterialOrderService } from '../service/raw-material-order.service';
import { RawMaterialOrder } from '../model/rawMaterialOrder';

@Component({
  selector: 'app-get-by-id',
  templateUrl: './get-by-id.component.html',
  styleUrls: ['./get-by-id.component.css']
})
export class GetByIdComponent implements OnInit {
  order:RawMaterialOrder=null;
  service:RawMaterialOrderService;
  constructor(service:RawMaterialOrderService) {
    this.service=service;
  }
  ngOnInit(): void {
  }
  getById(form:any){
    this.order=null;
    let details=form.value;
    let id=details.id;
    let result = this.service.getById(id);
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
