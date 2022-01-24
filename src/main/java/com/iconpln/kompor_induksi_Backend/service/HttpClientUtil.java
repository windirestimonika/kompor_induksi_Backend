package com.iconpln.kompor_induksi_Backend.service;

import io.netty.handler.codec.http.HttpMethod;
import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.Param;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    public static Map<CharSequence, Iterable<Object>> defaultHeader() {
        Map<CharSequence, Iterable<Object>> headers = new HashMap<>();
        headers.put("Content-Type", Collections.singleton("application/json"));
        return headers;
    }

    public static Map<CharSequence, Iterable<Object>> authHeader(@Nonnull String token, @Nonnull String appSource) {
        Map<CharSequence, Iterable<Object>> headers = defaultHeader();
        headers.put("Authorization", Collections.singleton("Bearer " + token));
        headers.put("App-Source", Collections.singleton(appSource));
        return headers;
    }

    public static Map<CharSequence, Iterable<Object>> appSourceHeader(@Nonnull String appSource) {
        Map<CharSequence, Iterable<Object>> headers = defaultHeader();
        headers.put("App-Source", Collections.singleton(appSource));
        return headers;
    }

    public static Request postRequest(@Nonnull String url, @Nonnull String appSource, @Nullable String json) {
        return new RequestBuilder()
                .setMethod(HttpMethod.POST.name())
                .setUrl(url)
                .setHeaders(appSourceHeader(appSource))
                .setBody(StringUtils.isNotBlank(json) ? json : null)
                .build();
    }

    public static Request postRequestJwt(
            @Nonnull String url,
            @Nullable String json,
            @Nonnull String token,
            @Nonnull String appSource) {
        return new RequestBuilder()
                .setMethod(HttpMethod.POST.name())
                .setUrl(url)
                .setHeaders(authHeader(token, appSource))
                .setBody(json)
                .build();
    }

    public static Request getRequest(@Nonnull String url, @Nullable List<Param> params) {
        return new RequestBuilder()
                .setMethod(HttpMethod.GET.name())
                .setUrl(url)
                .addQueryParams(params)
                .setHeaders(defaultHeader())
                .build();
    }

    public static Request getRequestAppSource(@Nonnull String url, @Nullable List<Param> params, @Nonnull String appSource) {
        return new RequestBuilder()
                .setMethod(HttpMethod.GET.name())
                .setUrl(url)
                .addQueryParams(params)
                .setHeaders(appSourceHeader(appSource))
                .build();
    }

    public static boolean isValid(@Nonnull String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Request getRequestJwt(
            @Nonnull String url,
            @Nullable List<Param> params,
            @Nonnull String token,
            @Nonnull String appSource) {
        return new RequestBuilder()
                .setMethod(HttpMethod.GET.name())
                .setUrl(url)
                .setQueryParams(params)
                .setHeaders(authHeader(token, appSource))
                .build();
    }
}