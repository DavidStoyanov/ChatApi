package org.myapp.chat_api.models.dto.user;

public class UserProfileDto {
    private String alias;
    private String firstName;
    private String lastName;

    public UserProfileDto() {
    }

    public UserProfileDto(String alias, String firstName, String lastName) {
        this.alias = alias;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
