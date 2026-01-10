package com.example.art_museum.painting;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.boot.spi.SessionFactoryOptions;

import java.io.Serializable;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Paintings")
public class Painting implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "painting_year")
    private int year;

    @JoinColumn(name = "author", nullable = false)
    private Integer authorID;

//    @Override
//    public int compareTo(Painting other) {
//        int comp = this.title.compareTo(other.title);
//        if (comp != 0) return comp;
//        return this.authorID.compareTo(other.authorID);
//    }

    @Override
    public String toString() {
        StringBuilder painting = new StringBuilder(title + " (" + year + ")");
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
