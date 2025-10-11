package com.example.crudapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.crudapp.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

}
