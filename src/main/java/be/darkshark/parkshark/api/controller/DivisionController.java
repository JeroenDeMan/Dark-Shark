package be.darkshark.parkshark.api.controller;

import be.darkshark.parkshark.api.dto.division.CreateDivisionDto;
import be.darkshark.parkshark.api.dto.division.DivisionDto;
import be.darkshark.parkshark.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/divisions")
public class DivisionController {

    private DivisionService divisionService;

    @Autowired
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDivision(@RequestBody CreateDivisionDto createDivisionDto) {
        divisionService.createDivision(createDivisionDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<DivisionDto> getAll() {
       // myLogger.info("List of all books was requested.");
        Collection<DivisionDto> divisionsToReturn = divisionService.getAll();
        return divisionsToReturn;
    }

}
