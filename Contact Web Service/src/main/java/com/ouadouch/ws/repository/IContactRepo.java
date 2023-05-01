package com.ouadouch.ws.repository;

import com.ouadouch.ws.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IContactRepo extends JpaRepository<Contact,Long> {
    @Override
    Optional<Contact> findById(Long id);
}
