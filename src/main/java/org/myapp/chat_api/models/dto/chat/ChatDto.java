package org.myapp.chat_api.models.dto.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatDto {
    private String id;
    private String title;
    private Integer participantsSize;
    private boolean isDisabled;

    public ChatDto() {
    }

    public ChatDto(String id, String title, Integer participantsSize, boolean isDisabled) {
        this.id = id;
        this.title = title;
        this.participantsSize = participantsSize;
        this.isDisabled = isDisabled;
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

    @JsonProperty("isDisabled")
    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
