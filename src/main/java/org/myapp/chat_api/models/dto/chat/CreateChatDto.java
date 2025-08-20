package org.myapp.chat_api.models.dto.chat;

public class CreateChatDto {
    private String title;
    private Long creator;

    public CreateChatDto() {
    }

    public CreateChatDto(String title, Long creator) {
        this.title = title;
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }
}
