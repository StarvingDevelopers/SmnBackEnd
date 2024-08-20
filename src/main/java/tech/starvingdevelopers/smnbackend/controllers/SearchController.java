package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.search.output.SearchResultDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Group;
import tech.starvingdevelopers.smnbackend.models.entities.Profile;
import tech.starvingdevelopers.smnbackend.services.SearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{key}")
    public ResponseEntity<SearchResultDTO> search(@PathVariable String key) {
        List<Group> groups = this.searchService.searchGroups(key);
        List<Profile> profiles = this.searchService.searchProfiles(key);

        SearchResultDTO result = new SearchResultDTO(groups, profiles);
        return ResponseEntity.ok(result);
    }
}
