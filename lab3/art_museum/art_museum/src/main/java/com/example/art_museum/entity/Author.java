package com.example.art_museum.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Authors")
public class Author implements Comparable<Author>, Serializable {

    @Id
    private UUID uuid;

    @Column(name = "author_name")
    private String name;

    @Column(name = "year_of_birth")
    private int year_of_birth;

    @Column(name = "year_of_death")
    private int year_of_death;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Painting> paintings;

    @Override
    public String toString() {
        StringBuilder author = new StringBuilder(name + "(" + year_of_birth + " - " + year_of_death + "): " + "\n");
        for(Painting painting : paintings){
            author.append("  - ").append(painting.getTitle()).append(" (").append(painting.getYear()).append(")\n");
        }
        return author.toString();
    }

    @Override
    public int compareTo(Author other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author other = (Author) o;
        return uuid != null && uuid.equals(other.uuid);
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }
}
