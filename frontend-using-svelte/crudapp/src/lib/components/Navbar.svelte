<script lang="ts">
import { isLoggedIn, logout } from '$lib/stores/auth.js';
import { goto } from '$app/navigation';

function handleLogout() {
    logout();
    goto('/login');
}
</script>

<nav class="flex gap-4 p-4 bg-gray-100">
  {#if $isLoggedIn}
    <a href="/notes" class="hover:underline">My Notes</a>
    <button onclick={handleLogout} class="ml-auto text-red-600">Logout</button>
  {:else}
    <a href="/login" class="hover:underline">Login</a>
    <a href="/signup" class="hover:underline">Signup</a>
  {/if}
</nav>