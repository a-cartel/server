package server.data.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import server.data.entity.ShopEntity;

@Getter
public class ShopDTO {

    //  private Long shopId; Id는 굳이 프런트엔드에 전해줄 필요가 없음
    private String shopName;
    private String shopAddress;
    private String shopAccess;
    private String shopHoliday;
    private String shopHours;
    private String shopPhone;
    private String shopImg;

    public ShopDTO(ShopEntity entity) {
        this.shopName = entity.getShopName();
        this.shopAddress = entity.getShopAddress();
        this.shopAccess = entity.getShopAccess();
        this.shopHoliday = entity.getShopHoliday();
        this.shopHours = entity.getShopHours();
        this.shopPhone = entity.getShopPhone();
        this.shopImg = entity.getShopImg();
    }
}
