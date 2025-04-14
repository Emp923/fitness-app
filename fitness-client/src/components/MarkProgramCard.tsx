import { FormEvent, useState } from "react";
import { formatDate } from "../utils";
import { submitExerciseLog } from "../services/exerciseProgramService";
import { useSelector } from "react-redux";
import { RootState } from "../store";

type Props = {
    exerciseName: string;
};

const MarkProgramCard = ({exerciseName}: Props) => {
    const { token } = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");
    const [resistance, setResistance] = useState<number>(0);
    const [sets, setSets] = useState<number>(0);
    const [repetitions, setRepetitions] = useState<number>(0);
    const [comments, setComments] = useState<string>("");

    const recordedDate = new Date();
    const formattedDate = `${recordedDate.getMonth() + 1}/${recordedDate.getDate()}/${recordedDate.getFullYear()}`;

    const handleSubmit = async (event: FormEvent) => {
        event.preventDefault();
        
        const formData = {
            recordedDate: formatDate(recordedDate.toString()),
            resistance,
            sets,
            repetitions,
            exerciseName,
            comments
        }
        console.log(formData.recordedDate);
        try{
            const res = await submitExerciseLog(
                formData, token
            );
            if (res.id){
                alert("Exercise Submitted");
            }
        }catch (error) {
            console.log(error);
        }
    };

    return (
        <div id="exercise-submit" className="text-center">
            <h2>{formattedDate}</h2>
            <h2>Submit Completed Exercise</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-input-group">
                    <label htmlFor="resistance">Weight:</label>
                    <input
                        type="number"
                        name="resistance"
                        placeholder="weight"
                        required
                        onChange={(e) => setResistance(Number(e.target.value))}
                    />
                </div>
                <div className="form-input-group">
                    <label htmlFor="sets">Sets:</label>
                    <input
                        type="number"
                        name="sets"
                        placeholder="sets"
                        required
                        onChange={(e) => setSets(Number(e.target.value))}
                    />
                </div>
                <div className="form-input-group">
                    <label htmlFor="repetitions">Repetitions:</label>
                    <input
                        type="number"
                        name="repetitions"
                        placeholder="repetitions"
                        required
                        onChange={(e) => setRepetitions(Number(e.target.value))}
                    />
                </div>
                <div className="form-input-group">
                    <label htmlFor="comments">Comments:</label>
                    <input
                        type="text"
                        name="comments"
                        placeholder="comments"
                        onChange={(e) => setComments(e.target.value)}
                    />
                </div>
                <button type="submit">Save Details</button>
            </form>
        </div>
    );

};

export default MarkProgramCard;