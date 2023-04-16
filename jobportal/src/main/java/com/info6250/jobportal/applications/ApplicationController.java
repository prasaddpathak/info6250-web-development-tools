package com.info6250.jobportal.applications;

import com.info6250.jobportal.templates.Controller;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "v1/applications")
public class ApplicationController implements Controller<Application> {

    private final ApplicationService applicationService = new ApplicationService();

    @CrossOrigin
    @GetMapping
    public List<Application> get() {
        System.out.println("Received GET");
        return applicationService.getAll();
    }

    @Override
    public ResponseEntity create(Application application) {
        return null;
    }

    @CrossOrigin
    @PostMapping(path = "/{userid}")
    public ResponseEntity createForUser(@RequestBody Application newApplication,
                                 @PathVariable("userid") String userid) {
        try {
            newApplication.setUser_id(userid);
            return new ResponseEntity<>(applicationService.save(newApplication), HttpStatus.CREATED) ;
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

//    @GetMapping(path = "/{userid}")
    public ResponseEntity getOne(String s) {
        return null;
    }

    @CrossOrigin
    @GetMapping(path = "/{userid}")
    public ResponseEntity getForUser(@PathVariable("userid") String userid) {
        try {
//            newApplication.setUser_id(userid);
            return new ResponseEntity<>(applicationService.getForUser(userid), HttpStatus.OK) ;
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

    @CrossOrigin
    @PutMapping(path = "/{userid}")
    public ResponseEntity updateForUser(@RequestBody Application updatedApplication, @PathVariable("userid") String userid) {
        try {
            updatedApplication.setUser_id(userid);
            updatedApplication.setApplied_on(new Date());
            return new ResponseEntity<>(applicationService.updateForUser(updatedApplication), HttpStatus.OK) ;
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }
}
