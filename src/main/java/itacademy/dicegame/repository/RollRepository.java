package itacademy.dicegame.repository;

import itacademy.dicegame.domain.entities.Roll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RollRepository extends JpaRepository<Roll, Long> {
}
