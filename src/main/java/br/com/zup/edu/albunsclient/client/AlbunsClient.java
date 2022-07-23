package br.com.zup.edu.albunsclient.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "albunsClient",
        url = "http://localhost:8080/oauth2-resourceserver-minhas-figurinhas"
)
public interface AlbunsClient {

    @PostMapping("/api/albuns")
    public Response cadastraAlbum(@RequestBody NovoAlbumRequest request);

    @GetMapping("/api/albuns/{id}")
    public Optional<DetalhesDoAlbumResponse> detalhaAlbum(@PathVariable Long id);

}
