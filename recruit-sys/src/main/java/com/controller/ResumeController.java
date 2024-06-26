package com.controller;

import com.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResumeController {

    @Autowired
    private OpenAiService openAiService;

    @PostMapping("/generateResumeSuggestions")
    public String generateResumeSuggestions(@RequestBody String resumeData) {
        return openAiService.generateResumeSuggestions(resumeData);
    }
}