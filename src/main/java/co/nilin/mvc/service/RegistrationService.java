package co.nilin.mvc.service;

import co.nilin.mvc.data.entity.Registration;
import co.nilin.mvc.data.entity.User;
import co.nilin.mvc.repository.RegistrationRepository;
import co.nilin.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
public class RegistrationService implements IRegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Registration addRegistration(Long userId, String number, String message,Date date,Boolean accepted) {

        User user = userRepository.findById(userId).get();
        Registration registration = new Registration();
        registration.setUser(user);
        registration.setDate(date);
        registration.setActive(accepted);
        registration.setReceptor(number);
        registration.setMessage(message);
        registrationRepository.save(registration);
        return registration;

    }

    @Override
    public Registration findRegById(Long regId) {

        Registration registration = new Registration();
        for (Registration reg : registrationRepository.findAll()) {
            if (reg.getRegId().equals(regId)) registration = reg;
        }
        return registration;

    }

    @Override
    public Registration findUsersLastReg(Long userId) {

        User user=userRepository.findById(userId).get();
        List<Registration> registrations=new ArrayList<>();
        for (Registration reg:registrationRepository.findAll()) {
            if (reg.getUser().equals(user))registrations.add(reg);
        }
        registrations.sort((a,b)->b.getDate().compareTo(a.getDate()));
        return registrations.get(0);
    }

    @Override
    public Registration update(Registration registration){
        registrationRepository.save(registration);
        return registration;
    }
}
