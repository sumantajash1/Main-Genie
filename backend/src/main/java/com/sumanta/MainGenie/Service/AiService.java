package com.sumanta.MainGenie.Service;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AiService {

    private String apiUrl = "";
    private String apiKey = "";

    public AiService() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("API_KEY");
        this.apiUrl = dotenv.get("API_URL");
    }

    public String aiApiCall(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.set("Content-Type", "application/json");
        List<Map<String, String>> messages = List.of(Map.of("role", "user", "content", prompt));
        Map<String, Object> requestBody = Map.of(
                "messages", messages,
                "model", "llama-3.1-8b-instant",
                "max_completion_tokens", 8000
        );
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, Map.class);
//            System.out.println(responseEntity);
            Map<String, Object> responseBody = responseEntity.getBody();
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String content = (String) message.get("content");
            //System.out.println(content);
            String returnBody = content.substring(content.indexOf('$') + 1, content.lastIndexOf('$')).trim();
            System.out.println(returnBody);
            return returnBody;
//            return content;
        } catch (Exception e) {
            log.error("Error in AI API call: {}", e.getMessage());
            e.printStackTrace();
        }
        return "error";
    }
}