package com.example.art_museum.mapper;

import com.example.art_museum.entity.Painting;
import com.example.art_museum.DTO.Painting.PaintingCreateUpdateDTO;
import com.example.art_museum.DTO.Painting.PaintingReadDTO;
import com.example.art_museum.DTO.Painting.PaintingCollectionDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaintingMapper {

    public PaintingReadDTO toReadDTO(Painting painting) {
        if (painting == null) return null;
        return PaintingReadDTO.builder()
                .uuid(painting.getUuid())
                .title(painting.getTitle())
                .year(painting.getYear())
                .authorName(painting.getAuthor() != null ? painting.getAuthor().getName() : null)
                .build();
    }

    public PaintingCollectionDTO toCollectionDTO(Painting painting) {
        if (painting == null) return null;
        return PaintingCollectionDTO.builder()
                .uuid(painting.getUuid())
                .title(painting.getTitle())
                .build();
    }

    public PaintingCreateUpdateDTO toCreateUpdateDTO(Painting painting) {
        if (painting == null) return null;
        return PaintingCreateUpdateDTO.builder()
                .title(painting.getTitle())
                .year(painting.getYear())
                .build();
    }

    public Painting toEntity(PaintingCreateUpdateDTO dto) {
        if (dto == null) return null;
        Painting painting = new Painting();
        painting.setUuid(UUID.randomUUID());
        painting.setTitle(dto.getTitle());
        painting.setYear(dto.getYear());
        return painting;
    }
}
