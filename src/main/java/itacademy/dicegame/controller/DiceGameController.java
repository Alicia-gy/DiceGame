package itacademy.dicegame.controller;

import itacademy.dicegame.domain.dtos.UserDTO;
import itacademy.dicegame.service.RollService;
import itacademy.dicegame.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<?> createUser (@RequestBody UserDTO userDTO, @PathVariable(value = "id") Long id) {
        userService.save(userDTO);
        return new ResponseEntity<>("User updated", HttpStatus.CREATED);
    }
}
