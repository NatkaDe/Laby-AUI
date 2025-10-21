package com.example.art_museum;

import com.example.art_museum.entity.Author;
import com.example.art_museum.entity.Painting;
import com.example.art_museum.service.AuthorService;
import com.example.art_museum.service.PaintingService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public class DataInit {

    private final AuthorService authorService;
    private final PaintingService paintingService;

    @Autowired
    public DataInit(AuthorService authorService, PaintingService paintingService) {
        this.authorService = authorService;
        this.paintingService = paintingService;
    }

    @PostConstruct

    // data needed to present lab exercises
    public void init() {
        Author hokusai = Author.builder()
                .uuid(UUID.randomUUID())
                .name("Katsushika Hokusai")
                .year_of_birth(1760)
                .year_of_death(1849)
                .build();

        Painting theGreatWave = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("The Great Wave off Kanagawa")
                .year(1831)
                .author(hokusai)
                .build();

        Painting fineWind = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Fine Wind, Clear Morning")
                .year(1830)
                .author(hokusai)
                .build();

        Painting kajikazawa = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Kajikazawa in Kai Province")
                .year(1832)
                .author(hokusai)
                .build();

        hokusai.setPaintings(Arrays.asList(theGreatWave, fineWind, kajikazawa));
        authorService.save(hokusai);
        paintingService.save(theGreatWave);
        paintingService.save(fineWind);
        paintingService.save(kajikazawa);

        Author hiroshige = Author.builder()
                .uuid(UUID.randomUUID())
                .name("Utagawa Hiroshige")
                .year_of_birth(1797)
                .year_of_death(1858)
                .build();

        Painting fiftyThreeStations = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Fifty-three Stations of the Tokaido")
                .year(1833)
                .author(hiroshige)
                .build();

        Painting moonNight = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Sudden Shower over Shin-Ōhashi Bridge and Atake")
                .year(1857)
                .author(hiroshige)
                .build();

        Painting plumGarden = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Plum Garden at Kameido")
                .year(1857)
                .author(hiroshige)
                .build();

        hiroshige.setPaintings(Arrays.asList(fiftyThreeStations, moonNight, plumGarden));
        authorService.save(hiroshige);
        paintingService.save(fiftyThreeStations);
        paintingService.save(moonNight);
        paintingService.save(plumGarden);

        Author sotatsu = Author.builder()
                .uuid(UUID.randomUUID())
                .name("Tawaraya Sōtatsu")
                .year_of_birth(1570)
                .year_of_death(1640)
                .build();

        Painting matsu = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Matsushima Screens")
                .year(1620)
                .author(sotatsu)
                .build();

        Painting waves = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Waves at Matsushima")
                .year(1620)
                .author(sotatsu)
                .build();

        Painting flowers = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Flowers and Birds of the Four Seasons")
                .year(1625)
                .author(sotatsu)
                .build();

        sotatsu.setPaintings(Arrays.asList(matsu, waves, flowers));
        authorService.save(sotatsu);
        paintingService.save(matsu);
        paintingService.save(waves);
        paintingService.save(flowers);
    }
}
