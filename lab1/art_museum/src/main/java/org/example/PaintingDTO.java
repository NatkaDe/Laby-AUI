package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jdk.jfr.internal.MirrorEvent;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaintingDTO implements Comparable<PaintingDTO>, Serializable {

    @Id
    private UUID uuid;
    private String title;
    private int year;
    private String authorName;

    @Override
    public int compareTo(PaintingDTO other) {
        int comp = this.title.compareTo(other.title);
        if (comp != 0) return comp;
        return this.authorName.compareTo(other.authorName);
    }

    @Override
    public String toString() {
        StringBuilder painting = new StringBuilder(title + " (" + year + ") by " + authorName + " (DTO version)");
        return painting.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaintingDTO)) return false;
        PaintingDTO p = (PaintingDTO) o;
        return uuid != null && uuid.equals(p.uuid);
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }
}
