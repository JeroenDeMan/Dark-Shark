package be.darkshark.parkshark.api.controller;

import be.darkshark.parkshark.api.dto.division.DivisionDto;
import be.darkshark.parkshark.api.dto.parkinglot.CreateParkingLotDto;
import be.darkshark.parkshark.api.dto.parkinglot.DetailedParkingLotDto;
import be.darkshark.parkshark.api.dto.parkinglot.ParkingLotDto;
import be.darkshark.parkshark.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<ParkingLotDto> getAll() {
        Collection<ParkingLotDto> parkingLotsToReturn = parkingLotService.getAll();
        return parkingLotsToReturn;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DetailedParkingLotDto getOneParkingLotById(@PathVariable long id) {
        DetailedParkingLotDto ParkingLotToReturn = parkingLotService.getAParkingLotById(id);
        return ParkingLotToReturn;
    }

}
