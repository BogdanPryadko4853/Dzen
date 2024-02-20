package com.dzen.Repositories;

import com.dzen.models.CurrentEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<CurrentEvents,Long> {
    List<CurrentEvents> findByTitle(String title);
}
