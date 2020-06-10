package com.project.app.repository;

import com.project.app.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findAllByCurrencyShortName(String shortName);
    Optional<Rate> findTopByCurrencyShortNameOrderByDateDesc(String shortName);
}
