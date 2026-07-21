package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
// Page JSON 직렬화 경고 해결용 코드. 아래 코드를 추가해서 JSON 응답 형태를 PageImpl이 아닌 표준에 가까운 PageModel 형태로 받는것으로 설정하면 해결.
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ServerApplication {

	public static void main(String[] args) {

		//Dotenv setting
		Dotenv dotenv = Dotenv.configure().directory(".").ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

//		SpringApplication.run(ServerApplication.class, args);
	}

}
