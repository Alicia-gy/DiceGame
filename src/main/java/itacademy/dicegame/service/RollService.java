package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.dtos.UserDTO;

import java.util.List;

public interface RollService {

    public void save(RollDTO rollDTO);

    public void deleteById(Long id);

    public void deleteByUser(UserDTO Userdto);

    public List<RollDTO> findByUser(UserDTO userDTO);

}
