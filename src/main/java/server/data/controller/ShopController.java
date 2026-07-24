package server.data.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.data.dto.ShopDTO;
import server.data.service.ShopService;

import java.util.List;
import java.util.stream.Collectors;

// 프런트엔드에서 axios(/shop)요청이 왔을때 안내되는 곳
@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public ResponseEntity<List<ShopDTO>> getAllShops() {
        // news와 달리 페이지네이션은 클라이언트 사이드에 맡기기
        List<ShopDTO> shopList = shopService.getAllShops();

        return ResponseEntity.ok(shopList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> getShopById(@PathVariable Long id) {
        ShopDTO shopDTO = shopService.getShopById(id);

        return ResponseEntity.ok(shopDTO);
    }
}






