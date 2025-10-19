package org.example;

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

    private String title;
    private int year;

    //@ManyToOne
    //@JoinColumn(name = "author")
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
