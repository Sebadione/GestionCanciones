package ar.edu.utn.frc.backend.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTrackRequest {

    @NotBlank(message = "Name is required")
    String name;

    @NotNull(message = "Album is required")
    Integer albumId;

    @NotNull(message = "Media Type is required")
    Integer mediaTypeId;

    @NotNull(message = "Genre is required")
    Integer genreId;

    @NotBlank(message = "Composer is required")
    String composer;

    @NotNull(message = "Milliseconds is required")
    Integer milliseconds;

    @NotNull(message = "Bytes is required")
    Integer bytes;

    @NotNull(message = "Unit price is required")
    Double unitPrice;

}
