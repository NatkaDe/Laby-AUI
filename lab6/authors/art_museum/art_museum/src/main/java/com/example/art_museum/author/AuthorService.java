package com.example.art_museum.author;

import com.example.art_museum.author.Author;

import com.example.art_museum.author.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final RestTemplate restTemplate;

    private final String PAINTING_SERVICE_URL = "http://localhost:8082/authors/";

    public AuthorService(AuthorRepository authorRepository, RestTemplate restTemplate) {
        this.authorRepository = authorRepository;
        this.restTemplate = restTemplate;
    }


    //public Author save(Author author) {
        //return authorRepository.save(author);
    //}

    public List<Author> findAll() { return authorRepository.findAll();}

    public Optional<Author> findById(Integer id) { return authorRepository.findById(id);}

    //public void deleteById(Integer id) { authorRepository.deleteById(id);}

    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
        try {
            restTemplate.delete(PAINTING_SERVICE_URL + id);
            System.out.println("Wysłano żądanie usunięcia ID: " + id);
        } catch (Exception e) {
            System.err.println("Błąd synchronizacji: " + e.getMessage());
        }
    }

    public Author save(Author author) {
        Author savedAuthor = authorRepository.save(author);
        try {
            restTemplate.postForLocation("http://localhost:8082/authors", savedAuthor.getId());
        } catch (Exception e) {
            System.err.println("Nie udało się powiadomić serwisu Painting: " + e.getMessage());
        }

        return savedAuthor;
    }

}
