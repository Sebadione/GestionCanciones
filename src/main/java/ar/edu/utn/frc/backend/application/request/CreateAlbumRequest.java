package ar.edu.utn.frc.backend.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAlbumRequest {

    @NotBlank(message = "Title is required")
    String title;

    @NotNull(message = "Artist Id is required")
    Integer artistId;
}
