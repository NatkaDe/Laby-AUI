//package com.example.art_museum.author;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//
//public class AuthorReadDTO {
//    private Integer id;
//    private String name;
//    private int year_of_birth;
//    private int year_of_death;
//
//    @Repository
//    public static interface AuthorRepository extends JpaRepository<Author, Integer> {
//
//    }
//
//    @Service
//    public static class AuthorService {
//        private final AuthorRepository authorRepository;
//
//        public AuthorService(AuthorRepository authorRepository) {
//            this.authorRepository = authorRepository;
//        }
//
//        public Author save(Author author) {
//            return authorRepository.save(author);
//        }
//
//        public List<Author> findAll() { return authorRepository.findAll();}
//
//        public Optional<Author> findById(Integer id) { return authorRepository.findById(id);}
//
//        public void deleteById(Integer id) { authorRepository.deleteById(id);}
//    }
//}


package com.example.art_museum.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorReadDTO {
    private Integer id;
    private String name;
    private int year_of_birth;
    private int year_of_death;
}
