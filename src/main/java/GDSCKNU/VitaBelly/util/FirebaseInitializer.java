package GDSCKNU.VitaBelly.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {
    @Bean
    public FirebaseApp firebaseApp() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream("./firebase.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);
        return app;
    }

    @Bean
    public FirebaseAuth getFirebaseAuth(){
        try {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp());
            return firebaseAuth;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}