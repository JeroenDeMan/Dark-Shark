package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.allocation.CreateAllocationDTO;
import be.darkshark.parkshark.api.dto.allocation.GetAllocationDTO;
import be.darkshark.parkshark.domain.entity.Allocation;
import be.darkshark.parkshark.domain.entity.util.FormatUtil;
import org.springframework.stereotype.Component;

@Component
public class AllocationMapper {

    public Allocation toEntity(CreateAllocationDTO createAllocationDTO) {
        return new Allocation(createAllocationDTO.getLicencePlate());
    }

    public GetAllocationDTO getAllocationDTO(Allocation allocation) {
        GetAllocationDTO result = new GetAllocationDTO();
        result.setAllocationId(allocation.getId());
        result.setStartTime(allocation.getStartTime().format(FormatUtil.getDateTimeFormat()));
        result.setEndTime(allocation.getEndTime() == null ? " " : allocation.getEndTime().format(FormatUtil.getDateTimeFormat()));
        result.setParkingLotId(allocation.getParkingLot().getId());
        result.setParkingLotName(allocation.getParkingLot().getName());
        result.setStatus(allocation.getStatus().toString());
        return result;
    }

}
