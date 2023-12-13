package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.models.MediaType;
import ar.edu.utn.frc.backend.repositories.IdentifierRepository;
import ar.edu.utn.frc.backend.repositories.MediaTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MediaTypeServiceImpl implements MediaTypeService{

    MediaTypeRepository mediaTypeRepository;
    IdentifierRepository identifierRepository;

    @Override
    public List<MediaType> findAll() {
        return mediaTypeRepository.findAll();
    }

    @Override
    public Optional<MediaType> findById(Integer id) {
        return mediaTypeRepository.findById(id);
    }

    @Override
    public MediaType create(String name) {
        val mediaTypeId = identifierRepository.nextValue(MediaType.TABLE_NAME);
        val mediaType = new MediaType(mediaTypeId, name);
        return mediaTypeRepository.save(mediaType);
    }

    @Override
    public void delete(Integer id) {
        try {
            mediaTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Media Type not found");
        }
    }

    @Override
    public void update(Integer id, String name) {
        val mediaType = mediaTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Media Type not found"));

        mediaType.update(name);

        mediaTypeRepository.save(mediaType);

    }
}
