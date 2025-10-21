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

public class AuthorCreateUpdateDTO {
    public String name;
    public int year_of_birth;
    public int year_of_death;
}
