package Laboratory.Domain.Validator;
import Laboratory.Domain.Book;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book entity) throws ValidatorException
    {
       if (!(entity.getSerialNumber() instanceof String)) throw new ValidatorException("Serial number not of good type!");
        if (!(entity.getName() instanceof String)) throw new ValidatorException("Name not of good type!");
        if (!(entity.getGenre() instanceof String)) throw new ValidatorException("Genre not of good type!");
        if (!(entity.getAuthor() instanceof String)) throw new ValidatorException("Author not of good type!");
    }
}
