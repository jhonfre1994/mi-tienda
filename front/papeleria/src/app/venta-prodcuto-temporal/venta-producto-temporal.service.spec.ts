import { TestBed } from '@angular/core/testing';

import { VentaProductoTemporalService } from './venta-producto-temporal.service';

describe('VentaProductoTemporalService', () => {
  let service: VentaProductoTemporalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VentaProductoTemporalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
