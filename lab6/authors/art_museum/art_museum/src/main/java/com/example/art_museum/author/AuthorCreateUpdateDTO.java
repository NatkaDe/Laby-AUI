package com.example.art_museum.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorCreateUpdateDTO {


    public Integer id;
    public String name;
    public int year_of_birth;
    public int year_of_death;

    @Component
    public static class AuthorMapper {

        public AuthorReadDTO toReadDTO(Author author) {
            if (author == null) return null;
            return AuthorReadDTO.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .year_of_birth(author.getYear_of_birth())
                    .year_of_death(author.getYear_of_death())
                    .build();
        }

        public AuthorCreateUpdateDTO toCreateUpdateDTO(Author author) {
            if (author == null) return null;
            return AuthorCreateUpdateDTO.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .year_of_birth(author.getYear_of_birth())
                    .year_of_death(author.getYear_of_death())
                    .build();
        }

        public Author toEntity(AuthorCreateUpdateDTO dto) {
            if (dto == null) return null;
            Author author = new Author();

            if (dto.getId() != null) {
                author.setId(dto.getId());
            }

            author.setName(dto.getName());
            author.setYear_of_birth(dto.getYear_of_birth());
            author.setYear_of_death(dto.getYear_of_death());
            return author;
        }
    }
}