import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class MetodosService {
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  grupoMuscular: string[] = [
    'Abdominales', 
    'Piernas', 
    'Espalda', 
    'Pecho', 
    'Gluteos', 
    'Brazos',
    'Aeróbic'
  ];
  unidad:any[] = [
    { name: 'Kilogramo', value:'Kg'},
    { name: 'Minutos', value:'Minutos'},
    { name: 'Kilometro', value:'Km'},
    { name: 'Sin unidad de medida', value: null},
       
  ];

  img: any[] = [
    {name:'Pesas', url:'https://i.ibb.co/KLKw43t/pesa-01.png'},
    {name:'Intervalos', url:'https://i.ibb.co/bvCpFXP/Sin-t-tulo-1-01.png'},
    {name:'Soga ', url:'https://i.ibb.co/FXc6wbF/soga-01.png'},
    {name:'Bici Fija', url:'https://i.ibb.co/wzDV540/bici-01.png'},
    {name:'Caminadora', url:'https://i.ibb.co/f8YGVhD/caminadora-01.png'},
    {name:'Cardio', url:'https://i.ibb.co/c3GPvtQ/cardio-01.png'},
    {name:'WorldFit', url:'https://i.ibb.co/nBcWy2y/logo-01.png'}
  
  ];

  


  constructor(private _snackBar: MatSnackBar,) { }
  

  mensaje(mensaje: string, segundos: number): void {
    this._snackBar.open(mensaje, '',{
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      duration: segundos * 1000
    });
  }

  
}
