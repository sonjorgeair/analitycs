package com.alpha.predictor.collector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jorge1 on 07-04-17.
 */
@RestController
public class CollectorController
{
    @RequestMapping("/status")
    public String getStatus()
    {
        return "klkl";
    }
}
