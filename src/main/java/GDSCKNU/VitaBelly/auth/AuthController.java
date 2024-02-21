package GDSCKNU.VitaBelly.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @GetMapping("login")
    public ResponseEntity<String> firebaseLoginOrRegister(@RequestParam("code") String code){
        System.out.println("asdf");
        return new ResponseEntity<>(authService.registerOrlogin(code), HttpStatus.OK);
    }

}
