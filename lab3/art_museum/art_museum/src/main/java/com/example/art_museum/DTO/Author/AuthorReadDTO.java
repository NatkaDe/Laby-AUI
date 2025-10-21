package com.example.art_museum.DTO.Author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthorReadDTO {
    private UUID uuid;
    private String name;
    private int year_of_birth;
    private int year_of_death;
}
