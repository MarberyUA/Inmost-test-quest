package com.dev.testquest;

import com.dev.testquest.config.AppConfig;
import com.dev.testquest.model.Role;
import com.dev.testquest.model.Status;
import com.dev.testquest.model.Task;
import com.dev.testquest.model.User;
import com.dev.testquest.service.RoleService;
import com.dev.testquest.service.StatusService;
import com.dev.testquest.service.TaskService;
import com.dev.testquest.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new
                AnnotationConfigApplicationContext(AppConfig.class);
        RoleService roleService = applicationContext.getBean(RoleService.class);
        Role role = new Role();
        role.setRoleName(Role.RoleName.USER);
        roleService.add(role);
        User user = new User();
        user.setName("John");
        user.setEmail("marberymain@gmail.com");
        user.setPassword("1234");
        user.setSurname("Parker");
        UserService userService = applicationContext.getBean(UserService.class);
        userService.create(user);
        StatusService statusService = applicationContext.getBean(StatusService.class);
        Status status = new Status();
        status.setStatusName(Status.StatusName.In_Progress);
        statusService.create(status);
        TaskService taskService = applicationContext.getBean(TaskService.class);
        Task task = new Task();
        task.setDescription("Do anything what you want!");
        taskService.create(task, "marberymain@gmail.com");
    }
}
