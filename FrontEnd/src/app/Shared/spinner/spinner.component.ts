import { Component } from '@angular/core';

@Component({
  selector: 'app-spinner',
  templateUrl: './spinner.component.html',
  styles: [
    `
      .spinner-content {
        min-width: 100vw;
        min-height: 100vh;
        height: 100%;
      }

      @keyframes loader-rotate {
        0% {
          transform: rotate(0);
        }

        100% {
          transform: rotate(360deg);
        }
      }

      .loader {
        border-right-color: transparent;
        animation: loader-rotate 1s linear infinite;
      }
    `,
  ],
})
export class SpinnerComponent {}
