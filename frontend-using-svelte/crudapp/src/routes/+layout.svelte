<script>
  import '../app.css'
  import { isLoggedIn } from '$lib/stores/auth.js';
  import { goto } from '$app/navigation';
  import { onMount } from 'svelte';
  import Navbar from "$lib/components/Navbar.svelte";

  let { children } = $props();

  onMount(() => {
    if (!$isLoggedIn) {
      goto('/login'); 
    }
  });
</script>

<Navbar/>
{@render children()}