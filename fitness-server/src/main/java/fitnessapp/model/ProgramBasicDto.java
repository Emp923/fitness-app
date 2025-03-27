package fitnessapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgramBasicDto {

    private int id;
    private String name;
    private String createdBy;

    public ProgramBasicDto() {}

    public ProgramBasicDto(int id, String name, String createdBy) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
    }

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
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
