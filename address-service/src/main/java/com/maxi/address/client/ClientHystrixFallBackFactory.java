package com.maxi.address.client;

import com.maxi.address.model.Client;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientHystrixFallBackFactory implements FallbackFactory<ClientClient> {

    @Override
    public ClientClient create(Throwable throwable) {
        return new ClientClient() {
            @Override
            public ResponseEntity<Client> findClientByName(String name) {
                Client client = Client.builder()
                        .id(null)
                        .build();
                return ResponseEntity.ok(client);
            }
        };
    }
}
