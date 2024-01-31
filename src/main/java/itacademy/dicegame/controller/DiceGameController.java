package itacademy.dicegame.controller;

import itacademy.dicegame.domain.dtos.RollDTO;
import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.service.RollService;
import itacademy.dicegame.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class DiceGameController {

    private final UserService userService;
    private final RollService rollService;

    public DiceGameController(UserService userService, RollService rollService){
        this.userService = userService;
        this.rollService = rollService;
    }

    @PostMapping("/players")
    public ResponseEntity<?> createUser (@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<?> updateUser (@RequestBody UserDTO userDTO, @PathVariable(value = "id") Long id) {
        userService.update(userDTO, id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/players/{id}/games")
    public ResponseEntity<?> new2D6Roll(@PathVariable(value = "id") Long id) {
        UserDTO userDTO = userService.findById(id);

        RollDTO rollDTO = rollService.create2D6Roll(userDTO);
        return new ResponseEntity<>(rollDTO, HttpStatus.OK);
    }

    @DeleteMapping("/players/{id}/games")
    public ResponseEntity<?> deleteRolls(@PathVariable(value = "id") Long id) {
        UserDTO userDTO = userService.findById(id);

        rollService.deleteByUser(userDTO);
        return new ResponseEntity<>("Rolls deleted", HttpStatus.OK);
    }

    @GetMapping("/players/")
    public ResponseEntity<?> getPlayerList() {
        List<UserDTO> userDTOS = userService.findAll();

        String convertedList = userDTOS.stream()
                .map(UserDTO::toString)
                .collect(Collectors.joining("/n"));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @GetMapping("/players/{id}/games")
    public ResponseEntity<?> getPlayerRollList(@PathVariable(value = "id") Long id) {
        UserDTO userDTO = userService.findById(id);
        List<RollDTO> rollDTOS = rollService.findByUser(userDTO);

        String convertedList = rollDTOS.stream()
                .map(RollDTO::toString)
                .collect(Collectors.joining("/n"));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @GetMapping("/players/ranking")
    public ResponseEntity<?> getAverageScore() {
        List<UserDTO> userDTOS = userService.findAll();
        float totalValue = 0;

        for(UserDTO dto:userDTOS){
            totalValue += dto.getAverage();
        }
        float average = (totalValue / userDTOS.size());

        return new ResponseEntity<>("Average score: " + average, HttpStatus.OK);
    }

    @GetMapping("/players/loser")
    public ResponseEntity<?> getWorstAverage() {
        List<UserDTO> userDTOS = userService.findAll();
        List<UserDTO> sorteredList = userDTOS.stream()
                .sorted(Comparator.comparing(UserDTO::getAverage))
                .toList();

        UserDTO loser = sorteredList.getFirst();
        return new ResponseEntity<>(loser, HttpStatus.OK);
    }

    @GetMapping("/players/winner")
    public ResponseEntity<?> getBestAverage() {
        List<UserDTO> userDTOS = userService.findAll();
        List<UserDTO> sorteredList = userDTOS.stream()
                .sorted(Comparator.comparing(UserDTO::getAverage, Comparator.reverseOrder()))
                .toList();

        UserDTO winner = sorteredList.getFirst();
        return new ResponseEntity<>(winner, HttpStatus.OK);
    }
}
