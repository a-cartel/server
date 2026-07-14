package server.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import server.auth.entity.userEntity;

public interface userRepository
        extends JpaRepository<userEntity, String> {

    boolean existsByEmail(String email);

    Optional<userEntity> findByEmail(String email);
}