package com.example.art_museum;

import com.example.art_museum.author.Author;
import com.example.art_museum.author.AuthorReadDTO;
import com.example.art_museum.author.AuthorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInit {

    private final AuthorService authorService;

    @Autowired
    public DataInit(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostConstruct

    // data needed to present lab exercises
    public void init() {
        Author hokusai = Author.builder()
                //.id(0)
                .name("Katsushika Hokusai")
                .year_of_birth(1760)
                .year_of_death(1849)
                .build();


        authorService.save(hokusai);

        Author hiroshige = Author.builder()
                //.id(0)
                .name("Utagawa Hiroshige")
                .year_of_birth(1797)
                .year_of_death(1858)
                .build();


        authorService.save(hiroshige);

        Author sotatsu = Author.builder()
                //.id(0)
                .name("Tawaraya Sōtatsu")
                .year_of_birth(1570)
                .year_of_death(1640)
                .build();


        authorService.save(sotatsu);

        System.out.println(sotatsu.getId());
        System.out.println(hiroshige.getId());
        System.out.println(hokusai.getId());
    }
}
