package com.valerycrane.music.repository;

import com.valerycrane.music.entity.Composition;
import com.valerycrane.music.entity.Melody;
import com.valerycrane.music.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
