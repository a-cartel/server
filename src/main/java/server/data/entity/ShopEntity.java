package server.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POKE_SHOPS")
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
    private String shopAccess;

    @Column(name = "SHOP_TYPE")
    private String shopHoliday;

    @Column(name = "SHOP_OPENTIME")
    private String shopHours;

    @Column(name = "SHOP_PHONE_NUMBER")
    private String shopPhone;

    private String shopImg;

}
