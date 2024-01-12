package com.example.snippet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;
@Data
@Document(collection = "Snippets")
@NoArgsConstructor
@AllArgsConstructor
public class Snippet {
    @Id
    private ObjectId objectId;
    private String id;
    private String language;
    private String code;

    public Snippet(String id,String language, String code) {
        this.id = id;
        this.language = language;
        this.code = code;
    }
}
