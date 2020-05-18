import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuppliersIdComponent } from './suppliers-id.component';

describe('SuppliersIdComponent', () => {
  let component: SuppliersIdComponent;
  let fixture: ComponentFixture<SuppliersIdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuppliersIdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuppliersIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
