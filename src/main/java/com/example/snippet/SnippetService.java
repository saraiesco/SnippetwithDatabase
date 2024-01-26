package com.example.snippet;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class SnippetService {
    @Autowired
    private SnippetRepository snippetRepository;


    //getAll
    public List<Snippet> allSnippets(){
        System.out.println(snippetRepository.findAll());
        return snippetRepository.findAll();
    }

    //getByID
    public Snippet singleSnippet(String id) {
        return snippetRepository.findById(id);
    }

    //Post
    public Snippet createSnippet( String id,String language, String code){
        Snippet snippet = new Snippet(id, language, code);
        Snippet savedSnippet = snippetRepository.insert(snippet);
        return savedSnippet;
    }


}
