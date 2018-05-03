package com.redhat.hotelbooking.repository;


import com.redhat.hotelbooking.bean.Authentication;
import com.redhat.hotelbooking.bean.Details;
import org.springframework.data.repository.CrudRepository;

public interface DetailsRepository extends CrudRepository<Details, Integer> {
}