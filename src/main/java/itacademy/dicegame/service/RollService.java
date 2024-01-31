package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.dtos.UserDTO;

import java.util.List;

public interface RollService {

    public RollDTO create2D6Roll(UserDTO userDTO);

    public void deleteById(Long id);

    public void deleteByUser(UserDTO userDTO);

    public List<RollDTO> findByUser(UserDTO userDTO);

}
