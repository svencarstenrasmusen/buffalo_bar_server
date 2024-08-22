package com.sventheeagle.buffalo_bar_server.repository;

import com.sventheeagle.buffalo_bar_server.model.Buffalo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuffaloRepository extends JpaRepository<Buffalo, String> {
    public List<Buffalo> findAllByScalperId(String id);
    public List<Buffalo> findAllBySnaggeeId(String id);
}
