package server.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Table(name = "POKE_GOODS")
@Getter
@NoArgsConstructor
public class GoodsEntity {

    @Id
    private Long goodsId;

    private String goodsURL;
    private String goodsTitle;
    private String goodsImg;
    private LocalDate goodsDate;
}
