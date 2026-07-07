package com.example.server.auth.repository;

import com.example.server.auth.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<userEntity, Long> {

}