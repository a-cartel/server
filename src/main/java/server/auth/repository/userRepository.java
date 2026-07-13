package server.auth.repository;

import server.auth.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<userEntity, Long> {

}