package com.example.app.controller;

import java.util.regex.Pattern;

public interface Regex {

    Pattern FILTER = Pattern.compile("^[a-zA-zА-Яа-яё\\s]+$");
    Pattern PHONE = Pattern.compile("\\+?[\\d\\-]{7,15}");
    Pattern PASSPORT = Pattern.compile("[\\d]{8}");
    Pattern NUMBER = Pattern.compile("[\\d]{1,9}");
    Pattern RATE_NUMBER = Pattern.compile("[\\w]{1,45}");
    Pattern PRICE = Pattern.compile("[\\d]{1,15}");

}
