package ar.edu.utn.frc.backend.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackToPlaylistRequest {

    @NotNull(message = "Track Id is required")
    Integer trackId;
}
