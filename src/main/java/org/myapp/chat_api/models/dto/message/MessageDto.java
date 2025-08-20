package org.myapp.chat_api.models.dto.message;

public class MessageDto {
    private Long id;
    private String senderUsername;
    private String content;
    private String createdOn;

    public MessageDto() {
    }

    public MessageDto(Long id, String senderUsername, String content, String createdOn) {
        this.id = id;
        this.senderUsername = senderUsername;
        this.content = content;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}




