import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import { getTrainers, Trainer } from "../services/trainerDetailsService";
import TrainerCard from "../components/TrainerCard";
import "./UserDetailsPage.css";

const TrainersPage = () => {
    const { token } = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");
    const [trainers, setTrainers] = useState<Trainer[]>([]);

    useEffect(() => {
        const callApi = async (token: string) => {
            try {
                const trainers = await getTrainers(token);
                setTrainers(trainers);
            }catch(error){
                console.log(error);
            }
        };

        callApi(token);
    }, [token]);

    return (
        <>
            <h1>Available Trainers</h1>
            <div className="trainer-container">
            {trainers.map((trainer: Trainer) => (
                <TrainerCard key={trainer.trainerId} trainer={trainer}/>
            ))}
            </div>
        </>
    );
};

export default TrainersPage;