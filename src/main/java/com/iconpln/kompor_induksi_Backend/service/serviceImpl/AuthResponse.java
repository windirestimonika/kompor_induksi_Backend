package com.iconpln.kompor_induksi_Backend.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.model.JwtDto;
import com.iconpln.kompor_induksi_Backend.model.UserData;
import com.iconpln.kompor_induksi_Backend.service.AuthService;
import com.iconpln.kompor_induksi_Backend.service.HttpClientUtil;
import com.iconpln.kompor_induksi_Backend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class AuthResponse implements AuthService {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    AsyncHttpClient httpClient;
    @Autowired
    UserService userService;

    @Value("${avenger.baseUri.auth}")
    private String authBaseUri;
    @Value("${avenger.app-source}")
    private String appSource;

    @Override
    public BaseResponse<?> validate(@Nonnull String token, String param) throws ExecutionException, InterruptedException, JsonProcessingException {
        String validationUri = authBaseUri + "/validate";
        String localAppSource = appSource;
        if(param.equalsIgnoreCase("local")){
            localAppSource = "AVENGER_WEB_TEST";
        }
        Request request = HttpClientUtil.postRequestJwt(validationUri, null, token, localAppSource);
        Response response = httpClient.executeRequest(request).toCompletableFuture().get();
        if (response.getStatusCode() == 200) {
            TypeReference<BaseResponse<UserData>> type = new TypeReference<BaseResponse<UserData>>() {
            };
            return mapper.readValue(response.getResponseBody(), type);
        } else {
            String message = StringUtils.isNotBlank(response.getResponseBody()) ? response.getResponseBody() : response.getStatusText();
            return BaseResponse.builder()
                    .status(response.getStatusCode())
                    .success(false)
                    .message(StringUtils.isNotBlank(message) ? message : "Unknown Error")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> sso(String param) throws ExecutionException, InterruptedException, JsonProcessingException {
        String ssoUri = authBaseUri + "/sso";
        String localAppSource = appSource;
        if(param.equalsIgnoreCase("local")){
            localAppSource = "AVENGER_WEB_TEST";
        }
        Request request = HttpClientUtil.getRequestAppSource(ssoUri, null, localAppSource);
        Response response = httpClient.executeRequest(request).toCompletableFuture().get();
        if (response.getStatusCode() == 200) {
            String responseBody = response.getResponseBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonObj = objectMapper.readTree(responseBody);
            return BaseResponse.builder()
                    .status(response.getStatusCode())
                    .success(true)
                    .data(jsonObj.get("data").get("uri").textValue())
                    .build();
        } else {
            String message = StringUtils.isNotBlank(response.getResponseBody()) ? response.getResponseBody() : response.getStatusText();
            return BaseResponse.builder()
                    .status(response.getStatusCode())
                    .success(false)
                    .message(StringUtils.isNotBlank(message) ? message : "Unknown Error")
                    .build();
        }
    }

    @Override
    public BaseResponse<?> loginWithCode(@Nonnull String code, String param) throws ExecutionException, InterruptedException, JsonProcessingException {
        String exchangeUri = authBaseUri+"/exchange";
        String localAppSource = appSource;
        if(param.equalsIgnoreCase("local")){
            localAppSource = "AVENGER_WEB_TEST";
        }
        String codeJson = "{\"code\":\""+code+"\"}";
        Request request = HttpClientUtil.postRequest(exchangeUri, localAppSource, codeJson);
        Response response = httpClient.executeRequest(request).toCompletableFuture().get();
        String responseBody = response.getResponseBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonObj = objectMapper.readTree(responseBody);
        if (jsonObj.get("status").intValue() == 200) {
            String token = jsonObj.get("data").get("token").textValue();
            BaseResponse<?> wrapper = null;
            try{
                wrapper = validate(token, localAppSource);
            } catch (Exception e){
                e.printStackTrace();
                wrapper = BaseResponse.error(e.getMessage());
                return wrapper;
            }
            if(wrapper.getStatus()==200){
                UserData userData = (UserData) wrapper.getData();
                String username = userData.getUsername();
                Optional<User> optional =  userService.findByUsername(username);
                if(optional.isPresent()){
                    JwtDto jwtDto = userService.generateJwtDto(username);
                    jwtDto.setLogoutUrl(jsonObj.get("data").get("logoutUrl").textValue());
                    return BaseResponse.builder()
                            .status(response.getStatusCode())
                            .success(true)
                            .data(jwtDto)
                            .build();
                }
                Request logoutRequest = HttpClientUtil.getRequest(jsonObj.get("data").get("logoutUrl").textValue(), null);
                httpClient.executeRequest(logoutRequest);
                return BaseResponse.builder()
                        .status(404)
                        .success(false)
                        .message("UserRepo not found on Avenger")
                        .build();
            }
        }
        String message = StringUtils.isNotBlank(response.getResponseBody()) ? response.getResponseBody() : response.getStatusText();
        return BaseResponse.builder()
                .status(response.getStatusCode())
                .success(false)
                .message(StringUtils.isNotBlank(message) ? message : "Code Invalid")
                .build();
    }
}