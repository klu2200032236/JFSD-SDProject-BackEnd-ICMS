package com.example.cultureapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cultureapp.model.User;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByUsername(String username);
}
