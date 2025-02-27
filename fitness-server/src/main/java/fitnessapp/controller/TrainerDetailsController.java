package fitnessapp.controller;

import fitnessapp.dao.TrainerDetailsDao;
import fitnessapp.exception.DaoException;
import fitnessapp.model.TrainerDetails;
import fitnessapp.model.UserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/trainers")
public class TrainerDetailsController {

    private final TrainerDetailsDao trainerDetailsDao;

    public TrainerDetailsController(TrainerDetailsDao trainerDetailsDao){
        this.trainerDetailsDao = trainerDetailsDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<TrainerDetails> listTrainerDetails(){
        return trainerDetailsDao.getTrainerDetails();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public TrainerDetails getTrainerDetailsById(@PathVariable int id) {

        TrainerDetails trainerDetails = trainerDetailsDao.getTrainerById(id);

        if(trainerDetails == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found.");
        }else {
            return trainerDetails;
        }

    }

}
