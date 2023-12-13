package ar.edu.utn.frc.backend.application.controllers;

import ar.edu.utn.frc.backend.application.ResponseHandler;
import ar.edu.utn.frc.backend.application.request.CreateTrackRequest;
import ar.edu.utn.frc.backend.application.request.UpdateTrackRequest;
import ar.edu.utn.frc.backend.application.response.TrackResponse;
import ar.edu.utn.frc.backend.services.TrackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TrackController {

    TrackService trackService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val tracks = trackService.findAll()
                    .stream()
                    .map(TrackResponse::from)
                    .toList();
            return ResponseHandler.success(tracks);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return trackService.findById(id)
                    .map(track -> ResponseHandler.success(TrackResponse.from(track)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateTrackRequest request) {
        try {
            val track = trackService.create(request.getName(), request.getAlbumId(), request.getMediaTypeId(),
                    request.getGenreId(), request.getComposer(), request.getMilliseconds(), request.getBytes(), request.getUnitPrice());
            return ResponseHandler.success(track);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateTrackRequest request) {
        try {
            trackService.update(id, request.getName(), request.getAlbumId(), request.getMediaTypeId(),
                    request.getGenreId(), request.getComposer(), request.getMilliseconds(), request.getBytes(), request.getUnitPrice());
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            trackService.delete(id);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

}
