package fitnessapp.dao;

import fitnessapp.model.TrainerDetails;

import java.util.List;

public interface TrainerDetailsDao {

    List<TrainerDetails> getTrainerDetails();

    TrainerDetails getTrainerById(int trainerId);

}
