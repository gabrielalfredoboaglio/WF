import { Component, OnInit } from '@angular/core';
export interface Rutina {
  series: number;
  tiempoEjercicio: number;
  tiempoDescanso: number;
}
@Component({
  selector: 'app-cronometro',
  templateUrl: './cronometro.component.html',
  styleUrls: ['./cronometro.component.css']
})
export class CronometroComponent implements OnInit  {
  Ejercicio:boolean=true;
  minutos:number=0;
  segundos: number = 0;
  milisegundos: number = 0;
  intervaloCronometro: any = null;
  tipo:string = "Cronometro"
  contenedor:any

  tiempoEjercicio: number = 30; // en segundos
  tiempoDescanso: number = 10; // en segundos
  numSeries: number = 3;
  tiempoE: number = 0; // en segundos
  tiempoD: number=0;
  intervalo: any;
ngOnInit() {
 
}
  empezarCronometro() {
    if (!this.intervaloCronometro) {
      this.intervaloCronometro = setInterval(() => {
        this.milisegundos++;
        if (this.milisegundos == 100) {
          this.milisegundos = 0;
          this.segundos++;
        }if (this.segundos == 60) {
          this.segundos = 0;
          this.minutos++;
        }
      }, 10);
    }
  }

  pararCronometro() {
    clearInterval(this.intervaloCronometro);
    this.intervaloCronometro = null;
  }

  reiniciarCronometro() {
    this.minutos= 0;
    this.segundos = 0;
    this.milisegundos = 0;
    clearInterval(this.intervaloCronometro);
    this.intervaloCronometro = null;
  }
cambiar(tipo:string) {

  this.tipo= tipo
}
empezar() {
  this.tiempoE = this.tiempoEjercicio;
  let seriesRestantes = this.numSeries;
  this.tiempoD= this.tiempoDescanso
  this.intervalo = setInterval(() => {
    if (this.tiempoE > 0) {
      this.tiempoE--;
    } else {
      if (this.tiempoD > 0) {
        this.contenedor = document.getElementById("mi-contenedor") as HTMLElement;
        this.contenedor.style.backgroundColor = "grey";
        this.Ejercicio=false;
        this.tiempoD--;
        this.tiempoD = this.tiempoD;
      } else {
        seriesRestantes--;
        if (seriesRestantes > 0) {
          this.contenedor = document.getElementById("mi-contenedor") as HTMLElement;
          this.contenedor.style.backgroundColor = "#FFF";
          this.Ejercicio=true;
          this.tiempoD = this.tiempoDescanso // reiniciar el tiempo de descanso
          this.tiempoE = this.tiempoEjercicio; // reiniciar el tiempo de ejercicio
        } else {
          clearInterval(this.intervalo);
        }
      }
    }
  }, 1000);
}

pausar() {
 
  clearInterval(this.intervalo);
  this.intervalo=null
}

reiniciar() {
  clearInterval(this.intervalo);
  this.intervalo=null
  this.tiempoD = 0;
  this.tiempoE = 0;
this.Ejercicio = true;
this.contenedor = document.getElementById("mi-contenedor") as HTMLElement;
          this.contenedor.style.backgroundColor = "#FFF";
}




}

  