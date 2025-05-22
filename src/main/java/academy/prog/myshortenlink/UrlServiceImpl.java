package academy.prog.myshortenlink;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    @Transactional
    public String shortenUrl(String url) {
        var opt = urlRepository.findByUrl(url);
        if (opt.isPresent()) {
            return opt.get().getId().toString();
        }

        var record = new UrlRecord(url);
        urlRepository.save(record);

        return record.getId().toString();
    }

    @Override
    @Transactional
    public String getUrlById(Long id) {
        var opt = urlRepository.findById(id);
        if (opt.isEmpty())
            return null;

        var record = opt.get();
        record.setLastAccess(new Date());
        record.setCount(record.getCount() + 1);
        urlRepository.save(record);

        return record.getUrl();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UrlStatDTO> getStatistics() {
        var records = urlRepository.findAll();
        var result = new ArrayList<UrlStatDTO>();

        for (var record : records) {
            result.add(record.toDTO());
        }

        return result;
    }

    @Override
    @Transactional
    public void deleteUrlById(Long id) {
        urlRepository.deleteById(id);
    }
}
