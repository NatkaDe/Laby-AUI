package com.example.art_museum.mapper;

import com.example.art_museum.DTO.Author.AuthorCreateUpdateDTO;
import com.example.art_museum.DTO.Author.AuthorReadDTO;
import com.example.art_museum.entity.Author;
import com.example.art_museum.entity.Painting;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorReadDTO toReadDTO(Author author) {
        if (author == null) return null;
        return AuthorReadDTO.builder()
                .uuid(author.getUuid())
                .name(author.getName())
                .year_of_birth(author.getYear_of_birth())
                .year_of_death(author.getYear_of_death())
                .build();
    }

    public AuthorCreateUpdateDTO toCreateUpdateDTO(Author author) {
        if (author == null) return null;
        return AuthorCreateUpdateDTO.builder()
                .name(author.getName())
                .year_of_birth(author.getYear_of_birth())
                .year_of_death(author.getYear_of_death())
                .build();
    }

    public Author toEntity(AuthorCreateUpdateDTO dto) {
        if (dto == null) return null;
        Author author = new Author();
        author.setName(dto.getName());
        author.setYear_of_birth(dto.getYear_of_birth());
        author.setYear_of_death(dto.getYear_of_death());
        return author;
    }
}
