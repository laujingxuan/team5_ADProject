package nus.edu.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
