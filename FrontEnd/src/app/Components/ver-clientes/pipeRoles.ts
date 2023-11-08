import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'Roles'
})
export class TransformRoles implements PipeTransform {
  transform(value: string[]): string {
    if (value.includes('ROLE_ADMIN')) {
      return 'Administrador';
    } 
    else if(value.includes('ROLE_COACH')) {
        return 'Profesor';
      } 
     else if(value.includes('ROLE_CUSTOMER')) {
        return 'Cliente';
      } 
      else if(value.includes('ROLE_USER')) {
        return 'Usuario';
      }
      else {
      return '';
    }
  }

}





