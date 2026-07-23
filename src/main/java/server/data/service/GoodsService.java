package server.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.data.dto.GoodsDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public List<GoodsDTO> findAll() {
        return goodsRepository.findAll().stream()
                .map(GoodsDTO::new)
                .toList();
    }
}