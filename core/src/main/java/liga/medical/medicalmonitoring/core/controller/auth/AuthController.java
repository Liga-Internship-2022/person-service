package liga.medical.medicalmonitoring.core.controller.auth;

import liga.medical.medicalmonitoring.core.service.api.RoleService;
import liga.medical.medicalmonitoring.core.service.api.UserService;
import liga.medical.medicalmonitoring.dto.UserRequest;
import liga.medical.medicalmonitoring.dto.auth.RoleDto;
import liga.medical.medicalmonitoring.dto.auth.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @PostMapping("/registration")
    public String addUser(@RequestBody UserRequest userRequest) {
        userService.save(userRequest);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        List<UserDto> users = userService.getAll();
        model.put("users", users);
        return "main";
    }

    @GetMapping("/admin")
    public String admin(Map<String, Object> model) {
        List<UserDto> users = userService.getAll();
        List<RoleDto> roles = roleService.getAll();
        model.put("users", users);
        model.put("roles", roles);
        return "admin";
    }
}
