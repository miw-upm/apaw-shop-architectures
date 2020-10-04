package es.upm.miw.shop.rest;

import es.upm.miw.shop.rest.dtos.Tag;
import es.upm.miw.shop.services.exceptions.BadRequestException;
import es.upm.miw.shop.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(TagResource.TAGS)
public class TagResource {
    static final String TAGS = "/tags";
    static final String ID_ID = "/{id}";
    static final String SEARCH = "/search";

    private TagService tagService;

    @Autowired
    public TagResource(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(ID_ID)
    public Tag read(@PathVariable String id) {
        return new Tag(this.tagService.readById(id));
    }

    @DeleteMapping(ID_ID)
    public void delete(@PathVariable String id) {
        this.tagService.deleteById(id);
    }

    @GetMapping(SEARCH)
    public Stream<Tag> findByArticlesInShoppingCarts(@RequestParam String q) {
        if (!"in".equals(new LexicalAnalyzer().extractWithAssure(q, "shopping-carts"))) {
            throw new BadRequestException("q incorrect, expected in");
        }
        return this.tagService.findByArticlesInShoppingCarts()
                .map(Tag::new);
    }
}
