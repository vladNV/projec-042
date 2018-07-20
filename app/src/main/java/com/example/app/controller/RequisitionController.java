package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequisitionController {

    @GetMapping(Routes.MANAGEMENT)
    public String getManagementPage() {
        return UI.MANAGEMENT;
    }

}
