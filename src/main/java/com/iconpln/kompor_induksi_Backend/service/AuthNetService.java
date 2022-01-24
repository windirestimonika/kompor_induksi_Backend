package com.iconpln.kompor_induksi_Backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconpln.kompor_induksi_Backend.service.serviceImpl.BaseResponse;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutionException;

public interface AuthNetService {
    BaseResponse<?> validate(@Nonnull String token, String param) throws ExecutionException, InterruptedException, JsonProcessingException;
    BaseResponse<?> sso(String param) throws ExecutionException, InterruptedException, JsonProcessingException;
    BaseResponse<?> loginWithCode(@Nonnull String code, String param) throws ExecutionException, InterruptedException, JsonProcessingException;
}
