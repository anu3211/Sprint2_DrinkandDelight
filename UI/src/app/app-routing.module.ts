import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddOrderComponent } from './add-order/add-order.component';
import { DisplayOrdersComponent } from './display-orders/display-orders.component';
import { GetByIdComponent } from './get-by-id/get-by-id.component';
import { SuppliersIdComponent } from './suppliers-id/suppliers-id.component';
import { WarehousesIdComponent } from './warehouses-id/warehouses-id.component';
import { RawMaterialsNameComponent } from './raw-materials-name/raw-materials-name.component';
import { UpdateOrderComponent } from './update-order/update-order.component';


const routes: Routes = [
  {path: 'addOrder' , component : AddOrderComponent},
  {path:'details',component:DisplayOrdersComponent},
  {path:'getById',component:GetByIdComponent},
  {path:'suppliersId',component:SuppliersIdComponent},
  {path:'warehousesId',component:WarehousesIdComponent},
  {path:'rawMaterialsName',component:RawMaterialsNameComponent},
  {path:'updateStatus',component:UpdateOrderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
