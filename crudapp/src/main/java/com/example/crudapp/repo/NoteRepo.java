package com.example.crudapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.crudapp.model.Note;

@Repository
public interface NoteRepo extends JpaRepository<Note ,Long> {
    // fetch notes for a speicfic user
    List<Note> findByUserId(long user_id);
}
