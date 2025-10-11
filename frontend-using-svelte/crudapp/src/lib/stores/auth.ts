import { writable } from 'svelte/store';
import { browser } from '$app/environment';

export const token = writable<string | null>(null);
export const isLoggedIn = writable<boolean>(false);

export function logout() {
    token.set(null);
    isLoggedIn.set(false);
    if (browser) localStorage.removeItem('jwt_token');
}

export function setToken(t: string) {
    token.set(t);
    isLoggedIn.set(true);
    if (browser) localStorage.setItem('jwt_token', t);
}

// initialize from localStorage only in browser
if (browser) {
    const saved = localStorage.getItem('jwt_token');
    if (saved) {
        setToken(saved);
    }
}
