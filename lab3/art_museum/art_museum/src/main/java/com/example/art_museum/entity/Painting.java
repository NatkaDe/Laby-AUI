package com.example.art_museum.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Paintings")
public class Painting implements Comparable<Painting>, Serializable {

    @Id
    private UUID uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "painting_year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private Author author;

    @Override
    public int compareTo(Painting other) {
        int comp = this.title.compareTo(other.title);
        if (comp != 0) return comp;
        return this.author.compareTo(other.author);
    }

    @Override
    public String toString() {
        StringBuilder painting = new StringBuilder(title + " (" + year + ") by " + author.getName());
        return painting.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Painting)) return false;
        Painting p = (Painting) o;
        return uuid != null && uuid.equals(p.uuid);
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }
}
