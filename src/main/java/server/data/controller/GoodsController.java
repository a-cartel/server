package server.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.data.dto.GoodsDTO;
import server.data.service.GoodsService;

import java.util.List;

@RestController
@RequestMapping("/Goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping
    public ResponseEntity<List<GoodsDTO>> getAllGoods() {
        List<GoodsDTO> goodsList = goodsService.findAll();

        return ResponseEntity.ok(goodsList);
    }
}