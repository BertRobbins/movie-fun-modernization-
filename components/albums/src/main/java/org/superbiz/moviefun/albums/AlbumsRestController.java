package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsRestController {

    private final AlbumsRepository albumRepository;

    public AlbumsRestController(AlbumsRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("{id}")
    public Album find(@PathVariable Long id) {
        return albumRepository.find(id);
    }

    @GetMapping
    public List<Album> list() {
        return albumRepository.getAlbums();
    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        albumRepository.addAlbum(album);
    }
}


//test