package es.upm.miw.shop.graphql;

import es.upm.miw.shop.services.TagService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TagMutation implements GraphQLMutationResolver {

    private TagService tagService;

    @Autowired
    public TagMutation(TagService tagService) {
        this.tagService = tagService;
    }

    public void deleteTag(String id){
        this.tagService.deleteById(id);
    }

}
