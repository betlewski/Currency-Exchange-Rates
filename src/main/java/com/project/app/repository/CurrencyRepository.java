package com.project.app.repository;

import com.project.app.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findByShortName(String shortName);
    Optional<Currency> findByFullName(String fullName);
}
