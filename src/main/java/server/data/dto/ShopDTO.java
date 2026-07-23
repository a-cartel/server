package server.data.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import server.data.entity.ShopEntity;

@Getter
public class ShopDTO {

    private Long shopId;
    private String shopName;
    private String shopAddress;
    private String shopArea;
    private String shopType;
    private String shopHours;
    private String shopPhone;
    private String shopImg;

    public ShopDTO(ShopEntity entity) {
        this.shopId = entity.getShopId();
        this.shopName = entity.getShopName();
        this.shopAddress = entity.getShopAddress();
        this.shopArea = entity.getShopArea();
        this.shopType = entity.getShopType();
        this.shopHours = entity.getShopHours();
        this.shopPhone = entity.getShopPhone();
        this.shopImg = entity.getShopImg();
    }
}
