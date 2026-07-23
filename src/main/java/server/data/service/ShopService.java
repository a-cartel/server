package server.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.data.dto.ShopDTO;
import server.data.entity.ShopEntity;
import server.data.repository.ShopRepository;

import java.util.List;
import java.util.stream.Collectors;

//컨트롤러와 리포지토리 사이에서 데이터 처리를 담당
@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<ShopDTO> getAllShops() {
        return shopRepository.findAll().stream()
                .map(ShopDTO::new)
                .collect(Collectors.toList());
    }

    public ShopDTO getShopById(Long id) {
        ShopEntity shopEntity = shopRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("商店が見つかりませんでした。"));
        return new ShopDTO(shopEntity);
    }
}
