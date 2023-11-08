import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarRutinasComponent } from './editar-rutinas.component';

describe('EditarRutinasComponent', () => {
  let component: EditarRutinasComponent;
  let fixture: ComponentFixture<EditarRutinasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarRutinasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarRutinasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
