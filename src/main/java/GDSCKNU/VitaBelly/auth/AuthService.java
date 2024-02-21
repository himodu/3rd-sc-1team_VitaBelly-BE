package GDSCKNU.VitaBelly.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthService {
    private FirebaseAuth firebaseAuth;
    private AccountRepository accountRepository;
    private Environment env;

    private RestTemplate restTemplate = new RestTemplate();

    public AuthService(AccountRepository accountRepository, FirebaseAuth firebaseAuth, Environment env) {
        this.firebaseAuth = firebaseAuth;
        this.accountRepository = accountRepository;
        this.env = env;
    }

    public String registerOrlogin(String code){
        String accessToken = getAccessToken(code, "google");
        JsonNode userResourceNode = getUserResource(accessToken, "google");
        UserRecord userRecord;
        String email = userResourceNode.get("email").asText();
        String name = userResourceNode.get("name").asText();

        try{
            userRecord = firebaseAuth.getUserByEmail(email);
            return login(userRecord.getEmail());
        }catch (FirebaseAuthException e){
            e.printStackTrace();
        }
        register(email,name);
        return login(email);
    }

    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }

    private JsonNode getUserResource(String accessToken, String registrationId) {
        String resourceUri = env.getProperty("oauth2."+registrationId+".resource-uri");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }


    public void register(String email, String name){

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setEmailVerified(false)
                .setDisplayName(name)
                .setPassword(env.getProperty("oauth2.google.client-secret"))
                .setDisabled(false);
        try{
            firebaseAuth.createUser(request);
            accountRepository.save(Account.builder()
                            .userEmail(email)
                            .username(name)
                            .password(env.getProperty("oauth2.google.client-secret"))
                    .build());

        } catch(FirebaseAuthException e){
            e.printStackTrace();
        }
    }

    public String login(String email){


        WebClient webClient = WebClient.create();


        String returnSecureToken = "true";


        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("email", email);
        formData.add("password", env.getProperty("oauth2.google.client-secret"));
        formData.add("returnSecureToken", returnSecureToken);


        String getUserInfo = webClient.post()
                .uri("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyD2O6emE4O01pGXHIp-X0B32haQFTQdqMk")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return getUserInfo;
    }
}
