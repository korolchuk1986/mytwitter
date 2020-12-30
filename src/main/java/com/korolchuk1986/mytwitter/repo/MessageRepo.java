package com.korolchuk1986.mytwitter.repo;

import com.korolchuk1986.mytwitter.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
