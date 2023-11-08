import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Ejercicio } from 'src/app/Interface/ejercicio';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';

@Component({
  selector: 'app-ejercicios',
  templateUrl: './ejercicios.component.html',
  styleUrls: ['./ejercicios.component.css'],
})
export class EjerciciosComponent implements OnInit {
  form: FormGroup;
  idEjercicio: number | undefined;
  loading: boolean = false;
  operacion: string = 'Agregar ';
  appi: string = this._endpoints.apiUrlEjercicio;
  musculos: string[] = this._metodoService.grupoMuscular;
  imagenes: any[] = this._metodoService.img;
  unity: any[] = this._metodoService.unidad;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<EjerciciosComponent>,
    private fb: FormBuilder,
    private _endpoints: EndpointsService,
    private _metodoService: MetodosService
  ) {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      grupoMuscular: ['', Validators.required],
      imagen: [null, Validators.required],
      unidad: [''],
    });
    this.idEjercicio = this.data.id;
  }

  ngOnInit(): void {
    this.esEditar(this.idEjercicio);
  }

  esEditar(id: number | undefined) {
    if (id !== undefined) {
      this.operacion = 'Editar ';
      this.buscarEjercicio(id);
    }
  }

  buscarEjercicio(id: number) {
    this._endpoints.obtenerDatosId(id, this.appi).subscribe((data) => {
      this.form.patchValue({
        id: data.id,
        nombre: data.title,
        grupoMuscular: data.description,
        imagen: data.media,
        unidad: data.unit,
      });
    });
  }

  cancelar() {
    this.dialogRef.close();
  }

  addEditEjercicio() {
    const ejercicio: Ejercicio = {
      id: this.idEjercicio,
      title: this.form.get('nombre')?.value,
      description: this.form.get('grupoMuscular')?.value,
      media: this.form.get('imagen')?.value,
      unit: this.form.get('unidad')?.value,
    };
    this.loading = true;

    if (this.idEjercicio == undefined) {
      //Es agregar
      this._endpoints.NuevoItem(ejercicio, this.appi).subscribe(() => {
        this._metodoService.mensaje('Nuevo Ejercicio agregado con Exito !', 2);
      });
    } else {
      // es Editar
      this._endpoints
        .editarItem(this.idEjercicio, ejercicio, this.appi)
        .subscribe((data) => {
          this._metodoService.mensaje('Ejercicio editado con Exito !', 2);
        });
    }
    this.loading = false;
    this.dialogRef.close(true);
  }
}
