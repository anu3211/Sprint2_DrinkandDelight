import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehousesIdComponent } from './warehouses-id.component';

describe('WarehousesIdComponent', () => {
  let component: WarehousesIdComponent;
  let fixture: ComponentFixture<WarehousesIdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarehousesIdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarehousesIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
