import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RawMaterialsNameComponent } from './raw-materials-name.component';

describe('RawMaterialsNameComponent', () => {
  let component: RawMaterialsNameComponent;
  let fixture: ComponentFixture<RawMaterialsNameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RawMaterialsNameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RawMaterialsNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
