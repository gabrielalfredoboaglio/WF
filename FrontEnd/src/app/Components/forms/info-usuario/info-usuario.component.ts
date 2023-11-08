import { Component, ElementRef, Inject, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PerfilComponent } from '../../perfil/perfil.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';

import { User } from 'src/app/auth/interfaces/user';
import { AuthService } from 'src/app/auth/services/auth.service';
import { UserService } from 'src/app/auth/services/user.service';
import { Subject, first, switchMap, takeUntil } from 'rxjs';
import { HotToastService } from '@ngneat/hot-toast';
import { Sex } from 'src/app/auth/interfaces/sex';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-info-usuario',
  templateUrl: './info-usuario.component.html',
  styleUrls: ['./info-usuario.component.css'],
})
export class InfoUsuarioComponent {
  @ViewChild('fileInput') fileInput!: ElementRef;
  @ViewChild('profileImage') profileImage!: ElementRef;
  form: FormGroup;
  formImg: FormGroup;
  sexOptions = [
    { value: Sex.Female, label: 'Femenino' },
    { value: Sex.Male, label: 'Masculino' },
  ];
  objetivo!: string;
  medico!: string;
  previewImageUrl = '../../../../assets/img/image-placeholder.png';
  _User!: User;
  user$!: User;
  changeImg: boolean = false;
  destroyed$ = new Subject<void>();

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<PerfilComponent>,
    private fb: FormBuilder,
    private _metodoService: MetodosService,
    private userService: UserService,
    private authService: AuthService,
    private toast: HotToastService
  ) {
    this.user$ = <User>this.authService.userValue;

    this.form = this.fb.group({
      nombre: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(20),
        ],
      ],
      meta: ['', [Validators.minLength(1), Validators.maxLength(50)]],
      indicacionMedica: [
        '',
        [Validators.minLength(1), Validators.maxLength(50)],
      ],
      sexo: [''],
      edad: ['', [Validators.required, Validators.pattern(/^[1-9]\d*$/)]],
      altura: [
        '',
        [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)],
      ],
      peso: [
        '',
        [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)],
      ],
      // profileImg: [null],
    });

    this.formImg = this.fb.group({
      profileImg: [null],
    });

    this._User = <User>this.authService.userValue;
  }

  ngOnInit(): void {
    this.objetivo = this.notNull(this.data.objective);
    this.medico = this.notNull(this.data.medical_indication);

    this.userService
      .getById(this._User.id)
      .pipe(first())
      .subscribe((user) => {
        this.user$ = user;
        if (this.user$ && this.user$.profileImg) {
          this.previewImageUrl = `https://${this.user$.profileImg}`;
        }
      });

    this.form.patchValue({
      id: this.data.id,
      nombre: this.data.name,
      peso: this.data.weight,
      altura: this.data.height,
      sexo: this.data.sex,
      edad: this.data.age,
      profileImg: this.data.profileImg,
      indicacionMedica: this.medico,
      meta: this.objetivo,
    });
  }

  cancelar() {
    this.dialogRef.close();
  }

  notNull(data: string) {
    if (data !== null) {
      return data;
    } else {
      return '';
    }
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.formImg.get('profileImg')!.setValue(file);
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.previewImageUrl = e.target.result;
    };
    reader.readAsDataURL(file);
    this.uploadFile();
  }

  uploadFile() {
    this.changeImg = true;
    const formData = new FormData();
    // Obtener el archivo seleccionado del formulario
    const file = this.formImg.get('profileImg')!.value;

    // Agregar el archivo seleccionado a formData
    if (file) {
      formData.append('profileImg', file);
    }

    this.userService
      .editProfile(this.user$.id, formData)
      .subscribe((data) => {});
  }

  EditInfoCliente() {
    const formData = new FormData();
    formData.append('name', this.form.get('nombre')?.value);
    formData.append('sex', this.form.get('sexo')?.value);
    formData.append('age', this.form.get('edad')?.value);
    formData.append('height', this.form.get('altura')?.value);
    formData.append('weight', this.form.get('peso')?.value);
    formData.append('objective', this.form.get('meta')?.value);
    formData.append(
      'medical_indication',
      this.form.get('indicacionMedica')?.value
    );

    this.userService.editProfile(this.user$.id, formData).subscribe((data) => {
      const Toast = Swal.mixin({
        toast: true,
        position: 'center',
        showConfirmButton: false,
        timer: 2500,
      });
      Toast.fire({
        icon: 'success',
        title: 'Perfil actualizado con exito',
      });
      setTimeout(() => {
        location.reload();
      }, 2000);
    });
    this.dialogRef.close(true);
  }
}
