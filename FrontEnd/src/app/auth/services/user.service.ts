import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../interfaces/user';
import { BehaviorSubject, Observable, tap, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Role } from '../interfaces/role';

@Injectable({ providedIn: 'root' })
export class UserService {
  private baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient, private authService: AuthService) {
    const user = this.authService.userValue;
  }

  getAll() {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

  getById(id: number) {
    return this.http.get<User>(`${this.baseUrl}/users/${id}`);
  }

  editProfile(id: number, formData: FormData) {
    return this.http.put<User>(`${this.baseUrl}/users/${id}`, formData);
  }
}
