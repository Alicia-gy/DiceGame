package itacademy.dicegame.utilities;

import itacademy.dicegame.exceptions.UsernameAlreadyExistsException;
import itacademy.dicegame.repository.UserRepository;

public class NameValidator {

    public static String checkPublicName(String publicName, UserRepository userRepository) {
        if(publicName.isEmpty() || publicName.isBlank()){
            return "Anonymous";
        } else if(userRepository.findByPublicName(publicName).isPresent()) {
            throw new UsernameAlreadyExistsException("Public Name is already taken");
        }
        return publicName;
    }
}
