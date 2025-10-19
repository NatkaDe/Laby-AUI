package com.example.art_museum.repository;

import com.example.art_museum.Painting;
import com.example.art_museum.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, UUID> {
    List<Painting> findByAuthor(Author author);

}
