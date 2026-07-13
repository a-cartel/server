package server.data.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsListResponse {
    private List<NewsDTO> results;
    private NewsPaging paging;
}