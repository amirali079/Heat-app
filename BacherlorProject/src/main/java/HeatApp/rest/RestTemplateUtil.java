package HeatApp.rest;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

public class RestTemplateUtil {

    public static <T> ResponseEntity<T>
    createPost(RestTemplate restTemplate, String networkPath, String mainPath,
               String subPath, Map<String, Object> jsonRequestBody, Class<T> responseClass) {

        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(jsonRequestBody, headers);

        return restTemplate.postForEntity(networkPath + mainPath + subPath, entity, responseClass);
    }

    public static <T> ResponseEntity<T>
    createPatch(RestTemplate restTemplate, String networkPath, String mainPath,
                String subPath, String pathVariable, Map<String, Object> jsonRequestBody, Class<T> responseClass) {

        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(jsonRequestBody, headers);

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(500);
        requestFactory.setReadTimeout(500);
        restTemplate.setRequestFactory(requestFactory);

        return restTemplate.exchange(networkPath + mainPath + subPath, HttpMethod.PATCH, entity, responseClass, pathVariable);
    }

    public static <T> ResponseEntity<T>
    createGet(RestTemplate restTemplate, String path, String pathVariable, Class<T> responseClass) {

        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate.getForEntity(path , responseClass, pathVariable);
    }


}
