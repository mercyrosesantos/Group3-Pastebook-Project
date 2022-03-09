import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  @Output() hasToken: EventEmitter<boolean> = new EventEmitter();

  constructor() { }

  getEmail(): string {
    return localStorage.getItem('email')!;
  }

  setEmail(value: string): void {
    localStorage.setItem('email', value);
  }

  clear(): void {
    localStorage.clear();
  }
}