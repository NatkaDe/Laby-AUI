package com.example.art_museum.controller;

import com.example.art_museum.DTO.Painting.PaintingCollectionDTO;
import com.example.art_museum.DTO.Painting.PaintingCreateUpdateDTO;
import com.example.art_museum.DTO.Painting.PaintingReadDTO;
import com.example.art_museum.entity.Author;
import com.example.art_museum.mapper.PaintingMapper;
import com.example.art_museum.entity.Painting;
import com.example.art_museum.service.AuthorService;
import com.example.art_museum.service.PaintingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors/{authorId}/paintings")
public class PaintingController {

    private final PaintingService paintingService;
    private final AuthorService authorService;
    private final PaintingMapper paintingMapper;

    public PaintingController(PaintingService paintingService, AuthorService authorService, PaintingMapper paintingMapper) {
        this.paintingService = paintingService;
        this.authorService = authorService;
        this.paintingMapper = paintingMapper;
    }

    @GetMapping
    public ResponseEntity<List<PaintingCollectionDTO>> getPaintingsByAuthor(@PathVariable UUID authorId) {
        System.out.println("getPaintingsByAuthor - id: " + authorId);

        Optional<Author> author = authorService.findById(authorId);
        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<PaintingCollectionDTO> dtoList = paintingService.getPaintingsByAuthor(author.get())
                .stream()
                .map(paintingMapper::toCollectionDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingReadDTO> getPainting(@PathVariable UUID authorId, @PathVariable UUID id) {
        Optional<Painting> paintingOpt = paintingService.findById(id);
        if (paintingOpt.isEmpty() || !paintingOpt.get().getAuthor().getUuid().equals(authorId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paintingMapper.toReadDTO(paintingOpt.get()));
    }

    @PostMapping
    public ResponseEntity<PaintingReadDTO> addPainting(@PathVariable UUID authorId,
                                                       @RequestBody PaintingCreateUpdateDTO dto) {
        Optional<Author> authorOpt = authorService.findById(authorId);
        if (authorOpt.isEmpty()) return ResponseEntity.notFound().build();

        Painting painting = paintingMapper.toEntity(dto);
        painting.setAuthor(authorOpt.get());
        Painting saved = paintingService.save(painting);

        return new ResponseEntity<>(paintingMapper.toReadDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaintingReadDTO> updatePainting(@PathVariable UUID authorId,
                                                          @PathVariable UUID id,
                                                          @RequestBody PaintingCreateUpdateDTO dto) {
        Optional<Painting> existing = paintingService.findById(id);
        if (existing.isEmpty() || !existing.get().getAuthor().getUuid().equals(authorId)) {
            return ResponseEntity.notFound().build();
        }

        Painting painting = paintingMapper.toEntity(dto);
        painting.setUuid(id);
        painting.setAuthor(existing.get().getAuthor());
        Painting updated = paintingService.save(painting);

        return ResponseEntity.ok(paintingMapper.toReadDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePainting(@PathVariable UUID authorId, @PathVariable UUID id) {
        Optional<Painting> existing = paintingService.findById(id);
        if (existing.isEmpty() || !existing.get().getAuthor().getUuid().equals(authorId)) {
            return ResponseEntity.notFound().build();
        }

        paintingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

