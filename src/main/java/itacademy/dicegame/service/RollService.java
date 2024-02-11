package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.entities.User;

import java.util.List;

public interface RollService {

    RollDTO create2D6Roll(User user);

    void deleteByUser(User user);

    List<RollDTO> findByUser(User user);

}
