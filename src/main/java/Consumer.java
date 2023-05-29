
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users/";
        // Первая операция + получение куки
        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,null,String.class);
        HttpHeaders headers = response.getHeaders();
        String cookie = headers.getFirst(HttpHeaders.SET_COOKIE);
        System.out.println(response.getBody());


        //Вторая операция
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Cookie", cookie);
        Map<String,String> json1 = new HashMap<>();
        json1.put("id","3");
        json1.put("name","James");
        json1.put("lastName","Brown");
        json1.put("age","24");
        HttpEntity<Map<String,String>> request1 = new HttpEntity<>(json1,headers1);

        ResponseEntity<String> response2 = restTemplate.exchange(url,HttpMethod.POST, request1,String.class);

        System.out.println(response2.getBody());

        //Третья операция
        Map<String,String> json2 = new HashMap<>();
        json1.put("id","3");
        json1.put("name","Thomas");
        json1.put("lastName","Shelby");
        json1.put("age","24");
        HttpEntity<Map<String,String>> request2 = new HttpEntity<>(json2,headers1);
        ResponseEntity<String> response3 = restTemplate.exchange(url,HttpMethod.PUT, request1,String.class);
        System.out.println(response3.getBody());


        //Четвертая операция
        HttpEntity<Map<String,String>> request3 = new HttpEntity<>(headers1);
        ResponseEntity<String> response4 = restTemplate.exchange(url + "3", HttpMethod.DELETE, request3,String.class);
        System.out.println(response4.getBody());
    }


}
