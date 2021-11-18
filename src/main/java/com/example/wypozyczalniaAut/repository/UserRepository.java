package com.example.wypozyczalniaAut.repository;

import com.example.wypozyczalniaAut.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);


    public User findByPassword(String password);

    public User findByName(String name);
}
