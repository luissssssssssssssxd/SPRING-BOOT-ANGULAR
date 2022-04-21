import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObsIncidenciasComponent } from './obs-incidencias.component';

describe('ObsIncidenciasComponent', () => {
  let component: ObsIncidenciasComponent;
  let fixture: ComponentFixture<ObsIncidenciasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObsIncidenciasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObsIncidenciasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
