package fitnessapp.model;

public class TrainerDetails {

    private int userId;
    private String name;
    private String bio;
    private String specialtyOne;
    private String specialtyTwo;
    private String certification;
    private String photograph;

    public TrainerDetails(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSpecialtyOne() {
        return specialtyOne;
    }

    public void setSpecialtyOne(String specialtyOne) {
        this.specialtyOne = specialtyOne;
    }

    public String getSpecialtyTwo() {
        return specialtyTwo;
    }

    public void setSpecialtyTwo(String specialtyTwo) {
        this.specialtyTwo = specialtyTwo;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getPhotograph() {
        return photograph;
    }

    public void setPhotograph(String photograph) {
        this.photograph = photograph;
    }

    public TrainerDetails(int trainerId, String name, String bio, String specialtyOne, String specialtyTwo, String certification, String photograph) {
        this.userId = trainerId;
        this.name = name;
        this.bio = bio;
        this.specialtyOne = specialtyOne;
        this.specialtyTwo = specialtyTwo;
        this.certification = certification;
        this.photograph = photograph;
    }

    @Override
    public String toString() {
        return "TrainerDetails{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", specialtyOne='" + specialtyOne + '\'' +
                ", specialtyTwo='" + specialtyTwo + '\'' +
                ", certification='" + certification + '\'' +
                ", photograph='" + photograph + '\'' +
                '}';
    }
}
