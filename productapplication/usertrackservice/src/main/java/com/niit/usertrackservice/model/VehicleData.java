package com.niit.usertrackservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleData {
    @Id
    private int vehicleId;
    private  String vehicleName;
    private String model;
}
