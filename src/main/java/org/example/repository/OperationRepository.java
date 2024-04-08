package org.example.repository;

import org.example.entity.Operation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findOperationsByClientId(Long id);

    @Query("SELECT b FROM Operation b WHERE b.client.id = :id AND b.time_operation BETWEEN :beginDate AND  :endDate")
    List<Operation> findOperationsByClientIdAndDateRange(@Param("id") Long id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);



}
