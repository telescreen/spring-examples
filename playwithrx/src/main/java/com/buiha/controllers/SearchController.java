package com.buiha.controllers;

import com.buiha.models.QueryMessage;
import com.buiha.models.QueryResult;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public QueryResult greeting(QueryMessage message) {
        System.out.println(message);
        return new QueryResult("Got: " + message);
    }
}
