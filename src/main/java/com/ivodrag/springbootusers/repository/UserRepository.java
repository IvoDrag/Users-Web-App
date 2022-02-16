package com.ivodrag.springbootusers.repository;

import com.ivodrag.springbootusers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {}
