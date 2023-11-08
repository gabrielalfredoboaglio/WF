import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { InterceptorService } from './auth/services/user.interceptor';

import { VistaRolesDirective } from './directives/vista-roles.directive';
import { JwtInterceptor, JwtModule } from '@auth0/angular-jwt';
import { ErrorInterceptor } from './auth/services/error.interceptor';
import { SharedModule } from './Shared/shared.module';

import { RutinaComponent } from './Components/rutina/rutina.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InicioComponent } from './Components/inicio/inicio.component';
import { VerClientesComponent } from './Components/ver-clientes/ver-clientes.component';
import { EditarRutinasComponent } from './Components/editar-rutinas/editar-rutinas.component';
import { EditarEjercicioComponent } from './Components/editar-ejercicio/editar-ejercicio.component';
import { RutinasComponent } from './Components/forms/rutinas/rutinas.component';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { VerEjerciciosComponent } from './Components/ver-ejercicios/ver-ejercicios.component';
import { PerfilComponent } from './Components/perfil/perfil.component';
import { EjerciciosComponent } from './Components/forms/ejercicios/ejercicios.component';
import { TransformUnits } from './Components/forms/rutinas/TransformUnits';
import { NuevaRutinaComponent } from './Components/forms/nueva-rutina/nueva-rutina.component';
import { PageErrorComponent } from './page-error/page-error.component';
import { PageUserComponent } from './page-user/page-user.component';
import { FormsModule } from '@angular/forms';
import { TransformRoles } from './Components/ver-clientes/pipeRoles';
import { RolComponent } from './Components/forms/rol/rol.component';
import { CronometroComponent } from './Components/cronometro/cronometro.component';import { InfoUsuarioComponent } from './Components/forms/info-usuario/info-usuario.component';

@NgModule({
  declarations: [
    AppComponent,
    RutinaComponent,
    InicioComponent,
    VerClientesComponent,
    EditarRutinasComponent,
    EditarEjercicioComponent,
    NavbarComponent,
    VerEjerciciosComponent,
    RutinasComponent,
    PerfilComponent,
    EjerciciosComponent,
    TransformUnits,
    NuevaRutinaComponent,
    VistaRolesDirective,
    PageErrorComponent,
    PageUserComponent,
    InfoUsuarioComponent,
    TransformRoles,
    RolComponent,
    CronometroComponent,
  ],

  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    SharedModule,
    FormsModule,

    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ['http://localhost:8080/v1'], // Reemplaza con tu dominio
        disallowedRoutes: [
          'http://localhost:8080/v1/auth/authenticate',
        ], // Reemplaza con tus rutas de login
      },
    }),
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true },
    [Location],
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    // { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

export function tokenGetter() {
  return localStorage.getItem('authToken'); // Cambia "access_token" con el nombre de tu token de acceso
}
