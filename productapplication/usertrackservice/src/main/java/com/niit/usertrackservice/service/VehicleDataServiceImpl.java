package com.niit.usertrackservice.service;

import com.niit.usertrackservice.exception.VehicleDataAlreadyExists;
import com.niit.usertrackservice.exception.VehicleDataNotFound;
import com.niit.usertrackservice.model.VehicleData;
import com.niit.usertrackservice.repository.VehicleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDataServiceImpl implements VehicleDataService {
    VehicleDataRepository vehicleDataRepository;
@Autowired
    public VehicleDataServiceImpl(VehicleDataRepository vehicleDataRepository) {
        this.vehicleDataRepository = vehicleDataRepository;
    }

    @Override
    public VehicleData saveVehicleData( VehicleData vehicleData) throws VehicleDataAlreadyExists {
        if(vehicleDataRepository.findById(vehicleData.getVehicleId()).isPresent()){
            throw new VehicleDataAlreadyExists();
        }
        return vehicleDataRepository.save(vehicleData);
    }

    @Override
    public List<VehicleData> getAllVehicleData() throws Exception {
        return vehicleDataRepository.findAll();
    }

    @Override
    public VehicleData updateVehicleData(int vehicleId,String vehicleName) throws VehicleDataNotFound {
        if (vehicleDataRepository.findById(vehicleId).isEmpty()){
            throw new VehicleDataNotFound();
        }
        VehicleData vehicleData=vehicleDataRepository.findById(vehicleId).get();
        vehicleData.setVehicleName(vehicleName);
        return vehicleDataRepository.findById(vehicleId).get();
    }

}
