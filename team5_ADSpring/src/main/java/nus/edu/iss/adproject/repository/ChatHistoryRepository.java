package nus.edu.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

}