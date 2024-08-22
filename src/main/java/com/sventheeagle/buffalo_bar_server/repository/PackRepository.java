package com.sventheeagle.buffalo_bar_server.repository;

import com.sventheeagle.buffalo_bar_server.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackRepository extends JpaRepository<Pack, String> {
}
