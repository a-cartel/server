package server.home.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

/**
 * pokemon.co.jp의 내부 API(공지사항/동영상)를 클라이언트가 브라우저에서 직접 호출하면
 * CORS로 막혀서 AxiosError: Network Error가 난다 (그쪽에서 우리 origin을 허용 안 해줌).
 *
 * 그래서 우리 서버가 대신 호출해서 결과를 그대로 클라이언트에 전달하는 프록시.
 * 서버 <-> 서버 요청은 브라우저가 아니라서 CORS 검사 자체가 적용되지 않는다.
 */
@RestController
@RequestMapping("/home")
public class HomeProxyController {

	private static final String NOTICE_URL = "https://www.pokemon.co.jp/api/info/index/?emergency=1";
	private static final String MOVIE_URL = "https://www.pokemon.co.jp/api/movie/?limit=5";

	private final RestClient restClient = RestClient.create();

	@GetMapping("/notices")
	public ResponseEntity<String> notices() {
		return proxy(NOTICE_URL);
	}

	@GetMapping("/movies")
	public ResponseEntity<String> movies() {
		return proxy(MOVIE_URL);
	}

	private ResponseEntity<String> proxy(String url) {

		try {
			String body = restClient.get().uri(url).retrieve().body(String.class);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(body);
		} catch (Exception e) {
			// 외부 API가 응답을 안 주거나 형식이 바뀌어도, 우리 서버가 통째로 죽지 않고 빈 목록으로 응답
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"results\":[]}");
		}
	}
}
