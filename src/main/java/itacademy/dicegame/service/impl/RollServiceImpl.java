package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.repository.RollRepository;
import itacademy.dicegame.service.RollService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RollServiceImpl implements RollService {

    private final RollRepository rollRepository;

    public RollServiceImpl(RollRepository rollRepository) {
        this.rollRepository = rollRepository;
    }

    @Override
    @Transactional
    public void save(RollDTO rollDTO) {
        Roll roll = toEntity(rollDTO);
        rollRepository.save(roll);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    }

    @Override
    @Transactional(readOnly = true)
    public RollDTO findById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RollDTO> findAll() {
        return null;
    }

    static RollDTO toDto(Roll roll){
        return new RollDTO(roll);
    }

    static Roll toEntity(RollDTO rollDto){
        return new Roll(rollDto.getUser(),
                rollDto.getDice1(), rollDto.getDice2());
    }
}
