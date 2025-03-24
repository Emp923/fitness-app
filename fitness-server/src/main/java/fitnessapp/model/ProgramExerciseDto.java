package fitnessapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgramExerciseDto {

    private int id;
    private String dayOfTheWeek;
    private String exerciseName;
    private int resistance;
    private int sets;
    private int repetitions;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("dayOfTheWeek")
    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    @JsonProperty("exerciseName")
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @JsonProperty("resistance")
    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    @JsonProperty("sets")
    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    @JsonProperty("repetitions")
    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return "ProgramExerciseDto{" +
                "id=" + id +
                ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", resistance=" + resistance +
                ", sets=" + sets +
                ", repetitions=" + repetitions +
                '}';
    }
}
