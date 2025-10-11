package com.example.crudapp.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.crudapp.model.Note;
import com.example.crudapp.model.Users;
import com.example.crudapp.repo.NoteRepo;
import com.example.crudapp.repo.UsersRepo;

@Service
public class NoteService {
    
    private final NoteRepo noteRepo;
    private final UsersRepo usersRepo;

    public NoteService(NoteRepo noteRepo, UsersRepo usersRepo){
        this.noteRepo = noteRepo;
        this.usersRepo= usersRepo;
    }

    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> notes = noteRepo.findAll();

        if(notes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(notes);
    }

    public ResponseEntity<List<Note>>  getNoteByUserId(Long userId){
        List<Note> notes = noteRepo.findByUserId(userId);
        if(notes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(notes);
    }

    public ResponseEntity<Note> createNote(Note note, Long userId){
        Optional<Users> user = usersRepo.findById(userId);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        note.setUser(user.get());
        note.setCreatedAt(java.time.LocalDateTime.now());
        Note savedNote = noteRepo.save(note);
         return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }

    public ResponseEntity<String> deleteNote(Long noteId){
        Optional<Note> note = noteRepo.findById(noteId);
        if(note.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Noe not found with id: "+ noteId);
        }
        noteRepo.deleteById(noteId);
        return ResponseEntity.ok("Note deleted Succesfully!");
    }


    public ResponseEntity<?> updateNote(Long noteId, Note updatedNote){
        Optional<Note> existingNote = noteRepo.findById(noteId);
        if(existingNote.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found with id: "+ noteId);
        }  
        Note note = existingNote.get();
        note.setTitle(updatedNote.getTitle());
        note.setDescription(updatedNote.getDescription());
        note.setContent(updatedNote.getContent());
        Note savedNote = noteRepo.save(note);
        return ResponseEntity.ok(savedNote);
    }
}
