package fitnessapp.model;

public class Program {

    private int id;
    private String dayOfTheWeek;
    private String exercise;
    private int resistance;
    private int sets;
    private int repetitions;
    private int userId;

    public Program(){

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

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Program(int id, String dayOfTheWeek, String exercise, int resistance, int sets, int repetitions, int userId) {
        this.id = id;
        this.dayOfTheWeek = dayOfTheWeek;
        this.exercise = exercise;
        this.resistance = resistance;
        this.sets = sets;
        this.repetitions = repetitions;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
                ", exercise='" + exercise + '\'' +
                ", resistance=" + resistance +
                ", sets=" + sets +
                ", repetitions=" + repetitions +
                ", userId=" + userId +
                '}';
    }
}
