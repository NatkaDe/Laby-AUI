package com.example.art_museum.service;

import com.example.art_museum.entity.Author;
import com.example.art_museum.entity.Painting;
import com.example.art_museum.repository.PaintingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaintingService {

    private final PaintingRepository paintingRepository;

    public PaintingService(PaintingRepository paintingRepository) {
        this.paintingRepository = paintingRepository;
    }

    public List<Painting> findAll() {
        return paintingRepository.findAll();
    }

    public List<Painting> getPaintingsByAuthor(Author author) {
        return paintingRepository.findByAuthor(author);
    }

    public Painting save(Painting painting) {
        return paintingRepository.save(painting);
    }


    public void delete(Painting painting) {
        paintingRepository.delete(painting);
    }

    public Optional<Painting> findById(UUID id) { return paintingRepository.findById(id);}

    public void deleteById(UUID id) { paintingRepository.deleteById(id);}
}
