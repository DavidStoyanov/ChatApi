package org.myapp.chat_api.models.dto.chat;

public class UpdateChatDto {
    private String title;
    private Boolean disable;

    public UpdateChatDto() {
    }

    public UpdateChatDto(String title, boolean disable) {
        this.title = title;
        this.disable = disable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
}
