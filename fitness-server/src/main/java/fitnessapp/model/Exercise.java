package fitnessapp.model;

public class Exercise {

    private int id;
    private String recordedDate;
    private int resistance;
    private int sets;
    private int repetitions;
    private int exerciseId;

    public Exercise(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Exercise(int id, String recordedDate, int resistance, int sets, int repetitions, int exerciseId) {
        this.id = id;
        this.recordedDate = recordedDate;
        this.resistance = resistance;
        this.sets = sets;
        this.repetitions = repetitions;
        this.exerciseId = exerciseId;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", recordedDate='" + recordedDate + '\'' +
                ", resistance=" + resistance +
                ", sets=" + sets +
                ", repetitions=" + repetitions +
                ", exerciseId=" + exerciseId +
                '}';
    }
}
