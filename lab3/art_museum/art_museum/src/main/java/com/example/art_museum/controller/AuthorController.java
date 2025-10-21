package com.example.art_museum.controller;

import com.example.art_museum.DTO.Author.AuthorCreateUpdateDTO;
import com.example.art_museum.DTO.Author.AuthorReadDTO;
import com.example.art_museum.entity.Author;
import com.example.art_museum.mapper.AuthorMapper;
import com.example.art_museum.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping
    public List<AuthorReadDTO> getAllAuthors() {
        return authorService.findAll()
                .stream()
                .map(authorMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorReadDTO> getAuthor(@PathVariable UUID id) {
        Optional<Author> authorOpt = authorService.findById(id);
        return authorOpt
                .map(a -> ResponseEntity.ok(authorMapper.toReadDTO(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorReadDTO> createAuthor(@RequestBody AuthorCreateUpdateDTO dto) {
        Author author = authorMapper.toEntity(dto);
        author.setUuid(UUID.randomUUID());
        Author saved = authorService.save(author);
        return new ResponseEntity<>(authorMapper.toReadDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorReadDTO> updateAuthor(@PathVariable UUID id,
                                                      @RequestBody AuthorCreateUpdateDTO dto) {
        Optional<Author> existing = authorService.findById(id);
        if (existing.isPresent()) {
            Author author = authorMapper.toEntity(dto);
            author.setUuid(id);
            Author updated = authorService.save(author);
            return ResponseEntity.ok(authorMapper.toReadDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        Optional<Author> existing = authorService.findById(id);
        if (existing.isPresent()) {
            authorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
