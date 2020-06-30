package com.dev.testquest;

import com.dev.testquest.config.AppConfig;
import com.dev.testquest.model.Role;
import com.dev.testquest.model.Status;
import com.dev.testquest.model.Task;
import com.dev.testquest.model.User;
import com.dev.testquest.service.AuthenticationService;
import com.dev.testquest.service.RoleService;
import com.dev.testquest.service.StatusService;
import com.dev.testquest.service.TaskService;
import com.dev.testquest.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new
                AnnotationConfigApplicationContext(AppConfig.class);
        RoleService roleService = applicationContext.getBean(RoleService.class);
        Role role = new Role();
        role.setRoleName(Role.RoleName.USER);
        roleService.add(role);
        AuthenticationService authenticationService = applicationContext.getBean(AuthenticationService.class);
        authenticationService.registration("John", "marberymain@gmail.com", "1234");
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
