package com.desirArman.tickets.repositories;

<<<<<<< Updated upstream
public interface EventRepository {
=======
import com.desirArman.tickets.domain.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
>>>>>>> Stashed changes
}
