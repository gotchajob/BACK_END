package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.User;
import com.example.gj.model.Verify;
import com.example.gj.repository.UserRepository;
import com.example.gj.repository.VerifyRepository;
import com.example.gj.util.Util;
import com.example.gj.validator.EmailValidator;
import com.example.gj.validator.PasswordValidator;
import com.example.gj.viewmodel.user.CreateForgetPasswordResponse;
import com.example.gj.viewmodel.user.VerifyForgetPasswordRequest;
import com.example.gj.viewmodel.user.VerifyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class VerifyService {
    private final int EMAIL_TYPE = 1;
    private final int FORGET_PASSWORD_TYPE = 2;
    private final int CODE_LENGTH = 4;
    private final int VERIFIED_STATUS = 1;
    private final int DELETE_VERIFY_STATUS = 0;
    private final int TIME_EXPIRE = 1000*60*30;
    private final int TIME_AWAIT = 1000*60*1;


    @Autowired
    VerifyRepository verifyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final UserService userService;

    public VerifyService(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }


    public boolean createEmailVerify(String email) throws Exception {
        User user = checkUser(email);
        if (user.getStatus() == VERIFIED_STATUS) {
            throw new Exception(Message.VERIFIED);
        }

        String code = Util.generateRandomNumberString(CODE_LENGTH);

        boolean isSuccess = createVerify(user.getId(), code, EMAIL_TYPE);
        if (!isSuccess) {
            throw new Exception(Message.CREATE_VERIFY_FAIL);
        }

        String fullName = user.getFirstName() + " " + user.getLastName();
        emailService.sendEmailVerifyEmail(email, code, fullName);

        return true;
    }

    public VerifyResponse verifyEmail(String email, String code) throws Exception {
        User user = checkUser(email);
        if (user.getStatus() == VERIFIED_STATUS) {
            throw new Exception(Message.VERIFIED);
        }

        checkCode(code);

        boolean isSuccess = verifyCode(user.getId(), code, EMAIL_TYPE);
        if (!isSuccess) {
            throw new Exception(Message.VERIFY_FAIL);
        }

        user.setStatus(VERIFIED_STATUS);
        userRepository.save(user);

        return new VerifyResponse(user.getFirstName() + " " + user.getLastName());
    }

    public void verifyPassword(VerifyForgetPasswordRequest request) throws Exception {
        User user = checkUser(request.getEmail());
        checkCode(request.getCode());

        if (!PasswordValidator.isValidPassword(request.getPassword())) {
            throw new Exception(Message.INVALID_PASSWORD);
        }

        boolean isSuccess = verifyCode(user.getId(), request.getCode(), FORGET_PASSWORD_TYPE);
        if (!isSuccess) {
            throw new Exception(Message.VERIFY_FAIL);
        }

        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public CreateForgetPasswordResponse createForgetPassword(String email) throws Exception {
        User user = checkUser(email);

        String code = Util.generateRandomNumberString(CODE_LENGTH);
        boolean isSuccess = createVerify(user.getId(), code, FORGET_PASSWORD_TYPE);
        if (!isSuccess) {
            throw new Exception(Message.CREATE_VERIFY_FAIL);
        }

        String fullName = user.getFirstName() + " " + user.getLastName();
        emailService.sendEmailResetPassword(email, code, fullName);

        return new CreateForgetPasswordResponse(code);
    }

    public boolean checkCodeForgetPassword(String email, String code) throws Exception {
        User user = checkUser(email);
        checkCode(code);

        Verify verify = verifyRepository.findByUserIdAndCodeAndType(user.getId(), code, FORGET_PASSWORD_TYPE);
        if (verify == null) {
            throw new Exception(Message.VERIFY_NOT_FOUND);
        }

        return true;
    }


    private boolean createVerify(String userId, String code, int type) throws Exception {
        Verify verify = verifyRepository.findByUserIdAndType(userId, type);

        Date expire = new Date(System.currentTimeMillis() + TIME_EXPIRE);

        if (verify == null) {
            Verify _verify = new Verify();

            _verify.setId(UUID.randomUUID().toString());
            _verify.setUserId(userId);
            _verify.setCode(code);
            _verify.setExpireDate(expire);
            _verify.setStatus(1);
            _verify.setCreatedAt(new Date());
            _verify.setType(type);

            verifyRepository.save(_verify);
        } else {
            if (verify.getExpireDate().after(new Date(System.currentTimeMillis() + TIME_EXPIRE - TIME_AWAIT))) {
                throw new Exception(Message.WAIT);
            }
            verify.setCode(code);
            verify.setStatus(1);
            verify.setExpireDate(expire);
            verify.setUpdatedAt(new Date());

            verifyRepository.save(verify);
        }

        return true;
    }

    private boolean verifyCode(String userId, String code, int type) throws Exception {
        Verify verify = verifyRepository.findByUserIdAndCodeAndType(userId, code, type);

        if (verify == null || verify.getStatus() == DELETE_VERIFY_STATUS) {
            throw new Exception(Message.INVALID_CODE);
        }

        if (verify.getExpireDate().before(new Date())) {
            throw new Exception(Message.EXPIRE_CODE);
        }

        verify.setStatus(DELETE_VERIFY_STATUS);
        verify.setUpdatedAt(new Date());
        verifyRepository.save(verify);

        return true;
    }

    private void checkCode(String code) throws Exception {
        if (code == null) {
            throw new Exception(Message.NULL_INPUT);
        }

        if (code.length() != CODE_LENGTH) {
            throw new Exception(Message.CODE_LENGTH);
        }
    }

    private User checkUser(String email) throws Exception {
        if (email == null) {
            throw new Exception(Message.NULL_INPUT);
        }

        if (!EmailValidator.isValidEmail(email)) {
            throw new Exception(Message.INVALID_EMAIL);
        }

        User user = userRepository.findByEmail(email);

        if (user == null || user.getStatus() == 0) {
            throw new Exception(Message.USER_NOT_FOUND);
        }

        return user;
    }

}
