package fitnessapp.model;

public class ProgramExercise {

    private int id;
    private String dayOfTheWeek;
    private String exerciseName;
    private int resistance;
    private int sets;
    private int repetitions;

    public ProgramExercise() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
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

    public ProgramExercise(int id, String dayOfTheWeek, String exerciseName, int resistance, int sets, int repetitions, int userId) {
        this.id = id;
        this.dayOfTheWeek = dayOfTheWeek;
        this.exerciseName = exerciseName;
        this.resistance = resistance;
        this.sets = sets;
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return "ProgramExercise{" +
                "id=" + id +
                ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
                ", exercise='" + exerciseName + '\'' +
                ", resistance=" + resistance +
                ", sets=" + sets +
                ", repetitions=" + repetitions +
                '}';
    }
}
