package org.myapp.chat_api.repository;

import org.myapp.chat_api.models.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByConversationId(String conversationId);
}
