package Laboratory.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan({"Laboratory.Domain","Laboratory.Repository", "Laboratory.Controller","Laboratory.UserInterface"})
public class BookStoreConfig{}