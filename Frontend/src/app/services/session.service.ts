import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  @Output() hasToken: EventEmitter<boolean> = new EventEmitter();

  constructor() {
    if (localStorage.getItem('token') !== null) {
      this.hasToken.emit(true);
    } else {
      this.hasToken.emit(false);
    }
  }

  getToken(): string {
    return localStorage.getItem('token')!;
  }

  getFirstName(): string {
    return localStorage.getItem('firstName')!;
  }

  getLastName(): string {
    return localStorage.getItem('lastName')!;
  }

  setToken(value: string): void {
    this.hasToken.emit(true);
    localStorage.setItem('token', value);
  }

  setFirstName(value: string): void {
    localStorage.setItem('firstName', value);
  }

  setLastName(value: string): void {
    localStorage.setItem('lastName', value);
  }

  clear(): void {
    localStorage.clear();
    this.hasToken.emit(false);
  }
}
