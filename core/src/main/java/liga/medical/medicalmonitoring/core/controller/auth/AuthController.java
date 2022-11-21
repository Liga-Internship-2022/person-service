package liga.medical.medicalmonitoring.core.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liga.medical.medicalmonitoring.core.service.api.RoleService;
import liga.medical.medicalmonitoring.core.service.api.UserService;
import liga.medical.medicalmonitoring.dto.auth.UserRequest;
import liga.medical.medicalmonitoring.dto.auth.RoleDto;
import liga.medical.medicalmonitoring.dto.auth.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Api(value = "API для аутентификации и авторизации пользователей")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/")
    @ApiOperation(value = "Стартовая страница")
    public String greeting() {
        return "greeting";
    }

    @PostMapping("/registration")
    @ApiOperation(value = "Отправка запроса со страницы регистрации")
    public String addUser(@RequestBody UserRequest userRequest) {
        userService.save(userRequest);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    @ApiOperation(value = "Страница регистрации")
    public String registration() {
        return "registration";
    }

    @GetMapping("/main")
    @ApiOperation(value = "Главная страница")
    public String main(Map<String, Object> model) {
        List<UserDto> users = userService.getAll();
        model.put("users", users);
        return "main";
    }

    @GetMapping("/admin")
    @ApiOperation(value = "Страница админа")
    public String admin(Map<String, Object> model) {
        List<UserDto> users = userService.getAll();
        List<RoleDto> roles = roleService.getAll();
        model.put("users", users);
        model.put("roles", roles);
        return "admin";
    }
}
