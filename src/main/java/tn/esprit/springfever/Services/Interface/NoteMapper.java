package tn.esprit.springfever.Services.Interface;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import tn.esprit.springfever.dto.NoteDTO;
import tn.esprit.springfever.entities.Note;
@Mapper(componentModel = "spring")

@Service
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNoteFromDto(NoteDTO noteDTO, @MappingTarget Note note);

}
