package fitnessapp.model;

import jakarta.validation.constraints.NotEmpty;

public class UpdateUserRoleDto {

    @NotEmpty
    private String role;

    public UpdateUserRoleDto(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
