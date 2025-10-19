package org.example;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class App
{
    public static void main(String[] args) {

        // zadanie 2
        System.out.println("\n\n Task 2 - creating collections \n");
        Data data = new Data();
        data.init();

        Data.authors.forEach(author -> {
            System.out.println(author.getName() + " (" + author.getYear_of_birth() + " - " + author.getYear_of_death() + "):");
            author.getPaintings().forEach(painting ->
                    System.out.println("  - " + painting.getTitle() + " (" + painting.getYear() + ")")
            );
        });


        // zad 3
        System.out.println("\n\n Task 3 - creating pipelines \n");
        Set<Painting> allPaintings = Data.authors.stream().flatMap(author -> author
                .getPaintings()
                .stream())
                .collect(Collectors.toSet());

        allPaintings.stream().forEach(painting -> System.out.println(painting.toString()));


        // zad 4
        System.out.println("\n\n Task 4 - sorting \n");

        System.out.println("Paintings made after year 1800: \n");
        allPaintings.stream()
                .filter(painting -> painting.getYear() > 1800)
                .sorted(Comparator.comparing(Painting::getTitle))
                .forEach(painting -> System.out.println(painting.toString()));


        // zad 5
        System.out.println("\n\n Task 5 - DTO objects \n");

        List<PaintingDTO> paintingDTOs = allPaintings.stream()
                .map(p -> PaintingDTO.builder()
                        .title(p.getTitle())
                        .year(p.getYear())
                        .authorName(p.getAuthor().getName())
                        .build())
                .sorted()
                .collect(Collectors.toList());
        paintingDTOs.stream()
                .forEach(paintingDTO -> System.out.println(paintingDTO.toString()));


        // zad 6
        System.out.println("\n\n Task 6 - binary file \n");

        String filename = "museum_collection.bin";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(Data.authors);
            System.out.println("Serialized successfully to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Author> deserializedAuthors = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            deserializedAuthors = (List<Author>) ois.readObject();
            System.out.println("\nDeserialized successfully!\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (deserializedAuthors != null) {
            deserializedAuthors.forEach(author -> System.out.println(author.toString()));
        }


        // zad 7
        System.out.println("\n\n Task 7 - parallel pipelines \n");

        for(int i=1; i<=3; i++) {
            System.out.println("\n Pool size: " + i + "\n");
            ForkJoinPool pool = new ForkJoinPool(i);
            pool.submit(() ->
                    Data.authors.parallelStream().forEach(author -> {
                        System.out.println("Author: " + author.getName() + " | Thread: " + Thread.currentThread().getName());
                        author.getPaintings().forEach(painting -> {
                            System.out.println(painting.toString());
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }});
                    })
            ).join();
            pool.shutdown();
        }


    }


}
