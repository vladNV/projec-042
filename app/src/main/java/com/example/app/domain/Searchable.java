package com.example.app.domain;

/**
 * Annotate that field should be searchable on UI side
 */
public @interface Searchable {

    int minLength() default 2;

}
