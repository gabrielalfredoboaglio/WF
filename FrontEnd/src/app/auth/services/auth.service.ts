import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable, map, of } from 'rxjs';

import { environment } from 'src/environments/environment';

import { User } from '../interfaces/user';
import { Credentials } from '../interfaces/credentials';
import { Router } from '@angular/router';
import { Role } from '../interfaces/role';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private userSubject: BehaviorSubject<User | null>;
  public user$: Observable<User | null>;
  private baseUrl: string = environment.baseUrl;
  private jwtHelper = new JwtHelperService();
  private authTokenKey = 'authToken';

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject(
      JSON.parse(localStorage.getItem('user')!)
    );
    this.user$ = this.userSubject.asObservable();
  }

  public get userValue() {
    return this.userSubject.value;
  }

  register(formData: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/register`, formData);
  }

  login(creds: Credentials) {
    const body = creds;
    return this.http.post<any>(`${this.baseUrl}/auth/authenticate`, body).pipe(
      map((user) => {
        this.setAuthToken(user.token);
        const decodedToken = this.jwtHelper.decodeToken(user.token);
        localStorage.setItem('user', JSON.stringify(decodedToken));
        // Almacenar la propiedad "rol" en el localStorage
        localStorage.setItem('roles', JSON.stringify(decodedToken.roles));
        this.userSubject.next(decodedToken);
        return user;
      })
    );
  }

  setAuthToken(token: string): void {
    localStorage.setItem(this.authTokenKey, token);
  }

  getAuthToken(): string | null {
    return localStorage.getItem(this.authTokenKey);
  }

  // Método para obtener la información del usuario a partir del token JWT
  decodeAuthToken(authToken: string): any {
    const decodedToken = this.jwtHelper.decodeToken(authToken);
    return decodedToken;
  }

  removeAuthToken(): void {
    localStorage.removeItem(this.authTokenKey);
  }

  // Metodo verificar si el usuario esta loqueado
  isAuthenticated(): Observable<boolean> {
    const authToken = this.getAuthToken();
    if (authToken) {
      return of(!this.jwtHelper.isTokenExpired(authToken));
    }
    return of(false);
  }

  getRoles(): Role[] {
    const storedRoles = localStorage.getItem('roles');
    if (storedRoles) {
      return storedRoles.split(',') as Role[];
    }
    return [];
  }

  logout() {
    localStorage.clear();
    this.userSubject.next(null);
    this.router.navigate(['/auth/login']);
  }
}
