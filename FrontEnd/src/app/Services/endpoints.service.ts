import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EndpointsService {
  private myAppUrl: string = 'http://localhost:8080/v1'
  apiUrlUser: string;
  apiUrlRutine: string;
  apiUrlEjercicio: string;
  apiUrlUserRutine: string;
  apiUrlRutineEjercicio: string;

  constructor(private http: HttpClient) {
    this.myAppUrl = environment.baseUrl;
    this.apiUrlUser = '/users';
    this.apiUrlRutine = '/routines';
    this.apiUrlEjercicio = '/exercises';
    this.apiUrlUserRutine = '/users/routine';
    this.apiUrlRutineEjercicio = '/routines/exercises';
  }
  //Modificar roles
  public cambiarRol(id: number, rol: string): Observable<void> {
    return this.http.put<void>(`${this.myAppUrl}/users/role/${id}/${rol}`, {});
  }
  //Obtener usuarios por roles
  public UsersRoles(role: string): Observable<any> {
    return this.http.get(this.myAppUrl + '/users/role/' + `${role}`);
  }
  //Eliminar ejercicio de una rutina especifica
  public borrarEjercicioRutina(
    idRutina: number,
    idEjercicio: any
  ): Observable<any> {
    return this.http.delete<any>(
      this.myAppUrl + '/routines/exercises' + `/${idRutina}`,
      idEjercicio
    );
  }
  //Agregar ejercicio a una rutina
  public AgregarItem(
    id: number,
    objeto: any,
    apiUrlItem: string
  ): Observable<any> {
    return this.http.post<any>(this.myAppUrl + apiUrlItem + `/${id}`, objeto);
  }
  //Obtener datos
  public obtenerDatos(apiUrlItem: string): Observable<any> {
    return this.http.get(this.myAppUrl + apiUrlItem);
  }

  //Obtener datos por id
  public obtenerDatosId(id: number, apiUrlItem: string): Observable<any> {
    return this.http.get(this.myAppUrl + apiUrlItem + `/${id}`);
  }
  //Eliminar
  public borrarItem(id: number, apiUrlItem: string): Observable<void> {
    return this.http
      .delete<void>(this.myAppUrl + apiUrlItem + `/${id}`)
      .pipe(catchError(this.errorHandler));
  }
  //Crear
  public NuevoItem(objeto: any, apiUrlItem: string): Observable<any> {
    return this.http.post<any>(this.myAppUrl + apiUrlItem, objeto);
  }

  //Editar
  public editarItem(
    id: number,
    objeto: any,
    apiUrlItem: string
  ): Observable<void> {
    return this.http.put<void>(this.myAppUrl + apiUrlItem + `/${id}`, objeto);
  }

  // Metodo Maejo de Error

  errorHandler(error: HttpErrorResponse) {
    return throwError(error.message);
  }
}
