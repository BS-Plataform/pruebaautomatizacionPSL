package com.psl.pruebas.selenium;


import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by marcos on 13/01/17.
 */
public class PslRestClientTest  {


    @Test
    public void validateCodeStatus() throws IOException {
        Client client = ClientBuilder.newClient();
        Response response = client.target("https://jsonplaceholder.typicode.com/todos").request("text/plain").get();



        assertThat(response.getStatus())
                .isEqualTo(200);

        assertThat(response.getEntity())
                .isNotNull();


    }





}