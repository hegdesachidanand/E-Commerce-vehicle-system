package com.niit.usertrackservice.controller;

import com.niit.usertrackservice.exception.VehicleDataAlreadyExists;
import com.niit.usertrackservice.exception.VehicleDataNotFound;
import com.niit.usertrackservice.model.VehicleData;
import com.niit.usertrackservice.service.VehicleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleDataController {
    VehicleDataService userTrackService;
    @Autowired
    public VehicleDataController(VehicleDataService userTrackService) {
        this.userTrackService = userTrackService;
    }
    @PostMapping("/saveVehicleData")
    public ResponseEntity<?> saveVehicleData(@RequestBody VehicleData vehicleData) throws VehicleDataAlreadyExists {
        try {
            System.out.println(vehicleData);
            return new ResponseEntity<>(userTrackService.saveVehicleData(vehicleData), HttpStatus.CREATED);
        }
        catch (VehicleDataAlreadyExists e) {
            throw new VehicleDataAlreadyExists();
        }
        catch (Exception e){
           return new ResponseEntity<>("try after sometime",HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getAllData")
    public ResponseEntity<?> getAllUserTrackFromWishList() throws Exception {
        try {
            System.out.println(userTrackService.getAllVehicleData());
            return new ResponseEntity<>(userTrackService.getAllVehicleData(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("try after sometime",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/userdata/update/{vehicleId}/{vehicleName}")
    public ResponseEntity<?> updateUserTrackFromWishList(@PathVariable int vehicleId,@PathVariable String vehicleName) throws VehicleDataNotFound {
        try {
            return new ResponseEntity<>(userTrackService.updateVehicleData(vehicleId,vehicleName),HttpStatus.OK);
        } catch (VehicleDataNotFound e) {
            throw new VehicleDataNotFound();
        }
        catch (Exception e){
            return new ResponseEntity<>("try after some time",HttpStatus.NOT_FOUND);
        }
    }
}
