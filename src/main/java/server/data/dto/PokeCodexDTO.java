package server.data.dto;

import lombok.Getter;
import server.data.entity.PokeCodex;

/**
 * 클라이언트(PokeDexMain.tsx, PokeDexDetail.tsx)가 기대하는 필드명에 맞춘 응답 DTO.
 * 엔티티/DB 컬럼명(pokeId, pokeName ...)과는 다르게, 프런트가 쓰는 이름(id, name ...)으로 여기서 매핑한다.
 */
@Getter
public class PokeCodexDTO {

	private final Integer id;
	private final String name;
	private final String desc;
	private final String type;
	private final String imgUrl;
	private final Integer height;
	private final Integer weight;

	public PokeCodexDTO(PokeCodex entity) {
		this.id = entity.getPokeId();
		this.name = entity.getPokeName();
		this.desc = entity.getPokeDesc();
		this.type = entity.getPokeType();
		this.imgUrl = entity.getPokeImg();
		this.height = entity.getPokeHeight();
		this.weight = entity.getPokeWeight();
	}
}
