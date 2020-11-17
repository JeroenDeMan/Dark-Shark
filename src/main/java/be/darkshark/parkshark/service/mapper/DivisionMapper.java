package be.darkshark.parkshark.service.mapper;

import be.darkshark.parkshark.api.dto.CreateDivisionDto;
import be.darkshark.parkshark.api.dto.DivisionDto;
import be.darkshark.parkshark.domain.entity.Division;
import be.darkshark.parkshark.domain.entity.person.Employee;
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
                                .setParent_division_id(division.getParentDivision() == null ? "" : String.valueOf(division
                                        .getParentDivision().getId())))
                        .collect(Collectors.toList());
    }

    public Division maptoDivision(CreateDivisionDto createDivisionDto, Employee director, Division parentDivision) {
        return new Division(createDivisionDto.getName(), createDivisionDto.getOriginalName(), director, parentDivision);
    }
}
