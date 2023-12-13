package ar.edu.utn.frc.backend.application.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateArtistRequest {

    @NotBlank(message = "Name is required")
    String name;
}
