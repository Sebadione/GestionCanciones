package ar.edu.utn.frc.backend.application.controllers;

import ar.edu.utn.frc.backend.application.ResponseHandler;
import ar.edu.utn.frc.backend.application.request.CreateAlbumRequest;
import ar.edu.utn.frc.backend.application.request.UpdateAlbumRequest;
import ar.edu.utn.frc.backend.application.response.AlbumResponse;
import ar.edu.utn.frc.backend.services.AlbumService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/albums")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlbumController {

    AlbumService albumService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val albums = albumService.findAll()
                    .stream()
                    .map(AlbumResponse::from)
                    .toList();

            return ResponseHandler.success(albums);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return albumService.findById(id)
                    .map(album -> ResponseHandler.success(AlbumResponse.from(album)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateAlbumRequest request) {
        try {
            val album = albumService.create(request.getTitle(), request.getArtistId());
            return ResponseHandler.success("Se creó el album");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateAlbumRequest request) {
        try {
            albumService.update(id, request.getTitle(), request.getArtistId());
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
            albumService.delete(id);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}