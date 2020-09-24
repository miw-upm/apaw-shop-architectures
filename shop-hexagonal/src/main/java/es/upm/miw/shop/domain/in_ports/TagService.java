package es.upm.miw.shop.domain.in_ports;

import es.upm.miw.shop.domain.models.Tag;

import java.util.stream.Stream;

public interface TagService {

    Tag read(String id);

    void delete(String id);

    Stream<Tag> findByArticlesInShoppingCarts();
}