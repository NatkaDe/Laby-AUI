package com.example.art_museum.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimpleAuthorRepository extends JpaRepository<SimpleAuthor, Integer> {


}