import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormimpresorasComponent } from './formimpresoras.component';

describe('FormimpresorasComponent', () => {
  let component: FormimpresorasComponent;
  let fixture: ComponentFixture<FormimpresorasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormimpresorasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormimpresorasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
