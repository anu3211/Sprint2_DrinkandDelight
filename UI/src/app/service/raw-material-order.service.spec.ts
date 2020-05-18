import { TestBed } from '@angular/core/testing';

import { RawMaterialOrderService } from './raw-material-order.service';

describe('RawMaterialOrderService', () => {
  let service: RawMaterialOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RawMaterialOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
