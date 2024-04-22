package org.example.jenkinstest.repo;


import org.example.jenkinstest.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemRepo extends JpaRepository<Problem,Long> {
    void deleteProblemById(Long id);

    Optional<Problem> findProblemById(Long id);
}
