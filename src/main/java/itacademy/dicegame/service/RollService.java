package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.RollDTO;

import java.util.List;
import java.util.Optional;

public interface RollService {

    public void save(RollDTO rollDTO);

    public void deleteById(Long id);

    public RollDTO findById(Long id);

    public List<RollDTO> findAll();

}
