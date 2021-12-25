package service;

import model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private static final String URL_ADDRESS = "http://91.241.64.178:7081/api/users";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    String cookie = "";

    public void readUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity(URL_ADDRESS, String.class);
        HttpHeaders headers = response.getHeaders();
        System.out.println(response);
        cookie = headers.getFirst(headers.SET_COOKIE);

    }

    public String createUser(User user) {
        headers.add("Cookie", cookie);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(URL_ADDRESS, requestEntity, String.class);
        return result.getBody();
    }

    public String updateUser(User user) {
        headers.add("Cookie", cookie);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL_ADDRESS, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(long id) {
        HttpEntity<User> httpentity = new HttpEntity<>(headers);
        String result = restTemplate.exchange(URL_ADDRESS + "/" + id, HttpMethod.DELETE, httpentity, String.class).getBody();
        return result;
    }

}
