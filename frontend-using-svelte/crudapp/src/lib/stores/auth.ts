import { writable } from 'svelte/store';

export const token = writable<string | null>(null);

export const isLoggedIn = writable<boolean>(false);

export function logout() {
    token.set(null);
    isLoggedIn.set(false);
    localStorage.removeItem('jwt_token');
}

export function setToken(t: string) {
    token.set(t);
    isLoggedIn.set(true);
    localStorage.setItem('jwt_token', t);
}

// initialize from localStorage
const saved = localStorage.getItem('jwt_token');
if (saved) {
    setToken(saved);
}
