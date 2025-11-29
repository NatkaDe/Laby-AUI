package com.example.art_museum.painting;

import com.example.art_museum.author.SimpleAuthor;
import com.example.art_museum.author.SimpleAuthor;
import com.example.art_museum.author.SimpleAuthorService;
import com.example.art_museum.author.SimpleAuthorService;
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
    private final SimpleAuthorService authorService;
    private final PaintingMapper paintingMapper;

    public PaintingController(PaintingService paintingService, SimpleAuthorService authorService, PaintingMapper paintingMapper) {
        this.paintingService = paintingService;
        this.authorService = authorService;
        this.paintingMapper = paintingMapper;
    }

    @GetMapping
    public ResponseEntity<List<PaintingCollectionDTO>> getPaintingsByAuthor(@PathVariable Integer authorId) {
        System.out.println("getPaintingsByAuthor - id: " + authorId);

        Optional<SimpleAuthor> author = authorService.findById(authorId);
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
    public ResponseEntity<PaintingReadDTO> getPainting(@PathVariable Integer authorId, @PathVariable UUID id) {
        Optional<Painting> paintingOpt = paintingService.findById(id);
        if (paintingOpt.isEmpty() || !paintingOpt.get().getAuthorID().equals(authorId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paintingMapper.toReadDTO(paintingOpt.get()));
    }

    @PostMapping
    public ResponseEntity<PaintingReadDTO> addPainting(@PathVariable Integer authorId,
                                                       @RequestBody PaintingCreateUpdateDTO dto) {
        Optional<SimpleAuthor> authorOpt = authorService.findById(authorId);
        if (authorOpt.isEmpty()) return ResponseEntity.notFound().build();

        Painting painting = paintingMapper.toEntity(dto);
        painting.setAuthorID(authorOpt.get().getId());
        Painting saved = paintingService.save(painting);

        return new ResponseEntity<>(paintingMapper.toReadDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaintingReadDTO> updatePainting(@PathVariable Integer authorId,
                                                          @PathVariable UUID id,
                                                          @RequestBody PaintingCreateUpdateDTO dto) {
        Optional<Painting> existing = paintingService.findById(id);
        if (existing.isEmpty() || !existing.get().getAuthorID().equals(authorId)) {
            return ResponseEntity.notFound().build();
        }

        Painting painting = paintingMapper.toEntity(dto);
        painting.setUuid(id);
        painting.setAuthorID(existing.get().getAuthorID());
        Painting updated = paintingService.save(painting);

        return ResponseEntity.ok(paintingMapper.toReadDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePainting(@PathVariable Integer authorId, @PathVariable UUID id) {
        Optional<Painting> existing = paintingService.findById(id);
        if (existing.isEmpty() || !existing.get().getAuthorID().equals(authorId)) {
            return ResponseEntity.notFound().build();
        }

        paintingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

