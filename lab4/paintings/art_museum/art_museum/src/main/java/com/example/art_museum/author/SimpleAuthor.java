package com.example.art_museum.author;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SimpleAuthor {

    @Id
    private Integer id;

    private String name;

    private Integer yearOfBirth;
    private Integer yearOfDeath;

}

