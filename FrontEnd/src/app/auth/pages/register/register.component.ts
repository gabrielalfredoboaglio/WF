import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormErrors } from '../../interfaces/form-errors.interface';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  // Variables
  hide = true;
  loading: boolean = false;
  previewImageUrl: string | undefined;

  // Formulario de registro de usuario
  registerForm: FormGroup = this.fb.group({
    name: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    sex: ['', [Validators.required]],
    age: [0, [Validators.required, Validators.pattern(/^[1-9]\d*$/)]],
    height: [0, [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
    weight: [0, [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
    profileImg: new FormControl(null, {
      validators: [
        (control: AbstractControl): { [key: string]: any } | null => {
          const file = control.value as File;
          const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
          if (file && !allowedTypes.includes(file.type)) {
            return { invalidImageType: true };
          }
          return null;
        },
      ],
    }),
  });

  // Constructor
  constructor(
    public authService: AuthService,
    private fb: NonNullableFormBuilder,
    private router: Router
  ) {}

  // Comprueba si un campo del formulario es inválido
  isFieldInvalid(field: string): boolean {
    const control = this.registerForm.get(field)!;
    return (
      control?.hasError('required') ||
      control?.hasError('invalidName') ||
      control?.hasError('invalidEmail') ||
      (control?.hasError('invalidPassword') && control?.touched) ||
      (control?.hasError('invalidConfirmPassword') && control?.touched)
    );
  }

  // Errores de formulario
  formErrors: FormErrors = {
    required: 'Este campo es obligatorio',
    invalidName: 'El nombre no es válido',
    invalidEmail: 'El email no es válido',
    invalidPassword:
      'La contraseña debe tener al menos 6 caracteres y contener mayúsculas, minúsculas, números y caracteres especiales.',
    invalidImageType:
      'El tipo de archivo no es válido. Los formatos permitidos son: jpeg, png y gif.',
  };

  // Obtiene el mensaje de error para un campo del formulario
  getErrorMessage(field: string): string {
    const control = this.registerForm.get(field);
    if (control?.touched) {
      if (control?.errors) {
        const errorKey = Object.keys(control.errors)[0];
        return this.formErrors[errorKey];
      }
    }
    return '';
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.registerForm.get('profileImg')!.setValue(file);
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.previewImageUrl = e.target.result;
    };
    reader.readAsDataURL(file);
  }

  resetImage() {
    // Borrar imagen cargada previamente
    this.registerForm.get('profileImg')!.setValue(null);
    this.previewImageUrl = undefined;
  }

  // Envío del formulario
  register() {
    // Obtener los valores del formulario
    const formData = new FormData();
    formData.append('name', this.registerForm.get('name')!.value);
    formData.append('email', this.registerForm.get('email')!.value);
    formData.append('password', this.registerForm.get('password')!.value);
    formData.append('sex', this.registerForm.get('sex')!.value);
    formData.append('age', this.registerForm.get('age')!.value);
    formData.append('height', this.registerForm.get('height')!.value);
    formData.append('weight', this.registerForm.get('weight')!.value);

    // Obtener el archivo seleccionado del formulario
    const file = this.registerForm.get('profileImg')!.value;

    // Agregar el archivo seleccionado a formData
    if (file) {
      formData.append('profileImg', file);
    }

    // Imprimir los datos de FormData por consola
    formData.forEach((value, key) => {
     
    });

    // Llamar al método de registro con los datos del formulario y el archivo
    this.authService.register(formData).subscribe(
      (response) => {
        // Manejar la respuesta exitosa del servidor
        this.authService.setAuthToken(response.token);
        this.loading = true;
        setTimeout(() => {
          this.router.navigate(['inicio']);
        }, 1500);
      },
      (error) => {
        // Manejar errores en caso de fallo
        if (error && error.error && error.error.message) {
          Swal.fire({
            icon: 'error',
            title: 'Error al registrar usuario',
            text: error.error.message,
          });
        }
      }
    );
  }
}
