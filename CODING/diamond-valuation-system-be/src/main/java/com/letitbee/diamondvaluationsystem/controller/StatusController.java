package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.enums.RequestDetailStatus;
import com.letitbee.diamondvaluationsystem.enums.RequestStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/status")
public class StatusController {

    @GetMapping("request-status")
    public Map<String, String> getRequestStatus() {
        Map<String, String> requestStatus = new HashMap<>();
        for (RequestStatus status : RequestStatus.values()) {
            requestStatus.put(status.name(), status.name());
        }
        return requestStatus;
    }

    @GetMapping("request-detail-status")
    public Map<String, String> getRequestDetailStatus() {
        Map<String, String> requestDetailStatus = new HashMap<>();
        for (RequestDetailStatus status : RequestDetailStatus.values()) {
            requestDetailStatus.put(status.name(), status.name());
        }
        return requestDetailStatus;
    }
}
