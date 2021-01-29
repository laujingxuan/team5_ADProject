package nus.edi.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edi.iss.adproject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
