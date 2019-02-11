package co.nilin.mvc.service;

import co.nilin.mvc.data.entity.Registration;

import java.util.Date;
import java.util.List;

public interface IRegistrationService {

    public Registration addRegistration(Long userId, String number, String message, Date date,Boolean accepted);

    public Registration findRegById(Long regId);

    public Registration findUsersLastReg(Long userId);

    public Registration update(Registration registration);

}
