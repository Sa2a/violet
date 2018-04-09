package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.User;

import jonamatoka.violet.data.repo.UserRepository;
import net.openhft.hashing.LongHashFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Lib.Mappings.API_V1_USER)
public class AuthenticityServices {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Boolean> register(@RequestParam("username") String username,
                                            @RequestParam("email") String email,
                                            @RequestParam("pass") String pass) {

        User user = new User().setUsername(username).setEmail(email);
        userRepository.save(user.setHash(LongHashFunction.xx().hashChars(pass)).setPriviliges(6));

        return new ResponseEntity<>(true,  HttpStatus.OK);

    }

}
