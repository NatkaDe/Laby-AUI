package com.example.art_museum.painting;

import com.example.art_museum.author.SimpleAuthor;
import com.example.art_museum.author.SimpleAuthor;
import jakarta.transaction.Transactional;
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

    public List<Painting> getPaintingsByAuthor(SimpleAuthor author) {
        return paintingRepository.findByAuthorID(author.getId());
    }

    public Painting save(Painting painting) {
        return paintingRepository.save(painting);
    }


    public void delete(Painting painting) {
        paintingRepository.delete(painting);
    }

    public Optional<Painting> findById(UUID id) { return paintingRepository.findById(id);}

    public void deleteById(UUID id) { paintingRepository.deleteById(id);}


    @Transactional
    public void deletePaintingsByAuthorId(Integer id) {
        paintingRepository.deleteAllByAuthorID(id);
    }
}
