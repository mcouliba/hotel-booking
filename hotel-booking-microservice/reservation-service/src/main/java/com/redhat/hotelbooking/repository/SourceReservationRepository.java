package com.redhat.hotelbooking.repository;

import com.redhat.hotelbooking.bean.SourceReservation;
import org.springframework.data.repository.CrudRepository;

public interface SourceReservationRepository extends CrudRepository<SourceReservation, Integer> {
}