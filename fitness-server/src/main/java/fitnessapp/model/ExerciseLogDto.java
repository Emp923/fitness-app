package fitnessapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExerciseLogDto {

    private int id;
    private String recordedDate;
    private int resistance;
    private int sets;
    private int repetitions;
    private String exerciseName;
    private String comments;

    public ExerciseLogDto() {}

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("recordedDate")
    public String getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
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

    @JsonProperty("exerciseName")
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ExerciseLogDto(int id, String recordedDate, int resistance, int sets, int repetitions, String exerciseName, String comments) {
        this.id = id;
        this.recordedDate = recordedDate;
        this.resistance = resistance;
        this.sets = sets;
        this.repetitions = repetitions;
        this.exerciseName = exerciseName;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ExerciseLogDto{" +
                "id=" + id +
                ", recordedDate='" + recordedDate + '\'' +
                ", resistance=" + resistance +
                ", sets=" + sets +
                ", repetitions=" + repetitions +
                ", exerciseName='" + exerciseName + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
