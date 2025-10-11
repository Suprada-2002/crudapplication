<script lang="ts">
    import { signup } from '$lib/api/auth.js';
    import type { User } from '$lib/types/User.js';
    import { setToken } from '$lib/stores/auth.js'
    import { goto } from '$app/navigation';

    let user: User = { userName: '', email: '', password: '', phoneNo: '' };
    let error: string | null = null;
    let loading = false;

    async function handleSignup() {
        loading = true;
        error = null;
        try {
            const res = await signup(user);
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
    <h1 class="text-2xl font-bold mb-4 text-center">Signup</h1>

    {#if error}<p class="text-red-500 mb-2">{error}</p>{/if}

    <form class="flex flex-col gap-3" on:submit|preventDefault={handleSignup}>
        <input placeholder="Name" bind:value={user.userName} class="border p-2 rounded" required />
        <input placeholder="Email" bind:value={user.email} type="email" class="border p-2 rounded" required />
        <input placeholder="Phone" bind:value={user.phoneNo} class="border p-2 rounded" />
        <input placeholder="Password" bind:value={user.password} type="password" class="border p-2 rounded" required />
        <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" disabled={loading}>
            {loading ? 'Signing up...' : 'Signup'}
        </button>
    </form>
</div>
