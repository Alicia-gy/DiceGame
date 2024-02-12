package itacademy.dicegame.repository;

import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RollRepository extends MongoRepository<Roll, String> {

    List<Roll> findByUser(User user);

    void deleteByUser(User user);

}
