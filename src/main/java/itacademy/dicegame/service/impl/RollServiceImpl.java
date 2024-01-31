package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.Roll;
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
    public RollDTO create2D6Roll(UserDTO userDTO) {
        Roll roll = new Roll(
                DtoConverter.userToEntity(userDTO),
                DiceRoller.d6Roll(),
                DiceRoller.d6Roll());

        rollRepository.save(roll);

        return DtoConverter.rollToDto(roll);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        rollRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByUser(UserDTO userDTO){
        rollRepository.deleteByUser(DtoConverter.userToEntity(userDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RollDTO> findByUser(UserDTO userDTO){
        List<Roll> rolls = rollRepository.findByUser(DtoConverter.userToEntity(userDTO));
        List<RollDTO> dtos = rolls.stream().map(DtoConverter::rollToDto).collect(Collectors.toList());
        return dtos;
    }

}
