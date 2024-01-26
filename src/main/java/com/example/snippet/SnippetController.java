package com.example.snippet;

//for seeding
import org.springframework.core.io.ResourceLoader;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
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


    @Value("${encryption.password}")
    private String encryptionPass;

    @Value("${encryption.salt}")
    private String encryptionSalt;
    //getAll
    @GetMapping
    public ResponseEntity<List<Snippet>> getAllSnippets(){
        //for seeding
        // File snippetResource = resourceLoader.getResource("classpath:seedData.json").getFile();
        List<Snippet> snippets = snippetService.allSnippets();
        TextEncryptor encryptor = Encryptors.text(encryptionPass, encryptionSalt);
        for (Snippet snippet : snippets) {
            String decryptedCode = encryptor.decrypt(snippet.getCode());
            System.out.println(decryptedCode);
            snippet.setCode(decryptedCode);
        }
        return new ResponseEntity<>(snippetService.allSnippets(), HttpStatus.OK);
    }

    //getOne
    @GetMapping("/{id}")
    public ResponseEntity<Snippet> getSingleSnippet(@PathVariable String id) {
        Snippet snippet = snippetService.singleSnippet(id);
        if (snippet != null) {
            TextEncryptor encryptor = Encryptors.text(encryptionPass, encryptionSalt);
            String decryptedCode = encryptor.decrypt(snippet.getCode());
            snippet.setCode(decryptedCode);
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
        TextEncryptor encryptor = Encryptors.text(encryptionPass, encryptionSalt);
        String encryptedCode = encryptor.encrypt(code);


        Snippet snippet = snippetService.createSnippet(id,language,code) ;
        return new ResponseEntity<Snippet>(snippet, HttpStatus.CREATED);
    }
}
