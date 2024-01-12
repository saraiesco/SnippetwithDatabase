package com.example.snippet;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/snippets")
public class SnippetController {
    @Autowired
    private SnippetService snippetService;

    //getAll
    @GetMapping
    public ResponseEntity<List<Snippet>> getAllSnippets(){
        return new ResponseEntity<>(snippetService.allSnippets(), HttpStatus.OK);
    }

    //getOne
    @GetMapping("/{id}")
    public ResponseEntity<Snippet> getSingleSnippet(@PathVariable String id) {
        Snippet snippet = snippetService.singleSnippet(id);
        if (snippet != null) {
            return new ResponseEntity<>(snippet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //post
    @PostMapping
    public ResponseEntity<Snippet> createSnippet(@RequestBody Map<String, Object> payload) {
        String id = (String) payload.get("id");
        String language = (String) payload.get("language");
        String code = (String) payload.get("code");

        Snippet snippet = snippetService.createSnippet(id,language,code) ;
        return new ResponseEntity<Snippet>(snippet, HttpStatus.CREATED);
    }
}
