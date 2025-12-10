package com.example.art_museum.author;

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
    private final AuthorCreateUpdateDTO.AuthorMapper authorMapper;


    public AuthorController(AuthorService authorService, AuthorCreateUpdateDTO.AuthorMapper authorMapper) {
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
    public ResponseEntity<AuthorReadDTO> getAuthor(@PathVariable Integer id) {
        Optional<Author> authorOpt = authorService.findById(id);
        return authorOpt
                .map(a -> ResponseEntity.ok(authorMapper.toReadDTO(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorReadDTO> createAuthor(@RequestBody AuthorCreateUpdateDTO dto) {
        Author author = authorMapper.toEntity(dto);
        if (dto.getId() != null) {
            author.setId(dto.getId());
        } else {
            // Jeśli nie podano, generujemy losowy Integer (prowizorka dla ręcznego ID)
            author.setId((int) (Math.random() * 10000));
        }
        Author saved = authorService.save(author);
        return new ResponseEntity<>(authorMapper.toReadDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorReadDTO> updateAuthor(@PathVariable Integer id,
                                                      @RequestBody AuthorCreateUpdateDTO dto) {
        Optional<Author> existing = authorService.findById(id);
        if (existing.isPresent()) {
            Author author = authorMapper.toEntity(dto);
            author.setId(id);
            Author updated = authorService.save(author);
            return ResponseEntity.ok(authorMapper.toReadDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        Optional<Author> existing = authorService.findById(id);
        if (existing.isPresent()) {
            authorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
