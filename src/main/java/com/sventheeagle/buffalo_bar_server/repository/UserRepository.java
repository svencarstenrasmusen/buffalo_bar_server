package com.sventheeagle.buffalo_bar_server.repository;

import com.sventheeagle.buffalo_bar_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
