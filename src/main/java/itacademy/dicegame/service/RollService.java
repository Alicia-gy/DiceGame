package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.entities.User;

import java.util.List;

public interface RollService {

    public RollDTO create2D6Roll(User user);

    public void deleteByUser(User user);

    public List<RollDTO> findByUser(User user);

}
