package nus.edi.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edi.iss.adproject.model.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

}
