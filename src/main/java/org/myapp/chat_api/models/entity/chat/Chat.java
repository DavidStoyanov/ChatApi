package org.myapp.chat_api.models.entity.chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myapp.chat_api.models.entity.User;
import org.myapp.chat_api.models.entity.base.BaseEntity;
import org.myapp.chat_api.core.component.GeneratorAccessor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chats")
@Getter
@Setter
@NoArgsConstructor
public class Chat extends BaseEntity {

    @Column(name = "conversation_id", nullable = false, unique = true)
    private String conversationId;

    @ManyToMany
    @JoinTable(name = "chats_users",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_chats_users_chats"),
            inverseForeignKey = @ForeignKey(name = "fk_chats_users_users"))
    private Set<User> participants;

    @OneToMany
    @JoinColumn(name = "chat_id", foreignKey = @ForeignKey(name = "fk_messages_chats"))
    private List<Message> messages;


    @PrePersist
    protected void onCreate() {
        this.conversationId = GeneratorAccessor.getGenerator().generate();
    }
}
