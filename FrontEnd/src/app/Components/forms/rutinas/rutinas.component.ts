import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialog,
} from '@angular/material/dialog';
import { EjercicioR } from 'src/app/Interface/ejercicio';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';
@Component({
  selector: 'app-rutinas',
  templateUrl: './rutinas.component.html',
  styleUrls: ['./rutinas.component.css'],
})
export class RutinasComponent implements OnInit {
  form: FormGroup;
  Ejercicios: any;
  id: number | undefined;
  rutinaEjercioService: string = this.Service.apiUrlRutineEjercicio;
  EjerciciosUrl: string = this.Service.apiUrlEjercicio;
  constructor(
    public dialogRef: MatDialogRef<RutinasComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public Service: EndpointsService,
    private fb: FormBuilder,
    private _metodoService: MetodosService
  ) {
    this.form = fb.group({
      exerciseId: ['', Validators.required],
      quantity: ['', Validators.required],
      repetitions: ['', Validators.required],
      series: ['', Validators.required],
    });
    this.id = data.idRutina;
  }
  ngOnInit(): void {
    this.Service.obtenerDatos(this.EjerciciosUrl).subscribe((data) => {
      this.Ejercicios = data;
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  Add() {
    const ejercicio: EjercicioR = {
      exerciseId: this.form.value.exerciseId,
      quantity: this.form.value.quantity,
      repetitions: this.form.value.repetitions,
      series: this.form.value.series,
    };

    // Es agregar
    this.Service.AgregarItem(
      this.id!,
      ejercicio,
      this.rutinaEjercioService
    ).subscribe(() => {
      this._metodoService.mensaje('Ejercicio Agregada con Exito !', 5);
    });
  }
}
