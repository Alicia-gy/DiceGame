package itacademy.dicegame.service;

import itacademy.dicegame.domain.dtos.RollDTO;

import java.util.List;
import java.util.Optional;

public interface RollService {

    public RollDTO save(RollDTO rollDTO);

    public void deleteById(int id);

    public Optional<RollDTO> findById(int id);

    public List<RollDTO> findAll();

}
