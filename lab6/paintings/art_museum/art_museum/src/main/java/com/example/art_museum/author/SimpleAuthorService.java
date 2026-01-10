package com.example.art_museum.author;

import com.example.art_museum.author.SimpleAuthor;
import com.example.art_museum.author.SimpleAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimpleAuthorService {
    private final SimpleAuthorRepository authorRepository;

    public SimpleAuthorService(SimpleAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public SimpleAuthor save(SimpleAuthor author) {
        return authorRepository.save(author);
    }

    public List<SimpleAuthor> findAll() { return authorRepository.findAll();}

    public Optional<SimpleAuthor> findById(Integer id) { return authorRepository.findById(id);}

    public void deleteById(Integer id) { authorRepository.deleteById(id);}
}
