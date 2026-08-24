package server.data.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import server.data.dto.PokeCodexDTO;
import server.data.service.PokeCodexService;

// 클라이언트가 호출하는 경로: GET /codex (목록), GET /codex/{id} (상세)
@RestController
@RequestMapping("/codex")
@RequiredArgsConstructor
public class PokeCodexController {

	private final PokeCodexService pokeCodexService;

	@GetMapping
	public ResponseEntity<List<PokeCodexDTO>> getAllCodex() {
		return ResponseEntity.ok(pokeCodexService.getAllCodex());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PokeCodexDTO> getCodexById(@PathVariable Integer id) {
		return ResponseEntity.ok(pokeCodexService.getCodexById(id));
	}
}
