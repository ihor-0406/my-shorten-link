package academy.prog.myshortenlink;

import java.util.List;

public interface UrlService {
    String shortenUrl(String url);
    String getUrlById(Long id);
    List<UrlStatDTO> getStatistics();
    void deleteUrlById(Long id);
}
