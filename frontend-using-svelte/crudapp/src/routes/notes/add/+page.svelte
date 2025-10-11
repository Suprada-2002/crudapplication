<script lang="ts">
  import { writable } from 'svelte/store';
  import { goto } from '$app/navigation';
  import type { Note } from '$lib/types/Note.js';
  import { createNote } from '$lib/api/notes.js';

  // reactive form state
  let note: {
    title: string;
    description: string;
    content: string;
  } = {
    title: '',
    description: '', // no undefined
    content: ''
  };

  const error = writable<string | null>(null);
  const loading = writable(false);

  async function handleAdd(event: Event) {
    event.preventDefault(); // prevent page reload
    loading.set(true);
    error.set(null);

    try {
      // create note without userId because backend uses logged-in user
      await createNote({
        title: note.title!,       
        description: note.description,
        content: note.content!
      });

      // redirect to notes list
      goto('/notes');
    } catch (err) {
      console.log("Error Creating NOte: ",err)
      error.set((err as { message: string }).message || 'Error creating note');
    } finally {
      loading.set(false);
    }
  }
</script>

<section class="container mx-auto p-8 max-w-2xl">
  <h1 class="text-3xl font-bold mb-6 text-center">Add Note</h1>

  {#if $error}
    <p class="text-red-500 mb-4">{$error}</p>
  {/if}

  <form class="flex flex-col gap-3" onsubmit={handleAdd}>
    <input
      class="border p-2 rounded"
      placeholder="Title"
      bind:value={note.title}
      required
    />

    <input
      class="border p-2 rounded"
      placeholder="Description"
      bind:value={note.description}
    />

    <textarea
      class="border p-2 rounded"
      placeholder="Content"
      bind:value={note.content}
      required
    ></textarea>

    <div class="flex gap-2 mt-2">
      <button
        type="submit"
        class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        disabled={$loading}
      >
        {$loading ? 'Adding...' : 'Add Note'}
      </button>

      <button
        type="button"
        class="bg-gray-400 text-white px-4 py-2 rounded"
        onclick={() => goto('/notes')}
      >
        Cancel
      </button>
    </div>
  </form>
</section>
