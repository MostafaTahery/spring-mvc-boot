package co.nilin.mvc.async;

import co.nilin.mvc.data.dto.RegisterDto;
import co.nilin.mvc.data.entity.Registration;
import co.nilin.mvc.data.entity.User;
import co.nilin.mvc.repository.RegistrationRepository;
import co.nilin.mvc.repository.UserRepository;
import co.nilin.mvc.service.RegistrationService;
import co.nilin.mvc.service.UserService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping(value = "/async")
@ComponentScan
public class AsyncRestController {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserService userService;
    @Autowired
    private Queue queue;

    @Bean
    public Queue hello() {
        return new Queue("hello", true);
    }

    @Bean
    public AsyncReceiver asyncReceiver() {
        return new AsyncReceiver();
    }

    @RequestMapping(value = "/register", params = {"receptor","userName"}, method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam("receptor") String receptor,@RequestParam("userName") String userName) {

        Registration registration=registrationService.addRegistration(userService.findIdByUserName(userName),receptor,String.valueOf((Math.round(Math.random() * 10000))),new Date(),false);

        //sending to rabbitMQ
        template.convertAndSend(queue.getName(), registration);
        return registration.getMessage();
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST, params = {"vcode","userName"})
    public String verify(@RequestParam("vcode") String verificationCode,@RequestParam("userName") String userName) {

        //preparing a Registration Object;
        Registration registration=registrationService.findUsersLastReg(userService.findIdByUserName(userName));

        //verification of sended code
        String status;
        if (registration.getMessage().equals(verificationCode)){
            registration.setActive(true);
            registrationService.update(registration);
            status = "Verified";
        }
        else status = "Not Verified";
        return status;
    }
}
