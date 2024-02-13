package itacademy.dicegame.service.impl;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.entities.Roll;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.repository.RollRepository;
import itacademy.dicegame.service.RollService;
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

    @Override
    @Transactional
    public RollDTO create2D6Roll(User user) {
        Roll roll = new Roll(user,
                d6Roll(),
                d6Roll());

        rollRepository.save(roll);

        return new RollDTO(roll);
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

    @Override
    public byte d6Roll() {
        return (byte) (Math.random() * 6 + 1);
    }

}
