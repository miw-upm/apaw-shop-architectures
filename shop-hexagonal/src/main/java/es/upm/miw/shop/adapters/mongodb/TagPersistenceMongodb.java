package es.upm.miw.shop.adapters.mongodb;

import es.upm.miw.shop.domain.exceptions.NotFoundException;
import es.upm.miw.shop.domain.models.Tag;
import es.upm.miw.shop.domain.out_ports.TagPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("tagPersistence")
public class TagPersistenceMongodb implements TagPersistence {

    private TagRepository tagRepository;

    @Autowired
    public TagPersistenceMongodb(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Stream<Tag> readAll() {
        return this.tagRepository.findAll().stream()
                .map(TagEntity::toTag);
    }

    @Override
    public Tag readById(String id) {
        return this.tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(" Tag id: " + id))
                .toTag();
    }

    @Override
    public void deleteById(String id) {
        this.tagRepository.deleteById(id);
    }
}
