package com.sumanta.MainGenie.Service;

import com.sumanta.MainGenie.Utils.PromptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtensionService {

    @Autowired
    private AiService aiService;

    public String generateCode(String problemStatement, String ideCode) {
        return aiService.aiApiCall(PromptUtil.generatePromptForCodeGeneration(problemStatement, ideCode));
    }
}
