package server.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.data.repository.ShopRepository;

//컨트롤러와 리포지토리 사이에서 데이터 처리를 담당
@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

}
