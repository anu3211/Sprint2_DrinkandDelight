import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddOrderComponent } from './add-order/add-order.component';
import { DisplayOrdersComponent } from './display-orders/display-orders.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import { HttpClientModule } from '@angular/common/http';
import { GetByIdComponent } from './get-by-id/get-by-id.component';
import { SuppliersIdComponent } from './suppliers-id/suppliers-id.component';
import { WarehousesIdComponent } from './warehouses-id/warehouses-id.component';
import { RawMaterialsNameComponent } from './raw-materials-name/raw-materials-name.component';

@NgModule({
  declarations: [
    AppComponent,
    AddOrderComponent,
    DisplayOrdersComponent,
    UpdateOrderComponent,
    GetByIdComponent,
    SuppliersIdComponent,
    WarehousesIdComponent,
    RawMaterialsNameComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
