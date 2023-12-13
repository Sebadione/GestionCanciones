package ar.edu.utn.frc.backend.application.response;

import ar.edu.utn.frc.backend.models.Track;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackResponse {

    Integer id;
    String name;
    String album;
    String mediaType;
    String genre;
    String composer;
    Integer milliseconds;
    Integer bytes;
    Double unitPrice;

    public static TrackResponse from(Track track) {
        return TrackResponse.builder()
                .id(track.getId())
                .name(track.getName())
                .album(track.getAlbum().getTitle())
                .mediaType(track.getMediaType().getName())
                .genre(track.getGenre().getName())
                .composer(track.getComposer())
                .milliseconds(track.getMilliseconds())
                .bytes(track.getBytes())
                .unitPrice(track.getUnitPrice()).build();
    }
}
