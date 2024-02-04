package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.repository.RollRepository;
import itacademy.dicegame.service.RollService;
import itacademy.dicegame.utilities.DiceRoller;
import itacademy.dicegame.utilities.DtoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RollServiceImpl implements RollService {

    private final RollRepository rollRepository;

    public RollServiceImpl(RollRepository rollRepository) {
        this.rollRepository = rollRepository;
    }

    @Override
    @Transactional
    public RollDTO create2D6Roll(User user) {
        Roll roll = new Roll(user,
                DiceRoller.d6Roll(),
                DiceRoller.d6Roll());

        rollRepository.save(roll);

        return DtoConverter.rollToDto(roll);
    }

    @Override
    @Transactional
    public void deleteByUser(User user){
        rollRepository.deleteByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RollDTO> findByUser(User user){
        List<Roll> rolls = rollRepository.findByUser(user);
        List<RollDTO> dtos = rolls.stream().map(DtoConverter::rollToDto).collect(Collectors.toList());
        return dtos;
    }

}
