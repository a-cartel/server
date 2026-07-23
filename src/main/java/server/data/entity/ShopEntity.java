package server.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POKE_SHOP")
@Getter
@NoArgsConstructor
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOP_ID")
    private Long shopId;

    private String shopName;
    private String shopAddress;

    @Column(name = "SHOP_AREA")
    private String shopArea;

    @Column(name = "SHOP_TYPE")
    private String shopType;

    @Column(name = "SHOP_OPENTIME")
    private String shopHours;

    @Column(name = "SHOP_PHONE_NUMBER")
    private String shopPhone;

    private String shopImg;

}
