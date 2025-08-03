package com.alibou.WebSocket.repository;

import com.alibou.WebSocket.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderAndRecipientId(String senderId, String recipientId);
}
