package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.entities.User;

import java.util.List;

public interface RollService {

    RollDTO create2D6Roll(String id);

    void deleteByUserId(String id);

    List<RollDTO> findByUser(User user);

}
