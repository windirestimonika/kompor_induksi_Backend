package com.iconpln.kompor_induksi_Backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

public interface AuthService {
    BaseResponse<?> validate(@NotNull String token, String param) throws ExecutionException, InterruptedException, JsonProcessingException;
    BaseResponse<?> sso(String param) throws ExecutionException, InterruptedException, JsonProcessingException;
    BaseResponse<?> loginWithCode(@NotNull String code, String param) throws ExecutionException, InterruptedException, JsonProcessingException;
}