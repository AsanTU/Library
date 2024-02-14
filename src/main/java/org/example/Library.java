package org.example;

import java.util.*;

public class Library {

    private Map<String, Book> books;
    private Map<String, User> users;
    private Map<String, Rental> rentals;

    private Queue<User> rentalQueue;

    public Library() {
        books = new HashMap<>();
        users = new HashMap<String, User>();
        this.rentalQueue = new LinkedList<>();
        this.rentals = new HashMap<>();
    }

    public void addBook(String title, String author, Integer year, String kind) {
        Book book = new Book(title, author, year, kind);
        books.put(title, book);
    }

    public void addUser(User user) {
        if (users.containsKey(user.getId())) {
            System.out.println("User with ID: " + user.getId() + " already exists.");
        } else {
            users.put(user.getId(), user);
            System.out.println("User with ID: " + user.getId() + " successfully added.");
        }
    }

    public void rentBook(String id, String title, String username, String password) {
        if (!authenticateUser(username, password)) {
            System.out.println("Error authentication. User with this data not found");
            return;
        }
        User user = users.get(id);
        if (user.getRole() != Role.ADMIN && !id.equals(username)) {
            System.out.println("Not have enough role for rent.");
            return;
        }
        if (books.containsKey(title) && users.containsKey(id)) {
            Book book = books.get(title);
            if (book.getRent()) {
                book.setRent(false);
                Rental rental = new Rental(id, title);
                rentals.put(rental.getUserId(), rental);
                System.out.println("Book take user: " + id + " title: " + title);
            } else {
                System.out.println("Book: " + title + " not access");
            }
        } else {
            System.out.println("Book: " + title + " not found");
        }
    }

    public void returnBook(String title, String id) {
        if (users.containsKey(id) && books.containsKey(title)) {
            Book book = books.get(title);
            if (!book.getRent()) {
                book.setRent(true);
                Rental rental = rentals.remove(id + "-" + title);
                if (rental != null) {
                    long dueData = rental.getDueData().getTime();
                    long currentData = System.currentTimeMillis();
                    if (currentData > dueData) {
                        long overdueDays = (currentData - dueData) / (1000 * 60 * 60 * 24);
                        double fine = overdueDays * 0.50;
                        System.out.println("Late payment penalty " + fine + " y.e.");
                    }
                    System.out.println("Book return user: " + id + " title: " + title);
                } else {
                    System.out.println("User with ID " + id + " not rent book with title " + title);
                }
            } else {
                System.out.println("Book: " + title + " not access");
            }
        } else {
            System.out.println("Book: " + title + " not found");
        }
    }

    public void viewBooks() {
        System.out.println("List books in library:");
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            Book book = entry.getValue();
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Year: " + book.getYear());
            System.out.println("Kind: " + book.getKind());
            System.out.println("Access: " + (!book.getRent() ? "Access" : "Not access"));
            System.out.println();
        }
    }

    public void viewAccess() {
        System.out.println("List books access for user:");
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            Book book = entry.getValue();
            if (book.getRent()) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Year: " + book.getYear());
                System.out.println("Kind: " + book.getKind());
                System.out.println();
            }
        }
    }

    public List<Book> searchBook(String searchTitle, String searchKind) {
        List<Book> foundBook = new ArrayList<>();
        for (Book book : books.values()) {
            boolean match = false;
            switch (searchKind.toLowerCase()) {
                case "title":
                    match = book.getTitle().equalsIgnoreCase(searchTitle);
                    break;
                case "author":
                    match = book.getAuthor().equalsIgnoreCase(searchTitle);
                    break;
                case "kind":
                    match = book.getKind().equalsIgnoreCase(searchTitle);
                    break;
            }
            if (match) {
                foundBook.add(book);
            }
        }
        return foundBook;
    }

    public void addToRentalQueue(User user) {
        rentalQueue.offer(user);
        System.out.println("User: " + user.getId() + " added in queue rent");
    }

    public void processQueue() {
        if (!rentalQueue.isEmpty()) {
            User nextUser = rentalQueue.poll();
            System.out.println("Rent book for user: " + nextUser.getId() + " success");
        } else {
            System.out.println("Queue rent empty");
        }
    }

    public boolean authenticateUser(String username, String password) {
        for (User user : users.values()) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
