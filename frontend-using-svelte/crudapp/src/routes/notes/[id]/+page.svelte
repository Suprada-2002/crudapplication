<script lang="ts">
  import { page } from '$app/stores'
  import { onMount } from 'svelte'
  import { getAllNotes, updateNote } from '$lib/api/notes.js'
  import type { Note } from '$lib/types/Note.js'
  import { goto } from '$app/navigation'

  let noteId = Number($page.params.id)
  let note: Partial<Note> = { title: '', description: '', content: '' }
  let error: string | null = null

  async function loadNote() {
    try {
      const notes = await getAllNotes()
      const found = notes.find(n => n.id === noteId)
      if (found) note = { ...found }
      else throw new Error('Note not found')
    } catch (err) {
      error = (err as { message: string }).message
    }
  }

  async function handleUpdate() {
    try {
      await updateNote(noteId, note)
      goto('/notes')
    } catch (err) {
      error = (err as { message: string }).message
    }
  }

  onMount(loadNote)
</script>

<div class="container mx-auto">
  <h1 class="text-3xl font-bold mb-6 text-center">Edit Note</h1>

  {#if error}
    <p class="text-red-500 mb-4">{error}</p>
  {/if}

  <form class="flex flex-col gap-3" on:submit|preventDefault={handleUpdate}>
    <label for="Title">Title: </label>
    <input class="border p-2 rounded" placeholder="Title" bind:value={note.title} required />
     <label for="Title">Description: </label>
    <input class="border p-2 rounded" placeholder="Description" bind:value={note.description} />
     <label for="Title">Content: </label>
    <textarea class="border h-70 p-2 rounded" placeholder="Content" bind:value={note.content} required></textarea>
    <div class="flex gap-2">
      <button class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">Update</button>
      <button class="bg-gray-400 text-white px-4 py-2 rounded" type="button" on:click={() => goto('/notes')}>Cancel</button>
    </div>
  </form>
</div>
