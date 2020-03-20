package com.the.mild.project.server.resources;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class UserAuth {

//    private String CLIENT_ID;
//    private JsonFactory jsonFactory;
//    private HttpTransport transport;

//    public UserAuth(String clientId) {
//        CLIENT_ID = clientId;
//        jsonFactory = new JacksonFactory();
//        transport = new NetHttpTransport();
//    }

    public static boolean checkAuth(String clientId, String idTokenString) throws IOException, GeneralSecurityException {
        JsonFactory jsonFactory = new JacksonFactory();
        HttpTransport transport = new NetHttpTransport();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(clientId))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        GoogleIdToken idToken;

        try {
            idToken = verifier.verify(idTokenString);
        } catch (IllegalArgumentException iae) {
            return false;
        }

        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            return true;

            // Use or store profile information
            // ...

        } else {
            return false;
        }
    }
}