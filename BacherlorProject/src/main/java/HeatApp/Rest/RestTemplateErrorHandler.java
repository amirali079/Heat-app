package HeatApp.Rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return new DefaultResponseErrorHandler().hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            //log.error(String.valueOf(response.getStatusCode()));

        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {

            //log.error(String.valueOf(response.getStatusCode()));

            //log.error(String.valueOf(response.getBody()));

            HttpHeaders headers = response.getHeaders();
            //log.error(String.valueOf(headers.get("Content-Type")));
            //log.error(String.valueOf(headers.get("Server")));
        }
    }
}