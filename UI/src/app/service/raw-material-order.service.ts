import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RawMaterialOrder } from '../model/rawMaterialOrder';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RawMaterialOrderService {
  client:HttpClient;
  constructor(client:HttpClient){
    this.client=client;
  }
  baseUrl = "http://localhost:8086/rawMaterialOrder";

    addOrder(order:RawMaterialOrder): Observable<RawMaterialOrder>{
        let url = this.baseUrl+"/placeOrder";
        let result :Observable<RawMaterialOrder>=this.client.post<RawMaterialOrder>(url,order);
        return result;
    }

    getAll():Observable<RawMaterialOrder[]>{
      let url=this.baseUrl;
      let result:Observable<RawMaterialOrder[]>=this.client.get<RawMaterialOrder[]>(url);
      return result;
    }

    getById(id:String): Observable<RawMaterialOrder>{
      let url = this.baseUrl+"/getbyid/"+id;
      let result :Observable<RawMaterialOrder>=this.client.get<RawMaterialOrder>(url);
      return result;
  }

    getAllSuppliers():Observable<String[]>{
      let url=this.baseUrl+"/allSupplierIds";
      let result : Observable<String[]>=this.client.get<String[]>(url);
      return result;
    }

    getAllWarehouses():Observable<String[]>{
      let url=this.baseUrl+"/allWarehouseIds";
      let result : Observable<String[]>=this.client.get<String[]>(url);
      return result;
    }

    getAllNames():Observable<String[]>{
      let url=this.baseUrl+"/allRawMaterialNames";
      let result : Observable<String[]>=this.client.get<String[]>(url);
      return result;
    }

    updateStatus(order:RawMaterialOrder): Observable<RawMaterialOrder>{
      let url = this.baseUrl+"/update/"+order.orderId;
      let result :Observable<RawMaterialOrder>=this.client.put<RawMaterialOrder>(url,order);
      return result;
  }
}
