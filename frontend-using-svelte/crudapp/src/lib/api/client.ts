import { get } from 'svelte/store';
import { token } from '$lib/stores/auth.js';
import { browser } from '$app/environment';

const API_BASE = 'http://localhost:8080/api';

export interface ApiError {
    status: number;
    message: string;
}

export async function apiFetch<T>(
    endpoint: string,
    options: RequestInit = {}
): Promise<T> {
    try {
        const headers: Record<string, string> = {
            'Content-Type': 'application/json',
            ...(options.headers && typeof options.headers === 'object' && !Array.isArray(options.headers)
                ? options.headers as Record<string, string>
                : {})
        };

        if (browser) {
            const t = get(token);
            if (t) headers['Authorization'] = `Bearer ${t}`;
        }

        const res = await fetch(`${API_BASE}${endpoint}`, {
            ...options,
            headers
        });

        if (!res.ok) {
            const message = await res.text();
            throw { status: res.status, message: message || res.statusText } as ApiError;
        }

        if (res.status === 204) return undefined as unknown as T;

        return (await res.json()) as T;
    } catch (err: unknown) {
        console.error('API fetch error:', err);
        if (typeof err === 'object' && err !== null && 'status' in err) {
            throw err as ApiError;
        }
        throw { status: 500, message: 'Network or server error' } as ApiError;
    }
}
