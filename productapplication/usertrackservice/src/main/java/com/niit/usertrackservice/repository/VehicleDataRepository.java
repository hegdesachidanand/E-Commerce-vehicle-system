package com.niit.usertrackservice.repository;

import com.niit.usertrackservice.model.VehicleData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDataRepository extends MongoRepository<VehicleData,Integer> {
}
