package client;


import client.ui.Console;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "client.clientconfig"
                );

        Console console = context.getBean(Console.class);
        console.run();
    }
}