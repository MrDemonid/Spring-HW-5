package mr.demonid.spring.hw5.controllers;

import lombok.AllArgsConstructor;
import mr.demonid.spring.hw5.domain.Project;
import mr.demonid.spring.hw5.domain.User;
import mr.demonid.spring.hw5.services.ProjectService;
import mr.demonid.spring.hw5.services.UserProjectService;
import mr.demonid.spring.hw5.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;
    ProjectService projectService;
    UserProjectService userProjectService;

    /**
     * Отображает список всех пользователей.
     */
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/users/list-users";
    }

    /**
     * Запускает форму для добавления нового пользователя.
     */
    @GetMapping("/add")
    public String doCreateUserForm(User user) {
        return "/users/add-user";
    }

    /**
     * Показывает список всех проектов пользователя.
     */
    @GetMapping("/projects/{id}")
    public String showAllProjects(Model model, @PathVariable(value = "id") Long userId) {
        List<Project> p = userProjectService.getProjectsByUserId(userId);
        User u = userService.getUserById(userId);
        model.addAttribute("projects", p);
        model.addAttribute("user", u);
        return "/users/list-user-projects";
    }

    /**
     * Удаляет пользователя из проекта.
     */
    @DeleteMapping("/projects/{userId}/{projectId}")
    public String removeFromProject(@PathVariable Long userId, @PathVariable Long projectId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return "redirect:/users/projects/{userId}";
    }

    /**
     * Запускает форму для добавления пользователю нового проекта.
     */
    @GetMapping("/projects/add/{id}")
    public String doAddProjectForm(Model model, @PathVariable(name = "id") Long userId) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("disabledItems", userProjectService.getProjectsByUserId(userId));
        return "/users/add-user-to-project";
    }

    /**
     * Добавляет пользователю новый проект.
     */
    @PostMapping("/projects/add/{userId}/{projectId}")
    public String addProjectToUser(@PathVariable Long userId,@PathVariable Long projectId) {
        userProjectService.addUserToProject(userId, projectId);
        return "redirect:/users/projects/add/{userId}";
    }

    /**
     * Добавляет нового пользователя в БД.
     */
    @PostMapping
    public String addNewUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    /**
     * Удаляет пользователя из БД.
     */
    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
