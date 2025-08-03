package com.alibou.WebSocket.repository;

import com.alibou.WebSocket.model.Status;
import com.alibou.WebSocket.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByStatus(Status status);
}
