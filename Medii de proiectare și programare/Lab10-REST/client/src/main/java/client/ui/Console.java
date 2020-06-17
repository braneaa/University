package client.ui;

import client.Provider.BookProvider;
import client.Provider.ClientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dto.BookDto;
import web.dto.ClientDto;

import java.io.IOException;
import java.util.Scanner;

@Component
public class Console {

    private ClientProvider clientProvider;
    private BookProvider bookProvider;

    private static Scanner scanner = new Scanner(System.in);

    @Autowired
    public Console(ClientProvider clientProvider, BookProvider bookProvider) {
        this.clientProvider = clientProvider;
        this.bookProvider = bookProvider;
    }

    private void printMenu(){
        System.out.println();
        System.out.println("0. Exit.");
        System.out.println("1. Add a client.");
        System.out.println("2. Delete a client.");
        System.out.println("3. Update a client.");
        System.out.println("4. Print all clients.");
        System.out.println("5. Filter clients by name.");
        System.out.println("6. Add a book.");
        System.out.println("7. Delete a book.");
        System.out.println("8. Update a book.");
        System.out.println("9. Print all books.");
        System.out.println("10. Print the books from a certain author.");
        System.out.println("11. Print the books of a certain genre.");
    }


    public void run() throws IOException {
        printMenu();
        int com;
        System.out.println(">>>");
        com = scanner.nextInt();
        while (com != 0) {
            if(com == 1){
                addClients();
            }
            if(com == 2){
                deleteClients();
            }
            if(com == 3){
                updateClients();
            }
            if(com == 4){
                printAllClients();
            }
            if(com == 5){
                filterClientsByFirstName();
            }
            if(com == 6){
                addBooks();
            }
            if(com == 7){
                deleteBooks();
            }
            if(com == 8)
                updateBooks();
            if(com == 9)
                printAllBooks();
            if(com == 10)
                filterBooksByAuthor();
            if(com == 11)
                filterBooksByGenre();
            printMenu();
            System.out.println(">>>");
            com = scanner.nextInt();
        }
    }

    private void filterBooksByGenre() {
        scanner.nextLine();
        System.out.println("Genre:");
        String genre = scanner.nextLine();

        System.out.println();
        bookProvider.filterClientsByGenre(genre).getBooks().forEach(System.out::println);

    }

    private void filterBooksByAuthor() {
        scanner.nextLine();
        System.out.println("Author:");
        String author = scanner.nextLine();

        System.out.println();
        bookProvider.filterClientsByAuthor(author).getBooks().forEach(System.out::println);
    }

    private void printAllBooks() {
        bookProvider.getAll().getBooks().forEach(System.out::println);
    }

    private void updateBooks() {
        System.out.println("id:");
        Long id = scanner.nextLong();

        System.out.println("Serialnumber:");
        String serialnumber = scanner.next();

        System.out.println("Name:");
        String name = scanner.next();

        System.out.println("Author:");
        String author = scanner.next();

        System.out.println("Genre:");
        String genre = scanner.next();

        BookDto bookDto = new BookDto(serialnumber,name,author,genre);
        bookDto.setId(id);
        bookProvider.update(bookDto);

        System.out.println();
        System.out.println("Book successfully updated!");
        System.out.println();
    }

    private void deleteBooks() {
        scanner.nextLine();
        System.out.println("Book ID:");
        Long ID = scanner.nextLong();

        bookProvider.delete(ID);

        System.out.println();
        System.out.println("Book removed successfully!");
        System.out.println();
    }

    private void addBooks() {
        System.out.println("id:");
        Long id = scanner.nextLong();

        System.out.println("Serialnumber:");
        String serialnumber = scanner.next();

        System.out.println("Name:");
        String name = scanner.next();

        System.out.println("Genre:");
        String author = scanner.next();

        System.out.println("Author:");
        String genre = scanner.next();

        BookDto bookDto = new BookDto(serialnumber,name,author,genre);
        bookDto.setId(id);
        bookProvider.save(bookDto);

        System.out.println();
        System.out.println("Book successfully added!");
        System.out.println();
    }

    private void updateClients() throws IOException {
        System.out.println("id:");
        Long id = scanner.nextLong();

        System.out.println("id2:");
        int id2 = scanner.nextInt();

        System.out.println("Name:");
        String name = scanner.next();

        System.out.println("Phone Number:");
        long phoneNumber = scanner.nextLong();

        ClientDto clientDTO = new ClientDto(id2, name, phoneNumber);
        clientDTO.setId(id);
        clientProvider.update(clientDTO);

        System.out.println();
        System.out.println("Client successfully updated!");
        System.out.println();
    }

    private void deleteClients() {
        scanner.nextLine();
        System.out.println("Client ID:");
        Long ID = scanner.nextLong();

        clientProvider.delete(ID);

        System.out.println();
        System.out.println("Client removed successfully!");
        System.out.println();
    }

    private void filterClientsByFirstName() {
        scanner.nextLine();
        System.out.println("Name:");
        String name = scanner.nextLine();

        System.out.println();
        clientProvider.filterClientsByName(name).getClients().forEach(System.out::println);
        System.out.println();
    }

    private void addClients() {
        System.out.println("id:");
        Long id = scanner.nextLong();

        System.out.println("id2:");
        int id2 = scanner.nextInt();

        System.out.println("Name:");
        String name = scanner.next();

        System.out.println("Phone Number:");
        long phoneNumber = scanner.nextLong();

        ClientDto clientDTO = new ClientDto(id2, name, phoneNumber);
        clientDTO.setId(id);
        clientProvider.save(clientDTO);

        System.out.println();
        System.out.println("Client successfully added!");
        System.out.println();
    }

    private void printAllClients() {
        clientProvider.getAll().getClients().forEach(System.out::println);
    }

}
