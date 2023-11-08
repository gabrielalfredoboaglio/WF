import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'UnidadATipo' })
export class TransformUnits implements PipeTransform {
  transform( unit: string): string {
    switch (unit) {
      case 'Km':
        return `Distancia`;
      case 'Kg':
        return `Peso`;
      case 'Minutos':
        return `Tiempo`;
      default:
        return " "
    }
  }
}