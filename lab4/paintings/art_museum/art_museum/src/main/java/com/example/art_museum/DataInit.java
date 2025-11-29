package com.example.art_museum;

import com.example.art_museum.author.SimpleAuthorService;
import com.example.art_museum.author.SimpleAuthor;
import com.example.art_museum.painting.Painting;
import com.example.art_museum.author.SimpleAuthorService;
import com.example.art_museum.painting.PaintingService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public class DataInit {

    private final SimpleAuthorService authorService;
    private final PaintingService paintingService;

    @Autowired
    public DataInit(SimpleAuthorService authorService, PaintingService paintingService) {
        this.authorService = authorService;
        this.paintingService = paintingService;
    }

    @PostConstruct

    // data needed to present lab exercises
    public void init() {
        SimpleAuthor hokusai = SimpleAuthor.builder()
                .id(123)
                .name("Katsushika Hokusai")
                .build();

        Painting theGreatWave = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("The Great Wave off Kanagawa")
                .year(1831)
                .authorID(hokusai.getId())
                .build();

        Painting fineWind = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Fine Wind, Clear Morning")
                .year(1830)
                .authorID(hokusai.getId())
                .build();

        Painting kajikazawa = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Kajikazawa in Kai Province")
                .year(1832)
                .authorID(hokusai.getId())
                .build();

        //hokusai.setPaintings(Arrays.asList(theGreatWave, fineWind, kajikazawa));
        authorService.save(hokusai);
        paintingService.save(theGreatWave);
        paintingService.save(fineWind);
        paintingService.save(kajikazawa);

        SimpleAuthor hiroshige = SimpleAuthor.builder()
                .id(456)
                .name("Utagawa Hiroshige")
                .build();

        Painting fiftyThreeStations = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Fifty-three Stations of the Tokaido")
                .year(1833)
                .authorID(hiroshige.getId())
                .build();

        Painting moonNight = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Sudden Shower over Shin-Ōhashi Bridge and Atake")
                .year(1857)
                .authorID(hiroshige.getId())
                .build();

        Painting plumGarden = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Plum Garden at Kameido")
                .year(1857)
                .authorID(hiroshige.getId())
                .build();

        //hiroshige.setPaintings(Arrays.asList(fiftyThreeStations, moonNight, plumGarden));
        authorService.save(hiroshige);
        paintingService.save(fiftyThreeStations);
        paintingService.save(moonNight);
        paintingService.save(plumGarden);

        SimpleAuthor sotatsu = SimpleAuthor.builder()
                .id(789)
                .name("Tawaraya Sōtatsu")
                .build();

        Painting matsu = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Matsushima Screens")
                .year(1620)
                .authorID(sotatsu.getId())
                .build();

        Painting waves = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Waves at Matsushima")
                .year(1620)
                .authorID(sotatsu.getId())
                .build();

        Painting flowers = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Flowers and Birds of the Four Seasons")
                .year(1625)
                .authorID(sotatsu.getId())
                .build();

        //sotatsu.setPaintings(Arrays.asList(matsu, waves, flowers));
        authorService.save(sotatsu);
        paintingService.save(matsu);
        paintingService.save(waves);
        paintingService.save(flowers);
    }
}
