package Laboratory;

        import Laboratory.UserInterface.UserInterface;
        import org.springframework.context.annotation.AnnotationConfigApplicationContext;

        import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext("Laboratory");

        context.getBean(UserInterface.class).run();


    }

}
