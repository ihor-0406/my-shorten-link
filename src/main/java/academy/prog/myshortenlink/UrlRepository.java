package academy.prog.myshortenlink;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlRecord, Long> {
    Optional<UrlRecord> findByUrl(String url);
}
