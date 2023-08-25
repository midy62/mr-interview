package com.mondialrelay.data.repository;

import com.mondialrelay.data.entity.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentStatusRepository extends JpaRepository<ShipmentStatus, Long> {
}
