package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import entity.Order;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Stateless
public class RestClient {

    private static final String URL = "http://localhost:8080/rest/report";

    public List<Order> getReport(Date fromDate, Date toDate) throws IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String from = dateFormat.format(fromDate);
        String to = dateFormat.format(toDate);

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(URL);

        ClientResponse response = webResource
                .queryParam("from_date", from)
                .queryParam("to_date", to)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        return response.getEntity(new GenericType<List<Order>>() {});
    }
}
