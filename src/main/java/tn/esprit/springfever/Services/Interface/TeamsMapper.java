package tn.esprit.springfever.Services.Interface;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import tn.esprit.springfever.dto.TeamsDTO;
import tn.esprit.springfever.entities.Teams;
@Mapper(componentModel = "spring")

@Service
public interface TeamsMapper {
    TeamsMapper INSTANCE = Mappers.getMapper(TeamsMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeamsFromDto(TeamsDTO teamsDto, @MappingTarget Teams teams);

}
