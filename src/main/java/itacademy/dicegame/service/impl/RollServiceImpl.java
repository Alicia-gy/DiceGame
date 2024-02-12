package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.exceptions.UserNotFoundException;
import itacademy.dicegame.repository.RollRepository;
import itacademy.dicegame.repository.UserRepository;
import itacademy.dicegame.service.RollService;
import itacademy.dicegame.utilities.DiceRoller;
import itacademy.dicegame.utilities.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RollServiceImpl implements RollService {

    private final RollRepository rollRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public RollDTO create2D6Roll(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Roll roll = new Roll(user,
                DiceRoller.d6Roll(),
                DiceRoller.d6Roll());

        rollRepository.save(roll);
        userRepository.save(user);

        return DtoConverter.rollToDto(roll);
    }

    @Override
    @Transactional
    public void deleteByUserId(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        rollRepository.deleteByUser(user);
        user.getRolls().clear();
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RollDTO> findByUser(User user){
        List<Roll> rolls = rollRepository.findByUser(user);
        List<RollDTO> dtos = rolls.stream().map(DtoConverter::rollToDto).collect(Collectors.toList());
        return dtos;
    }

}
