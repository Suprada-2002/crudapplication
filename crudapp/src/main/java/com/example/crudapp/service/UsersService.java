package com.example.crudapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.crudapp.model.Users;
import com.example.crudapp.repo.UsersRepo;

@Service
public class UsersService {

    private final UsersRepo usersRepo;

    // constructor injection
    public UsersService(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
    }

    public ResponseEntity<List<Users>> getAllUser(){
       List<Users> users = usersRepo.findAll();
       if(users.isEmpty()){
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       }
       return ResponseEntity.ok(users);
    }

    public ResponseEntity<Users> getUserById(Long userId){
     Optional<Users> user = usersRepo.findById(userId);
     if(user.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     }
     return ResponseEntity.ok(user.get());
    }

    public ResponseEntity<?> creatUsers(Users user){
        try{
        Users createdUser = usersRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }catch(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Creating User: "+e.getMessage());
        }
    }

    public ResponseEntity<String> deleteUser(Long id){
        Optional<Users> userRes = usersRepo.findById(id);
        if(userRes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("User not found with id: " + id);
        }
        usersRepo.deleteById(id);
        return ResponseEntity.ok("User Deleted Succesfully!");
    }

    public ResponseEntity<?> updateUser(Long id, Users updatedUser){
        Optional<Users> existingUser = usersRepo.findById(id);
        if(existingUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found with id: "+id);
        }
        Users user = existingUser.get();
        user.setUserName(updatedUser.getUserName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNo(updatedUser.getPhoneNo());
        Users savedUser = usersRepo.save(user);
        return ResponseEntity.ok(savedUser);
    }
}

