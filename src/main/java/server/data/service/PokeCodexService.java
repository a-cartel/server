package server.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import server.data.dto.PokeCodexDTO;
import server.data.entity.PokeCodex;
import server.data.repository.PokeCodexRepository;
import server.exception.CustomException;

@Service
@RequiredArgsConstructor
public class PokeCodexService {

	private final PokeCodexRepository pokeCodexRepository;

	public List<PokeCodexDTO> getAllCodex() {
		return pokeCodexRepository.findAll().stream()
				.map(PokeCodexDTO::new)
				.toList();
	}

	public PokeCodexDTO getCodexById(Integer id) {

		PokeCodex entity = pokeCodexRepository.findById(id)
				.orElseThrow(() -> new CustomException.NotFoundException("該当のポケモンが見つかりません。 id=" + id));

		return new PokeCodexDTO(entity);
	}
}
