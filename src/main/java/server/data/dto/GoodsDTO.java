package server.data.dto;

import lombok.Getter;
import server.data.entity.GoodsEntity;

import java.time.LocalDate;

@Getter
public class GoodsDTO {

    private Long goodsId;
    private String goodsURL;
    private String goodsTitle;
    private String goodsImg;
    private LocalDate goodsDate;

    public GoodsDTO(GoodsEntity entity) {
        this.goodsId = goodsId;
        this.goodsURL = goodsURL;
        this.goodsTitle = goodsTitle;
        this.goodsImg = goodsImg;
        this.goodsDate = goodsDate;
    }
}
