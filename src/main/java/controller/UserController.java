package controller;


import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired private UserService userService;

    @PostMapping(path = "/registerUser")
    public void registerUser (@RequestBody UserDTO userDto) {
        userService.registerUser(userDto);
    }

    @PutMapping(path = "/editUser/{id}")
    public ResponseEntity<Void> editUser (@RequestBody UserDTO userDto, @PathVariable(value = "id") Long id) {
        userService.editUser(userDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/listUsers")
    public ResponseEntity<?> listUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}

