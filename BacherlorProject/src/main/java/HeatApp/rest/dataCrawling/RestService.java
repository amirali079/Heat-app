package HeatApp.rest.dataCrawling;

import HeatApp.rest.RestTemplateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestService {

    public static ResponseEntity<String> get(RestTemplate restTemplate, Integer number,Integer offset) {


        String NETWORK_URI = String.format("https://api.spoonacular.com/recipes/complexSearch?apiKey=9c9d4b13164f4c0299176f4db1bd95e9&number=%d&offset=%d",number,offset);

        return RestTemplateUtil.createGet(restTemplate, NETWORK_URI,"", String .class);
    }

    public static ResponseEntity<String> getData(RestTemplate restTemplate, String number,String key) {


        String NETWORK_URI = String.format("https://api.spoonacular.com/recipes/%s/information?apiKey=%s&includeNutrition=true",number,key);

        return RestTemplateUtil.createGet(restTemplate, NETWORK_URI,"", String .class);
    }

    public static ResponseEntity<String> searchData(RestTemplate restTemplate, String keyword,String key,Integer offset) {

        String NETWORK_URI = String.format("https://api.spoonacular.com/recipes/complexSearch?apiKey=%s&number=100&offset=%d&query=%s",key,offset,keyword);

        return RestTemplateUtil.createGet(restTemplate, NETWORK_URI,"", String .class);
    }


}
