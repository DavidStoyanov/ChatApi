package org.myapp.chat_api.models.entity.chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myapp.chat_api.models.entity.User;
import org.myapp.chat_api.models.entity.base.BaseEntity;
import org.myapp.chat_api.core.component.GeneratorAccessor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "conversation_id", nullable = false, unique = true)
    private String conversationId;

    @Column(name = "disabled_on")
    private LocalDateTime disabledOn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false)
    private User creator;

    @ManyToMany
    @JoinTable(name = "chats_users",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_chats_users_chats"),
            inverseForeignKey = @ForeignKey(name = "fk_chats_users_users"))
    private Set<User> participants = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "chat_id", foreignKey = @ForeignKey(name = "fk_messages_chats"))
    private List<Message> messages;


    @PrePersist
    protected void onCreate() {
        //TODO: Do check before save entity
        /*while (repository.existsByConversationId(confId)) {
            confId = GeneratorAccessor.getGenerator().generate();
        }
        this.conversationId = confId;*/

        this.conversationId = GeneratorAccessor.getGenerator().generate();
    }
}
