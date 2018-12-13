package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {
    private String albumsUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String moviesUrl, RestOperations restOperations) {
        this.albumsUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo albumInfo) {
        restOperations.postForEntity(albumsUrl, albumInfo, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        return restOperations.getForObject(albumsUrl + "/{id}", AlbumInfo.class, id);
    }

    public List<AlbumInfo> getAlbums() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(albumsUrl);
        return restOperations.exchange(builder.toUriString(), GET, null, albumListType).getBody();
    }

    public void deleteAlbumId(Long albumId) {
        restOperations.delete(albumsUrl + "/" + albumId);
    }

}
