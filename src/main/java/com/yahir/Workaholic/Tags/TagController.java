package com.yahir.Workaholic.Tags;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tag")
@CrossOrigin
public class TagController {
    
    private final TagRepository repository;
    
    public TagController(TagRepository repository) {
        this.repository = repository;
    }
 
    @GetMapping("/all")
    public List<Tag> showAll() {
        return repository.findAll();
    }

}