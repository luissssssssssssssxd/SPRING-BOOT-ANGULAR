import { TestBed } from '@angular/core/testing';

import { ObsIncidenciasService } from './obs-incidencias.service';

describe('ObsIncidenciasService', () => {
  let service: ObsIncidenciasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObsIncidenciasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
