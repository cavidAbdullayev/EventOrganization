package org.example.eventorganization.repository;

import org.example.eventorganization.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token>findTokenByToken(String token);
    Token findTokenByUser_Id(Integer userId);
}
