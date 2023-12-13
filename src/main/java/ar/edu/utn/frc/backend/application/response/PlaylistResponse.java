package ar.edu.utn.frc.backend.application.response;

import ar.edu.utn.frc.backend.models.Playlist;
import ar.edu.utn.frc.backend.models.Track;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaylistResponse {

    Integer id;

    String name;

    List<TrackResponse> tracks;

    public static PlaylistResponse from(Playlist playlist) {
        return PlaylistResponse.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .tracks(playlist.getTracks().stream().map(TrackResponse::from).toList())
                .build();
    }
}
