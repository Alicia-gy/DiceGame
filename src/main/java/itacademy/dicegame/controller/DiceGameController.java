package itacademy.dicegame.controller;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.domain.entities.User;
import itacademy.dicegame.service.RollService;
import itacademy.dicegame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
public class DiceGameController {

    private final UserService userService;
    private final RollService rollService;


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser (@RequestBody String publicName, @PathVariable(value = "id") Long id) {
        String name = userService.update(publicName, id);
        return new ResponseEntity<>(
                "Public name updated: " + name, HttpStatus.OK);
    }

    @PostMapping("/{id}/games")
    public ResponseEntity<?> new2D6Roll(@PathVariable(value = "id") Long id) {
        User user = userService.findByIdReturnEntity(id);

        RollDTO rollDTO = rollService.create2D6Roll(user);
        return new ResponseEntity<>(rollDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/games")
    public ResponseEntity<?> deleteRolls(@PathVariable(value = "id") Long id) {
        User user = userService.findByIdReturnEntity(id);

        rollService.deleteByUser(user);
        return new ResponseEntity<>(
                "Rolls deleted", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getPlayerList() {
        List<UserDTO> userDTOS = userService.findAll();

        String convertedList = userDTOS.stream()
                .map(UserDTO::toString)
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @GetMapping("/{id}/games")
    public ResponseEntity<?> getPlayerRollList(@PathVariable(value = "id") Long id) {
        User user = userService.findByIdReturnEntity(id);
        List<RollDTO> rollDTOS = rollService.findByUser(user);

        String convertedList = rollDTOS.stream()
                .map(RollDTO::toString)
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @GetMapping("/ranking")
    public ResponseEntity<?> getAverageScore() {
        List<UserDTO> userDTOS = userService.findAll();
        float totalValue = 0;

        for(UserDTO dto:userDTOS){
            totalValue += dto.getAverage();
        }
        float average = (totalValue / userDTOS.size());

        return new ResponseEntity<>(
                "Average score: " + average, HttpStatus.OK);
    }

    @GetMapping("/loser")
    public ResponseEntity<?> getWorstAverage() {
        List<UserDTO> userDTOS = userService.findAll();
        List<UserDTO> sorteredList = userDTOS.stream()
                .sorted(Comparator.comparing(UserDTO::getAverage))
                .toList();

        UserDTO loser = sorteredList.getFirst();
        return new ResponseEntity<>(
                "Player with lower average: \n" + loser.toString(), HttpStatus.OK);
    }

    @GetMapping("/winner")
    public ResponseEntity<?> getBestAverage() {
        List<UserDTO> userDTOS = userService.findAll();
        List<UserDTO> sorteredList = userDTOS.stream()
                .sorted(Comparator.comparing(UserDTO::getAverage, Comparator.reverseOrder()))
                .toList();

        UserDTO winner = sorteredList.getFirst();
        return new ResponseEntity<>(
                "Player with highest average: \n" + winner.toString(), HttpStatus.OK);
    }
}
