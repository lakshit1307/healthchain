package com.healthedge.healthchain.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailSender {


    @Value("${notif.endpoint.url}")
    private String notificationUrl;

    public boolean post(Map<String, Object> payload) {
        //TODO add code to mail
        //eventSource.send(topic1, createMailTemplate(), EntityConstants.Entity.CAMPAIGN_NOTIFICATION, EventSourceConstant.EventType.CREATE);
        //sender.send(payload);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(notificationUrl, payload, String.class);

        return true;
    }

    public void buildRequestEmail(String username, String password, String fromEmail, String toEmail, Long tenantId, String createdBy,
                                  String template,String tenantName,String subject) {
        Map<String, Object> output = new HashMap<>();
        output.put("notificationEvent", template);
        output.put("tenantID", 1);
        List<Map<String, Object>> op = new ArrayList<>();
        Map<String, Object> server = new HashMap<>();
        Map<String, Object> con = new HashMap<>();
        Map<String, Object> prop = new HashMap<>();


        prop.put("to", toEmail);
        prop.put("from", fromEmail);
        prop.put("subject", subject);
        server.put("type", "email");
        server.put("engine", "sendgrid");
        con.put("emailServerAccountSid", "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");
        //con.put("smsServerApiKey", "a6c8d5e90980b2038857f4be37e40eb7");
        server.put("connect", con);
        server.put("prop", prop);


        Map<String, Object> values = new HashMap<>();
        op.add(server);

        values.put("userName", username);
        values.put("tenantName", tenantName);
        values.put("tenantId", tenantId);
        values.put("pwd", password);
        values.put("url", "http://ng-ui-dev.magneticunicorns.com/login/" + tenantId);
        values.put("createdBy", createdBy);
        output.put("server", op);
        output.put("values", values);


        try {
            post(output);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }
    }

}
