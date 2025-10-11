package com.example.crudapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crudapp.model.Note;
import com.example.crudapp.service.NoteService;

@Controller
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService; 
    }

    // get all notes irrespective of user
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        return noteService.getAllNotes();
    }

    // Get Notes for a user by id
   @GetMapping("/user")
    public ResponseEntity<List<Note>> getNoteByUserId(){
        return noteService.getNoteByUserId();
    }

    @PostMapping("/addnote")
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        return noteService.createNote(note);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId){
        return noteService.deleteNote(noteId);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<?> updateNote(@PathVariable Long noteId, @RequestBody Note note){
        return noteService.updateNote(noteId, note);
    }
}
