<script lang="ts">
    import { login } from '$lib/api/auth.js';
    import type { AuthRequest } from '$lib/types/User.js';
    import { setToken } from '$lib/stores/auth.js'
    import { goto } from '$app/navigation';

    let credentials: AuthRequest = { email: '', password: '' };
    let error: string | null = null;
    let loading = false;

    async function handleLogin() {
        loading = true;
        error = null;
        try {
            const res = await login(credentials);
            setToken(res.token);
            goto('/notes');
        } catch (e) {
            error = (e as Error).message;
        } finally {
            loading = false;
        }
    }
</script>

<div class="max-w-md mx-auto p-6 mt-12 border rounded shadow">
    <h1 class="text-2xl font-bold mb-4 text-center">Login</h1>

    {#if error}<p class="text-red-500 mb-2">{error}</p>{/if}

    <form class="flex flex-col gap-3" on:submit|preventDefault={handleLogin}>
        <input placeholder="Email" bind:value={credentials.email} type="email" class="border p-2 rounded" required />
        <input placeholder="Password" bind:value={credentials.password} type="password" class="border p-2 rounded" required />
        <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" disabled={loading}>
            {loading ? 'Logging in...' : 'Login'}
        </button>
    </form>
</div>
