package com.info6250.jobportal.users;

import com.info6250.jobportal.templates.Controller;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/users")
public class UserController implements Controller<User> {

    private final UserService userService = new UserService();

    @GetMapping
    public List<User> get() {
        return userService.getAll();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody User newUser) {
        try {
            return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED) ;
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

    @CrossOrigin
    @GetMapping(path = "/{userid}")
    public ResponseEntity getOne(@PathVariable("userid") String userid) {
        try {
            return new ResponseEntity<>(userService.getById(userid), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

    @CrossOrigin
    @GetMapping(path = "/auth")
    public ResponseEntity getAuth(@RequestHeader("Authorization") String basicToken) {
        try {
            return new ResponseEntity<>(userService.getAuth(basicToken), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }


}
