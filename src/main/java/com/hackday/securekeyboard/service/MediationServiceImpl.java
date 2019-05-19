package com.hackday.securekeyboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackday.securekeyboard.dto.ReqApprovalToCompDto;
import com.hackday.securekeyboard.dto.ReqRegisterToCompDto;
import com.hackday.securekeyboard.dto.ResApprovalResultDto;
import com.hackday.securekeyboard.dto.ResRegisterResultDto;
import com.hackday.securekeyboard.vo.CardCompanyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class MediationServiceImpl implements MediationService{
    private final RestTemplate restTemplate;

    public MediationServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public ResRegisterResultDto registerCard(CardCompanyInfo cardCompanyInfo, ReqRegisterToCompDto reqRegisterToCompDto) {

        String companyUrl = cardCompanyInfo.getUrl();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(companyUrl)
                .path("/api/register");

        HttpEntity<?> httpEntity = new HttpEntity<>(jsonMapper(reqRegisterToCompDto), setHttpHeader());
        return restTemplate.postForObject(uriComponentsBuilder.toUriString(), httpEntity, ResRegisterResultDto.class);
    }

    @Override
    public ResApprovalResultDto approvalPayment(CardCompanyInfo cardCompanyInfo, ReqApprovalToCompDto reqApprovalToCompDto) {
        String companyUrl = cardCompanyInfo.getUrl();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(companyUrl)
                .path("/approval/card");

        HttpEntity<?> httpEntity = new HttpEntity<>(jsonMapper(reqApprovalToCompDto), setHttpHeader());

        return restTemplate.postForObject(uriComponentsBuilder.toUriString(), httpEntity, ResApprovalResultDto.class);

    }


    private HttpHeaders setHttpHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

    private String jsonMapper(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        String dtoJson = null;

//        @TODO: Exception
        try {
            dtoJson = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return dtoJson;
    }
}
