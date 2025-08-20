package org.myapp.chat_api.models.dto.chat;

public class ChatDto {
    private String id;
    private String title;
    private Integer participantsSize;

    public ChatDto() {
    }

    public ChatDto(String id, String title, Integer participantsSize) {
        this.id = id;
        this.title = title;
        this.participantsSize = participantsSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParticipantsSize() {
        return participantsSize;
    }

    public void setParticipantsSize(Integer participantsSize) {
        this.participantsSize = participantsSize;
    }
}
