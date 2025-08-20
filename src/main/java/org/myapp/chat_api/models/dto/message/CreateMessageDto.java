package org.myapp.chat_api.models.dto.message;

public class CreateMessageDto {
    private Long sender;
    private String content;

    public CreateMessageDto() {
    }

    public CreateMessageDto(Long sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
