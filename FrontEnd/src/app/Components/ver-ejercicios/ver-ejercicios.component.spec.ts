import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerEjerciciosComponent } from './ver-ejercicios.component';

describe('VerEjerciciosComponent', () => {
  let component: VerEjerciciosComponent;
  let fixture: ComponentFixture<VerEjerciciosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerEjerciciosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerEjerciciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
