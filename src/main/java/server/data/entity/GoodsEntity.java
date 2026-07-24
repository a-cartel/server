package server.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "POKE_GOODS")
@Getter
@NoArgsConstructor
public class GoodsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;

    private String goodsUrl;
    private String goodsTitle;
    private String goodsImg;
    private LocalDate goodsDate;
}
