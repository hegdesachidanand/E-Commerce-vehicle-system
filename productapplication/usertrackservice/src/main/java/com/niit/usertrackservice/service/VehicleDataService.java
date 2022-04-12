package com.niit.usertrackservice.service;

import com.niit.usertrackservice.exception.VehicleDataAlreadyExists;
import com.niit.usertrackservice.exception.VehicleDataNotFound;
import com.niit.usertrackservice.model.VehicleData;

import java.util.List;

public interface VehicleDataService {
    VehicleData saveVehicleData( VehicleData vehicleData) throws VehicleDataAlreadyExists;
    List<VehicleData> getAllVehicleData() throws Exception;
    VehicleData updateVehicleData(int vehicleId,String vehicleName) throws VehicleDataNotFound;

}
