import type { Note } from "$lib/types/Note.js";
import { apiFetch } from "./client.js";

// Get all notes
export async function getAllNotes(): Promise<Note[]> {
    return apiFetch<Note[]>('/notes');
}

// get note by user id
export async function getNoteByUserId():Promise<Note[]> {
    return apiFetch<Note[]>(`/users/user}`);
}

// create a note
export async function createNote(note: Omit<Note, 'id' | 'createdAt' | 'user'>){
  return apiFetch<Note>('/notes/addnote/', {
    method: 'POST',
    body: JSON.stringify(note)
  })
}

// update a note
export async function updateNote(
  noteId: number,
  note: Partial<Note>
): Promise<Note> {
  return apiFetch<Note>(`/notes/${noteId}`, {
    method: 'PUT',
    body: JSON.stringify(note)
  })
}

// delete a note
export async function deleteNote(id: number): Promise<{message: string}>{
    return apiFetch<{message: string}>(`notes/${id}`, {
        method: 'DELETE'
    })
}