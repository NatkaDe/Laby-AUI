package com.example.art_museum;

import com.example.art_museum.service.AuthorService;
import com.example.art_museum.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final PaintingService paintingService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, PaintingService paintingService) {
        this.authorService = authorService;
        this.paintingService = paintingService;
    }

    @Override
    public void run(String... args){
        boolean running = true;
        String command = "";
        Scanner scanner = new Scanner(System.in);
        List<Painting> paintings;
        List<Author> authors;
        while(running){
            System.out.println("Choose the action: \n");
            System.out.println("showp - show all the paintings");
            System.out.println("showa - show all the authors");
            System.out.println("add - add a new painting");
            System.out.println("delete - delete a painting");
            System.out.println("stop - stop the program");
            System.out.println("");

            command = scanner.nextLine();

            switch (command){
                case "showp":
                    paintings = paintingService.findAll();
                    paintings.forEach(painting->System.out.println(painting.toString()));
                    break;
                case "showa":
                    authors = authorService.findAll();
                    authors.forEach(author->System.out.println(author.getName()));
                    break;
                case "add":
                    Author author;
                    String title;
                    int year;
                    String input;
                    while(true){
                        System.out.println("Choose the author by number:");
                        authors = authorService.findAll();
                        for (int i = 0; i < authors.size(); i++) {
                            System.out.println((i + 1) + ". " + authors.get(i).getName());
                        }
                        int author_index;
                        input = scanner.nextLine();
                        try {
                            author_index = Integer.parseInt(input) - 1;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input (not a number). Please try again.\n");
                            continue;
                        }
                        if(author_index >= 0 && author_index < authors.size())
                        {
                            author = authors.get(author_index);
                            break;
                        }
                        else {
                            System.out.println("Wrong number. Please try again\n");
                        }
                    }

                    System.out.println("Give the title:");
                    title = scanner.nextLine();
                    while (true){
                        System.out.println("Give the year:");
                        try {
                            year = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input (not a number). Please try again.\n");
                            continue;
                        }
                    }

                    Painting painting = Painting.builder()
                            .uuid(UUID.randomUUID())
                            .title(title)
                            .year(year)
                            .author(author)
                            .build();
                    paintingService.save(painting);
                    break;
                case "delete":
                    paintings = paintingService.findAll();
                    while(true){
                        System.out.println("Choose the painting to delete by number:");
                        for (int i = 0; i < paintings.size(); i++) {
                            System.out.println((i + 1) + ". " + paintings.get(i).toString());
                        }
                        int painting_index;
                        input = scanner.nextLine();
                        try {
                            painting_index = Integer.parseInt(input) - 1;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input (not a number). Please try again.\n");
                            continue;
                        }
                        if(painting_index >= 0 && painting_index < paintings.size())
                        {
                            painting = paintings.get(painting_index);
                            break;
                        }
                        else {
                            System.out.println("Wrong number. Please try again\n");
                        }
                    }
                    paintingService.delete(painting);
                    System.out.println("Deleted successfully");
                    break;
                case "stop":
                    running = false;
                    break;
                default:
                    System.out.println("Unrcognisable command. Try again");
            }
            System.out.println("\n");
        }
    }
}
