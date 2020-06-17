package Laboratory.Domain.Validator;


import Laboratory.Domain.Client;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException{
        // if (!(entity.getID() instanceof Integer)) throw new ValidatorException("Serial number not of good type!");
        if (!(entity.getName() instanceof String)) throw new ValidatorException("Name not of good type!");
        // if (!(entity.getPhoneNumber() instanceof Long)) throw new ValidatorException("Phone Number not of good type!");
    }
}
