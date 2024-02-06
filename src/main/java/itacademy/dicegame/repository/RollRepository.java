package itacademy.dicegame.repository;

import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RollRepository extends JpaRepository<Roll, Long> {

    List<Roll> findByUser(User user);

    void deleteByUser(User user);

}
