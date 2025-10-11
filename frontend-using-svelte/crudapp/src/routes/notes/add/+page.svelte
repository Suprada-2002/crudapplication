<script lang="ts">
  import { createNote } from "$lib/api/notes.js";
  import type { Note } from "$lib/types/Note.js";


async function handleAdd(newNote : Note){
    try{
        await createNote(1, newNote)
    }
}
</script>

<section class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Add Notes</h1>
</section>