import {Trainer} from "../services/trainerDetailsService";
import "../pages/UserDetailsPage.css";
import "./TrainerCard.css";


type Props = {
    trainer: Trainer;
};

const TrainerCard = ({trainer}: Props) => {
    return (
        <div className="trainer-card">
            <img src={trainer.photograph}/>
            <h2>{trainer.name}</h2>
            <p>{trainer.bio}</p>
            <p><strong>Specialties: </strong>{trainer.specialtyOne}, {trainer.specialtyTwo}</p>
            <p><strong>Certification: </strong>{trainer.certification}</p>
        </div>
    );
};

export default TrainerCard;