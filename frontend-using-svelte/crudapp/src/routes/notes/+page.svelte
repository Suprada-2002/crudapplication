<script lang="ts">
  import { deleteNote, getAllNotes } from "$lib/api/notes.js";
  import type { Note } from "$lib/types/Note.js";
  import { onMount } from "svelte";
import { goto } from "$app/navigation";

let loading = $state(false)
let notes: Note[] = $state([])

async function loadNotes(){
    loading = true;
    try{
        notes = await getAllNotes()
        console.log(notes)
    }catch(err){
        console.log("Error occured while loading notes: ", err);
    }finally{
        loading = false
    }
}

async function handleDelete(id: number){
     if (!confirm('Are you sure you want to delete this note?')) return
    try{
        await deleteNote(id)
        await loadNotes()
    }catch(err){
        console.log("Error occured while deleting note: ", err)
    }
} 
onMount(loadNotes)
</script>

<section class="container mx-auto">
    <h1 class="text-3xl font-bold mb-6 text-center">All Notes</h1>
   <button
      class="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
      onclick={() => goto('/notes/add')}
    >
      Add Note
    </button>

    {#if loading}
    <p>Loading you notes....</p>
    {:else if notes.length === 0}
    <p>No notes available.</p>
    {:else}
    <ul class="py-5">
        {#each notes as note}
        <li class="flex justify-between border-b border-gray-300 p-2">
            <div>
            <h2 class="font-semibold text-lg">{note.title}</h2>
            <p class="text-gray-600 italic">{note.description}</p>
            <p class="text-gray-700 whitespace-pre-line">{note.content}</p>
            </div>
            <div class="flex gap-3 items-center justify-center">
                <button
              class="bg-yellow-500 text-white px-2 py-1 rounded"
              onclick={() => goto(`/notes/${note.id}`)}
            >
              Edit
            </button>
            <button
              class="bg-red-500 text-white px-2 py-1 rounded"
              onclick={() => handleDelete(note.id)}
            >
              Delete
            </button>
            </div>
        </li>
        {/each}
    </ul>
    {/if}

</section>