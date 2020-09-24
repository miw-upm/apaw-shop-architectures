package es.upm.miw.shop.domain.out_ports;

import es.upm.miw.shop.domain.models.Tag;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TagPersistence {

    Tag readById(String id);

    void deleteById(String id);

    Stream<Tag> readAll();
}
