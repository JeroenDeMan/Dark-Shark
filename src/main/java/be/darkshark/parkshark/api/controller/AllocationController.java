package be.darkshark.parkshark.api.controller;

import be.darkshark.parkshark.api.dto.allocation.CreateAllocationDTO;
import be.darkshark.parkshark.api.dto.allocation.GetAllocationDTO;
import be.darkshark.parkshark.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

    private final AllocationService allocationService;

    @Autowired
    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GetAllocationDTO createAllocation (@RequestBody CreateAllocationDTO createAllocationDTO) {
        return allocationService.createAllocation(createAllocationDTO);
    }

    @PutMapping(path = "/{allocationId}/{memberId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GetAllocationDTO stopAllocation (@PathVariable long allocationId, @PathVariable long memberId) {
        return allocationService.stopAllocation(allocationId, memberId);
    }

}
