package server.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.data.dto.GoodsDTO;
import server.data.entity.GoodsEntity;
import server.data.repository.GoodsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public List<GoodsDTO> findAll() {
        List<GoodsEntity> goodsEntities = goodsRepository.findAll();

        return goodsRepository.findAll().stream()
                .map(GoodsDTO::new)
                .toList();
    }
}