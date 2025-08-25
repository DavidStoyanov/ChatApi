package org.myapp.chat_api.models.entity.chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myapp.chat_api.models.entity.User;
import org.myapp.chat_api.models.entity.base.BaseEntity;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private User sender;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "is_delivered")
    private Boolean isDelivered;

    @Column(name = "is_spoiler")
    private Boolean isSpoiler;

    @ManyToMany
    @JoinTable(name = "messages_users_seen_by",
            joinColumns = @JoinColumn(name = "message_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_messages_users_messages_seen_by"),
            inverseForeignKey = @ForeignKey(name = "fk_messages_users_users_seen_by"))
    private Set<User> seenBy;

    @ManyToMany
    @JoinTable(name = "messages_users_deleted_for",
            joinColumns = @JoinColumn(name = "message_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_messages_users_messages_deleted_for"),
            inverseForeignKey = @ForeignKey(name = "fk_messages_users_users_deleted_for"))
    private Set<User> deletedFor;

    /*
    * Bi-direction relation with Chat Entity
    * */
    /*@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_messages_chats"))
    private Chat chat;*/

    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
    }
}
