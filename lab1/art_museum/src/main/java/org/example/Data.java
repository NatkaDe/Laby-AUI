package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Data {

    public static List<Author> authors = new ArrayList<>();
    public static List<Painting> paintings = new ArrayList<>();

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

        Painting redFuji = Painting.builder()
                .uuid(UUID.randomUUID())
                .title("Kajikazawa in Kai Province")
                .year(1832)
                .author(hokusai)
                .build();

        hokusai.setPaintings(Arrays.asList(theGreatWave, fineWind, redFuji));
        authors.add(hokusai);
        paintings.addAll(Arrays.asList(theGreatWave, fineWind, redFuji));

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
        authors.add(hiroshige);
        paintings.addAll(Arrays.asList(fiftyThreeStations, moonNight, plumGarden));

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
        authors.add(sotatsu);
        paintings.addAll(Arrays.asList(matsu, waves, flowers));
    }
}
