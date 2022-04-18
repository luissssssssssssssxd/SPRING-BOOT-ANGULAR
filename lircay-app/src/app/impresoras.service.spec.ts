import { TestBed } from '@angular/core/testing';

import { ImpresorasService } from './impresoras.service';

describe('ImpresorasService', () => {
  let service: ImpresorasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImpresorasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
