package com.example.eventapp.Utils;

import com.google.firebase.auth.FirebaseUser;

public class Constants {

    public static final String FIREBASE_URL = "INCLUDE YOUR FIREBASE URL HERE";
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";

    public static FirebaseUser currentUser;


}