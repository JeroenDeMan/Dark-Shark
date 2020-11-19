package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.allocation.CreateAllocationDTO;
import be.darkshark.parkshark.api.dto.allocation.GetAllocationDTO;
import be.darkshark.parkshark.api.dto.person.GetMembersDTO;
import be.darkshark.parkshark.domain.entity.Allocation;
import org.springframework.stereotype.Component;

@Component
public class AllocationMapper {

    public Allocation toEntity(CreateAllocationDTO createAllocationDTO) {
        return new Allocation(createAllocationDTO.getLicencePlate());
    }

    public GetAllocationDTO getAllocationDTO(Allocation allocation) {
        GetAllocationDTO result = new GetAllocationDTO();
        result.setAllocationId(allocation.getId());
        result.setStartTime(allocation.getStartTime().toString());
        result.setEndTime(allocation.getEndTime() == null ? " " : allocation.getEndTime().toString());
        result.setParkingLotId(allocation.getParkingLot().getId());
        result.setParkingLotName(allocation.getParkingLot().getName());
        return result;
    }

}
