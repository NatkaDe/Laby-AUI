package com.example.art_museum.author;

import com.example.art_museum.painting.PaintingService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class SimpleAuthorController {

    private final PaintingService paintingService;
    private final SimpleAuthorRepository authorRepository;

    public SimpleAuthorController(PaintingService paintingService, SimpleAuthorRepository authorRepository) {
        this.paintingService = paintingService;
        this.authorRepository = authorRepository;
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorWithPaintings(@PathVariable Integer id) {
        System.out.println("Otrzymano żądanie usunięcia autora o ID: " + id);

        paintingService.deletePaintingsByAuthorId(id);

        if(authorRepository.existsById(id)){
            authorRepository.deleteById(id);
        }
    }
}
