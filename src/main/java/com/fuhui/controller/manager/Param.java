package com.fuhui.controller.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Param {

    @Value("${fuhui.tokenTime}")
    private String tokenTime;

    @Value("${fuhui.url}")
    private String url;

    @Value("${fuhui.apiKey}")
    private String apiKey;

    @Value("${fuhui.request}")
    private String request;

    @Value("${fuhui.redirect}")
    private String redirect;

    @Value("${fuhui.oauthApi}")
    private String oauthApi;

    @Value("${fuhui.checkToken}")
    private String checkToken;

    @Value("${fuhui.ipApi}")
    private String getIp;

    @Value("${spring.activemq.broker-url}")
    private String activeMq;

    @Value("${fuhui.ipApi}")
    private String urlStrs;

    private String client_id;

    private String client_secret;

    public String getGetIp() {
        return getIp;
    }

    public String getCheckToken() {
        return checkToken;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getOauthApi() {
        return oauthApi;
    }

    public String getRequest() {
        return request;
    }

    public String getRedirect() {
        return redirect;
    }

    public String getApiURL() {
        return tokenTime;
    }

    public String getTokenTime() {
        return tokenTime;
    }

    public String getUrl() {
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setTokenTime(String tokenTime) {
        this.tokenTime = tokenTime;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public void setOauthApi(String oauthApi) {
        this.oauthApi = oauthApi;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getActiveMq() {
        return activeMq;
    }

    public String getUrlStrs() {
        return urlStrs;
    }

    @Override
    public String toString() {
        return "Param{" +
                "tokenTime='" + tokenTime + '\'' +
                ", url='" + url + '\'' +
                ", apiKey='" + apiKey + '\'' +
                '}';
    }
}
