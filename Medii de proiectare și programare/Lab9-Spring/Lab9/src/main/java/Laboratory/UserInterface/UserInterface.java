package Laboratory.UserInterface;

import Laboratory.Controller.BookController;
import Laboratory.Controller.ClientController;
import Laboratory.Controller.SaleEntityController;
import Laboratory.Domain.Book;
import Laboratory.Domain.Client;
import Laboratory.Domain.SaleEntity;
import Laboratory.Domain.Validator.ValidatorException;
import Laboratory.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class UserInterface {
    @Autowired
    private BookController bookController;
    @Autowired
    private ClientController clientController;
    @Autowired
    private SaleEntityController saleEntityController;

    public void run() throws SQLException {
        printMenu();
        int com;
        System.out.println(">>>");
        Scanner sc= new Scanner(System.in);
        com = sc.nextInt();
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
                addBooks();
            }
            if(com == 7){
                updateBooks();
            }
            if(com == 6){
                deleteBooks();
            }
            if(com == 8){
                printAllBooks();
            }
            if(com == 9)
            {
                booksOfTheGenre();
            }
            if(com == 10){
                addSale();
            }
            if(com == 11){
                printAllSales();
            }
            if(com == 12){
                reportMostSoldBook();
            }
            printMenu();
            System.out.println(">>>");
            com = sc.nextInt();
        }
    }

    private void printMenu(){
        System.out.println();
        System.out.println("0. Exit.");
        System.out.println("1. Add a client.");
        System.out.println("2. Delete a client.");
        System.out.println("3. Update a client.");
        System.out.println("4. Print all clients.");
        System.out.println("5. Add a book.");
        System.out.println("6. Delete a book.");
        System.out.println("7. Update a book.");
        System.out.println("8. Print all books.");
        System.out.println("9. Print the books of a certain genre.");
        System.out.println("10. Sell a book");
        System.out.println("11. See all sold books");
        System.out.println("12. See the most sold book.");
    }

    private void printAllBooks(){
        List<Book> books = bookController.getAllBooks();
        books.forEach(System.out::println);
    }

    private void addBooks() {
        Book book = readBook();
        if (book == null) {
            return;
        }
        try {
            bookController.saveBook(book);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

    }

    private Book readBook() {
        System.out.println("Read Book {id,serialNumber, name, author, genre}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String serialNumber = bufferRead.readLine();
            String name = bufferRead.readLine();
            String author =bufferRead.readLine();
            String genre = bufferRead.readLine();

            Book book= new Book(serialNumber, name, author, genre);
            book.setId(id);
            return book;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void deleteBooks() throws SQLException {
        System.out.println("Give the id of the book you want to delete: ");
        Scanner sc= new Scanner(System.in);
        int id = sc.nextInt();
        bookController.deleteById(id);
    }

    private void updateBooks(){
        Book book = readBook();
        if (book == null || book.getId() < 0) {
            return;
        }
        try {
            bookController.updateBook(book);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    private Client readClient() {
        System.out.println("Read client {id,ID, name, phoneNumber}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());// ...
            int ID = Integer.parseInt(bufferRead.readLine());
            String name = bufferRead.readLine();
            long phoneNumber =Long.parseLong(bufferRead.readLine());

            Client client= new Client(ID, name, phoneNumber);
            client.setId(id);

            return client;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void addClients() {
        Client client = readClient();
        if (client == null || client.getId() < 0) {
            return;
        }
        try {
            clientController.saveClient(client);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

    }
    private void deleteClients() {
        System.out.println("Give the id of the client you want to delete: ");
        Scanner sc= new Scanner(System.in);
        int id = sc.nextInt();
        clientController.deleteById(id);
    }

    private void updateClients(){
        Client client = readClient();
        if (client == null || client.getId() < 0) {
            return;
        }
        try {
            clientController.updateClient(client);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    private void printAllClients(){
        List<Client> clients = clientController.getAllClients();
        clients.forEach(System.out::println);
    }

    private void booksOfTheGenre(){
        System.out.println("Give the genre you are interested in : ");
        Scanner sc= new Scanner(System.in);
        String genre = sc.next();

        List<Book> books = bookController.genreFilter(genre);
        books.forEach(System.out::println);
    }

    private SaleEntity readSaleEntity() {
        System.out.println("Read Sale Entity { id, BuyId, BookId, ClientId }");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            int BuyId = Integer.parseInt(bufferRead.readLine());
            int BookId = Integer.parseInt(bufferRead.readLine());
            int ClientId = Integer.parseInt(bufferRead.readLine());

            SaleEntity saleEntity= new SaleEntity(BuyId, BookId, ClientId);
            saleEntity.setId(id);

            return saleEntity;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void addSale() {
        SaleEntity buyEntity = readSaleEntity();
        if (buyEntity == null || buyEntity.getId() < 0) {
            return;
        }
        try {
            saleEntityController.saveSale(buyEntity);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

    }
    private void printAllSales(){
        List<SaleEntity> saleEntities = saleEntityController.getAllSales();
        saleEntities.forEach(System.out::println);
    }

    private void reportMostSoldBook(){
        System.out.println("The most sold book is the book with id : ");
        System.out.println(saleEntityController.mostSoldBooks());
    }

}
