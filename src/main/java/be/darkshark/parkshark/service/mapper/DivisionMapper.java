package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.DivisionDto;
import be.darkshark.parkshark.domain.entity.Division;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DivisionMapper {

    public Collection<DivisionDto> mapCollectionToDivisionDto(Collection<Division> divisions) {
        return divisions.stream()
                .map(division -> new DivisionDto()
                        .setId(division.getId())
                        .setName(division.getName())
                        .setOriginalName(division.getOriginalName())
                        .setDirector_id(1L)
                        .setParent_division_id(division.getParentDivision() == null ? -1 : division.getParentDivision().getId()))
                .collect(Collectors.toList());
    }

}
