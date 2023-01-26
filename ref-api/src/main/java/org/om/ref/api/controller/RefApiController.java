package org.om.ref.api.controller;

import org.om.ref.api.model.AsxCompany;
import org.om.ref.api.service.RefApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

@Controller
@ResponseBody
@RequestMapping("/api/asx")
public class RefApiController {

    @Autowired
    private RefApiService refApiService;

    @GetMapping("/companies")
    public Flux<AsxCompany> getCompanies() {
        return refApiService.readCsv();
    }
}
