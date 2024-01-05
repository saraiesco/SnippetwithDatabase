package com.example.snippet;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snippets")
public class SnippetController {
    @Autowired
    private SnippetService snippetService;

    //getAll
    @GetMapping
    public ResponseEntity<List<Snippet> getAllSnippets(){
        return new ResponseEntity<List<Snippet>>(snippetService.allSnippets(), HttpStatus.OK);
    }

    //getOne
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Snippet>> getSingleClinician(@PathVariable String id){
        return new ResponseEntity<Optional<Snippet>>(snippetService.singleSnippet(id), HttpStatus.OK);
    }

    //post

    }
}
