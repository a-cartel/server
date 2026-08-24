package server.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "POKE_CODEX")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokeCodex {

    @Id
    @Column(name = "POKE_ID")
    private Integer pokeId;

    @Column(name = "POKE_DESC", length = 2000)
    private String pokeDesc;

    @Column(name = "POKE_HEIGHT")
    private Integer pokeHeight;

    @Column(name = "POKE_IMG", length = 1000)
    private String pokeImg;

    @Column(name = "POKE_NAME", nullable = false, length = 200)
    private String pokeName;

    @Column(name = "POKE_TYPE", length = 100)
    private String pokeType;

    @Column(name = "POKE_WEIGHT")
    private Integer pokeWeight;
}