package server.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POKE_USER")
public class userEntity {

    @Id
    @Column(name = "ID", length = 24, nullable = false)
    private String id;

    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(
        name = "CREATED_AT",
        nullable = false,
        insertable = false,
        updatable = false
    )
    private LocalDateTime createdAt;

    public userEntity(
            String id,
            String email,
            String password,
            String name
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}