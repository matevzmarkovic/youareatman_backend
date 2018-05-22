package net.youareatman.rest.services;

import net.youareatman.enums.ErrorTypesEnum;
import net.youareatman.exceptions.GenericYouAreAtmanException;
import net.youareatman.model.AtmanUser;
import net.youareatman.model.forms.ChangeDateForm;
import net.youareatman.model.forms.ChangePasswordForm;
import net.youareatman.rest.repositories.AtmanUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtmanUserService {

    @Autowired
    private AtmanUserRepository atmanUserRepository;

    private static Logger logger = LogManager.getLogger(AtmanUserService.class);

    public List<AtmanUser> listUsers(){
        List<AtmanUser> users = new ArrayList<AtmanUser>();
        atmanUserRepository.findAll().forEach(users::add);

        return users;
    }

    public AtmanUser listUser(String userEmail) throws GenericYouAreAtmanException {
        AtmanUser userEntry = atmanUserRepository.findById(userEmail).orElseThrow(() -> new GenericYouAreAtmanException("Error while reading user from database", ErrorTypesEnum.CRUDError));
        return userEntry;
    }

    public AtmanUser changePassword(String userEmail, ChangePasswordForm changePasswordForm) throws GenericYouAreAtmanException {
        AtmanUser userEntry = atmanUserRepository.findById(userEmail).orElseThrow(() -> new GenericYouAreAtmanException("Error while reading user from database", ErrorTypesEnum.CRUDError));
        userEntry.setPasswordHash(changePasswordForm.getPassword());
        atmanUserRepository.save(userEntry);

        return userEntry;
    }

    public AtmanUser changeUserJoinDate(String userEmail, ChangeDateForm changeDateForm) throws GenericYouAreAtmanException{
        AtmanUser userEntry = atmanUserRepository.findById(userEmail).orElseThrow(() -> new GenericYouAreAtmanException("Error while reading user from database", ErrorTypesEnum.CRUDError));
        userEntry.setJoinDate(changeDateForm.getJoinDate());
        atmanUserRepository.save(userEntry);

        return userEntry;
    }

    public AtmanUser createUser(AtmanUser user){
        atmanUserRepository.save(user);
        return user;
    }

    public AtmanUser deleteUser(String userEmail) throws GenericYouAreAtmanException{
        AtmanUser user = atmanUserRepository.findById(userEmail).orElseThrow(() -> new GenericYouAreAtmanException("Error while reading user from database", ErrorTypesEnum.CRUDError));
        atmanUserRepository.deleteById(userEmail);
        return user;
    }

}