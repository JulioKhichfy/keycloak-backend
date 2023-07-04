package com.tutorial.keycloakbackend.repositories;

import com.tutorial.keycloakbackend.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FooRepository extends JpaRepository<Foo, Integer> {
}
