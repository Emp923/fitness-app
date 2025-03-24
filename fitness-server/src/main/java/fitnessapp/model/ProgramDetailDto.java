package fitnessapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProgramDetailDto {

    private int id;
    private String name;
    private int createdBy;
    private List<ProgramExerciseDto> programExercises;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("createdBy")
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("programExercises")
    public List<ProgramExerciseDto> getProgramExercises() {
        return programExercises;
    }

    public void setProgramExercises(List<ProgramExerciseDto> programExercises) {
        this.programExercises = programExercises;
    }
}
