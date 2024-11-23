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

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/projects")
public class ProjectController {

    ProjectService projectService;
    UserService userService;
    UserProjectService userProjectService;


    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "/projects/list-projects";
    }

    /**
     * Вывод списка всех прикрепленных к проекту пользователей.
     */
    @GetMapping("/users/{id}")
    public String showUsers(Model model, @PathVariable(name = "id") Long projectId) {
        List<User> users = userProjectService.getUsersByProjectId(projectId);
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("users", users);
        return "/projects/list-project-users";
    }

    /**
     * Вызывает форму для создания нового проекта.
     */
    @GetMapping("/add")
    public String doCreateProjectForm(Model model, Project project) {
        model.addAttribute("project", project);
        return "/projects/add-project";
    }

    /**
     * Добавляет новый проект в БД, присваивая ему дату создания.
     */
    @PostMapping
    public String createNewProject(@ModelAttribute Project project) {
        project.setCreatedDate(LocalDate.now());
        projectService.addProject(project);
        return "redirect:/projects";
    }

    /**
     * Удаляет проект из БД.
     */
    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable(value = "id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }

    /**
     * Открепляет пользователя от проекта.
     */
    @DeleteMapping("/users/{projectId}/{userId}")
    public String deleteUserFromProject(@PathVariable Long projectId, @PathVariable Long userId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return "redirect:/projects/users/{projectId}";
    }

    /**
     * Запускает форму прикрепления пользователя к проекту.
     */
    @GetMapping("users/add/{id}")
    public String doAddUsersForm(Model model, @PathVariable(name = "id") Long projectId) {
        model.addAttribute("project", projectService.getProjectById(projectId));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("disabledItems", userProjectService.getUsersByProjectId(projectId));
        return "/projects/add-users-to-project";
    }

    /**
     * Прикрепляет пользователя к проекту.
     */
    @PostMapping("/users/add/{projectId}/{userId}")
    public String addUserToProject(@PathVariable Long projectId, @PathVariable Long userId) {
        userProjectService.addUserToProject(userId, projectId);
        return "redirect:/projects/users/add/{projectId}";

    }

}
