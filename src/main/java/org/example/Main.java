package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        library.addBook("The Psychology of Money", "Morgan Housel", 2020, "Success");
        library.addBook("Hidden Genius", "Polina Marinova Pompliano", 2023, " Success");
        library.addBook("The Learning Game", "Ana Lorena Fábrega", 2023, " Success");
        library.addBook("How Big Things Get Done", "Bent Flyvbjerg", 2023, " Success");
        library.addBook("Think Faster, Talk Smarter", "Matt Abrahams", 2020, " Success");

        User admin = new User("452", "Asan", "2711", Role.ADMIN);
        library.addUser(admin);

        User user = new User("43", "Asan", "2711", Role.USER);
        library.addUser(user);
        library.authenticateUser("Asan", "2711");
        library.rentBook("452", "Hidden Genius", "Asan", "2711");

        library.viewBooks();
        library.viewAccess();

        List<Book> foundBookTitle = library.searchBook("Hidden Genius", "title");
        System.out.println(foundBookTitle);

        List<Book> foundBookAuthor = library.searchBook("Morgan Housel", "author");
        System.out.println(foundBookAuthor);

        List<Book> foundBookKind = library.searchBook("Success", "kind");
        System.out.println(foundBookKind);

        library.addToRentalQueue(user);
        library.processQueue();
    }
}