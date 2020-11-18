package be.darkshark.parkshark.api.controller;

import be.darkshark.parkshark.api.dto.CreateDivisionDto;
import be.darkshark.parkshark.api.dto.CreateParkingLotDto;
import be.darkshark.parkshark.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/parking-lots")
public class ParkingLotController {

    private ParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {
        parkingLotService.createParkingLot(createParkingLotDto);
    }

}
