import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Rutina } from 'src/app/Interface/rutina';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';

@Component({
  selector: 'app-nueva-rutina',
  templateUrl: './nueva-rutina.component.html',
  styleUrls: ['./nueva-rutina.component.css']
})
export class NuevaRutinaComponent {form: FormGroup
  

  constructor(
    public dialogRef: MatDialogRef<NuevaRutinaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public Service: EndpointsService,
    private fb: FormBuilder,
    private _metodoService: MetodosService
  ) {
    this.form = fb.group({
      title:['',Validators.required]
    })
    this.id = data.id;
    
  }
  Rutina: any
  id: number 
  api:string = this.Service.apiUrlRutine
  ngOnInit(): void {
    
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }
  
  
    Add() {

      const Rutina:Rutina= {
        
        title:this.form.value.title, 
        userId: this.id,
      }
      // Es agregar
      this. Service.NuevoItem( Rutina, this.api).subscribe(() => {
        this._metodoService.mensaje('Rutina agregada con Exito !', 5);
      })
  }
  

  }

