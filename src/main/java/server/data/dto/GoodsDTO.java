package server.data.dto;

import lombok.Getter;
import server.data.entity.GoodsEntity;

import java.time.LocalDate;

@Getter
public class GoodsDTO {

    private Long goodsId;
    private String goodsUrl;
    private String goodsTitle;
    private String goodsImg;
    private LocalDate goodsDate;

    public GoodsDTO(GoodsEntity entity) {
        this.goodsId = entity.getGoodsId();
        this.goodsUrl = entity.getGoodsUrl();
        this.goodsTitle = entity.getGoodsTitle();
        this.goodsImg = entity.getGoodsImg();
        this.goodsDate = entity.getGoodsDate();
    }
}
