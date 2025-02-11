import { FormEvent, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./UserDetailsPage.css";
import { userDetailsSubmit } from "../services/userDetailsService";
import { formatDate } from "../utils.ts";

const UserDetails = () => {
    const [preferredName, setPreferredName] = useState<string>("");
    const [availability, setAvailability] = useState<string[]>([]);
    const [birthday, setBirthday] = useState<Date | null>(null);
    const [gender, setGender] = useState<string>("");
    const [restrictions, setRestrictions] = useState<string>("");
    const [goals, setGoals] = useState<string[]>([]);
    const [comments, setComment] = useState<string>("");

    const navigate = useNavigate();

    const handleSubmit = async (event: FormEvent) => {
        event.preventDefault();
        const formData = {
            userId: 6,
            preferredName,
            availability: availability.toString(),
            birthday: formatDate(birthday?.toString()),
            gender,
            restrictions,
            goals: goals.toString(),
            comments
        }
        console.log(formData.birthday);
        try {
            const res = await userDetailsSubmit(
                formData
            );
            if (res.status === 201) {
                navigate("/login");
            }
        } catch (error) {
            console.log(error);
        }
    };

    const daysOfWeek = [
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
    ];

    const goalsList = [
        "Cardio",
        "Olympic Weightlifting",
        "Plyometrics",
        "Powerlifting",
        "Strength",
        "Stretching",
        "Strongman"
        
    ];

    const handleDayCheckboxChange = (day: string) => {
        setAvailability((prevAvailability) =>
            prevAvailability.includes(day)
                ? prevAvailability.filter((d) => d !== day)
                : [...prevAvailability, day]
        );
    };

    const handleGoalCheckboxChange = (goal: string) => {
        setGoals((prevGoal) =>
            prevGoal.includes(goal)
                ? prevGoal.filter((g) => g !== goal)
                : [...prevGoal, goal]
        );
    };


    return (
        <div id="register" className="text-center">
            <h1>Complete User Details</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-input-group">
                    <label htmlFor="preferredName">Preferred Name</label>
                    <input
                        type="text"
                        name="preferredName"
                        placeholder="preferred name"
                        required
                        onChange={(e) => setPreferredName(e.target.value)}
                    />
                </div>
                <div className="form-input-group">
                    <label htmlFor="availability">Availability (Select all that apply)</label>
                    <div className="checkbox-group">
                        {daysOfWeek.map((day) => (
                            <div key={day} className="checkbox-item">
                                <label>
                                    <input
                                        type="checkbox"
                                        value={day}
                                        checked={availability.includes(day)}
                                        onChange={() => handleDayCheckboxChange(day)}
                                    />
                                    {day}
                                </label>
                            </div>
                        ))}
                    </div>
                </div>
                <div className="form-input-group">
                    <label htmlFor="birthday">Date of Birth</label>
                    <input
                        type="date"
                        name="birthday"
                        placeholder="date of birth"
                        required
                        onChange={(e) => setBirthday(e.target.value ? new Date(e.target.value) : null)}
                    />
                </div>
                <div className="form-input-group">
                    <label htmlFor="gender">Gender</label>
                    <select name="gender" onChange={(e) => setGender(e.target.value)}>
                        <option></option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
                <div className="form-input-group">
                    <label htmlFor="restrictions">Restrictions</label>
                    <input
                        type="text"
                        name="restrictions"
                        placeholder="i.e. 'limited shoulder mobility'"
                        required
                        onChange={(e) => setRestrictions(e.target.value)}
                    />
                </div>
                <div className="form-input-group">
                    <label htmlFor="goals">Goals (Select all that apply)</label>
                    <div className="checkbox-group">
                        {goalsList.map((goal) => (
                            <div key={goal} className="checkbox-item">
                                <label>
                                    <input
                                        type="checkbox"
                                        value={goal}
                                        checked={goals.includes(goal)}
                                        onChange={() => handleGoalCheckboxChange(goal)}
                                    />
                                    {goal}
                                </label>
                            </div>
                        ))}
                    </div>
                </div>
                <div className="form-input-group">
                    <label htmlFor="comments">Other Comments</label>
                    <input
                        type="text"
                        name="comments"
                        placeholder="other comments"
                        required
                        onChange={(e) => setComment(e.target.value)}
                    />
                </div>
                <button type="submit">Save Details</button>
            </form>
        </div>
    );
};

export default UserDetails;
