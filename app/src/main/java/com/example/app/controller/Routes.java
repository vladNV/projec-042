package com.example.app.controller;

public interface Routes {
    // MAIN ROUTES
    String ROOT = "/";
    String LOGIN = "/login";
    String MANAGEMENT = "/management";
    String BUSINESS = "/business";

    // GET JSON
    String WHO_I_AM = "/whoAmI";

    // ANOTHER
    String REQUISITION_CREATION = "/business/creation";
    String AMOUNT_OF_BUSINESS_TRIPS = "/business/count";
    String EXPORT_TO_EXCEL = "/business/download";

}
