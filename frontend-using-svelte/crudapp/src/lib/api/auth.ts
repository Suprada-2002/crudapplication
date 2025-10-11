import type { AuthRequest, AuthResponse, User } from '$lib/types/User.js';

const API_URL = 'http://localhost:8080/api/auth';

export async function signup(user: User): Promise<AuthResponse> {
    const res = await fetch(`${API_URL}/signup`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });

    if (!res.ok) throw new Error('Signup failed');
    return res.json();
}

export async function login(credentials: AuthRequest): Promise<AuthResponse> {
    const res = await fetch(`${API_URL}/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credentials)
    });

    if (!res.ok) throw new Error('Login failed');
    return res.json();
}
