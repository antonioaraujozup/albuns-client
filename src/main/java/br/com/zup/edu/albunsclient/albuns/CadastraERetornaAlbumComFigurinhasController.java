package br.com.zup.edu.albunsclient.albuns;

import br.com.zup.edu.albunsclient.client.AlbunsClient;
import br.com.zup.edu.albunsclient.client.DetalhesDoAlbumResponse;
import br.com.zup.edu.albunsclient.client.NovoAlbumRequest;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastraERetornaAlbumComFigurinhasController {

    @Autowired
    private AlbunsClient client;

    @PostMapping("/albuns")
    public ResponseEntity<?> cadastraERetorna(@RequestBody @Valid NovoAlbumRequest request,
                                              UriComponentsBuilder uriComponentsBuilder) {

        Response response = client.cadastraAlbum(request);

        String location = response.headers().get("location").toString();

        int beginIndex = location.lastIndexOf("/") + 1;
        int endIndex = location.lastIndexOf("]");
        Long idAlbum = Long.parseLong(location.substring(beginIndex,endIndex));

        DetalhesDoAlbumResponse detalhesDoAlbumResponse = client.detalhaAlbum(idAlbum)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O álbum de id = %s não foi encontrado".formatted(idAlbum)));

        URI uri = uriComponentsBuilder.path("/albuns/{id}")
                .buildAndExpand(idAlbum)
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(detalhesDoAlbumResponse);
    }

}