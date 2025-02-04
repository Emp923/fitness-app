package fitnessapp.model;

import java.time.LocalDate;

public class UserDetails {

    private int userId;
    private String preferredName;
    private String availability;
    private LocalDate birthday;
    private String gender;
    private String restrictions;
    private String goals;
    private String comments;

    public UserDetails() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public UserDetails(int userId, String preferredName, String availability, LocalDate birthday, String gender, String restrictions, String goals, String comments) {
        this.userId = userId;
        this.preferredName = preferredName;
        this.availability = availability;
        this.birthday = birthday;
        this.gender = gender;
        this.restrictions = restrictions;
        this.goals = goals;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", preferredName='" + preferredName + '\'' +
                ", availability='" + availability + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", restrictions='" + restrictions + '\'' +
                ", goals='" + goals + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
