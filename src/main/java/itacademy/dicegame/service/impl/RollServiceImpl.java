package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.repository.RollRepository;
import itacademy.dicegame.service.RollService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RollServiceImpl implements RollService {

    private final RollRepository rollRepository;

    public RollServiceImpl(RollRepository rollRepository) {
        this.rollRepository = rollRepository;
    }

    @Override
    public RollDTO save(RollDTO rollDTO) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Optional<RollDTO> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<RollDTO> findAll() {
        return null;
    }
}
