package com.sumanta.MainGenie.Controller;

import com.sumanta.MainGenie.Model.ReqBody;
import com.sumanta.MainGenie.Service.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new-genie/extension")
public class ExtensionController {

    @Autowired
    ExtensionService extensionService;

    @PostMapping("/generate-code")
    public ResponseEntity<String> generateCode(@RequestBody ReqBody reqBody) {
        if(extensionService.generateCode(reqBody.getProblemStatement(), reqBody.getIdeCode()).equals("error")) {
            return ResponseEntity.badRequest().body("Error in generating code");
        } else {
            return ResponseEntity.ok(extensionService.generateCode(reqBody.getProblemStatement(), reqBody.getIdeCode()));
        }
    }
}
