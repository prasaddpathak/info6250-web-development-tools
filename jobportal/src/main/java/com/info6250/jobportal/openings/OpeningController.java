package com.info6250.jobportal.openings;

import com.info6250.jobportal.applications.Application;
import com.info6250.jobportal.templates.Controller;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/openings")
public class OpeningController implements Controller<Opening> {

    private final OpeningService openingService = new OpeningService();

    @CrossOrigin
    @GetMapping
    public List<Opening> get() {
        return openingService.getAll();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody Opening newOpening) {
        try {
            return new ResponseEntity<>(openingService.save(newOpening), HttpStatus.CREATED) ;
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

    @CrossOrigin
    @PostMapping(path = "/{userid}")
    public ResponseEntity createForUser(@RequestBody Opening newOpening,
                                        @PathVariable("userid") String userid) {
        try {
            newOpening.setEmployer_id(userid);
            return new ResponseEntity<>(openingService.save(newOpening), HttpStatus.CREATED) ;
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

    @CrossOrigin
    @GetMapping(path = "/id/{openid}")
    public ResponseEntity getById(@PathVariable("openid") String openid) {
        try {
            return new ResponseEntity<>(openingService.getById(openid), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

    @CrossOrigin
    @GetMapping(path = "/{userid}")
    public ResponseEntity getOne(@PathVariable("userid") String userid) {
        try {
            return new ResponseEntity<>(openingService.getForUser(userid), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }
}
