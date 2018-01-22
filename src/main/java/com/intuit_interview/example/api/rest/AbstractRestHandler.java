package com.intuit_interview.example.api.rest;

import com.intuit_interview.example.domain.RestErrorInfo;
import com.intuit_interview.example.domain.Task;
import com.intuit_interview.example.domain.User;
import com.intuit_interview.example.exception.DataFormatException;
import com.intuit_interview.example.exception.PhoneNumberNotValidException;
import com.intuit_interview.example.exception.ResourceNotFoundException;
import com.intuit_interview.example.exception.UserEmailNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is meant to be extended by all REST resource "controllers".
 * It contains exception mapping and other common REST API functionality
 */
//@ControllerAdvice?
public abstract class AbstractRestHandler implements ApplicationEventPublisherAware {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;

    protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";

    // source: https://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // YYYY-MM-DD_HH:MM format for time
    private static final String DATE_FORMATTER =
            "^([1-9][0-9][0-9][0-9])\\-(0[0-9]|1[0-2])\\-(0[0-9]|1[0-9]|2[0-9]|3[0-1])_([0-9]|0[0-9]|1[0-9]|2[0-3])\\:([0-5][0-9])$";

    // Phone number formatter (999)-999-9999
    private static final String PHONE_NUMBER =
            "^(\\([1-9][0-9][0-9]\\))\\-([0-9][0-9][0-9])\\-([0-9][0-9][0-9][0-9])$";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public
    @ResponseBody
    RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());

        return new RestErrorInfo(ex, "You messed up.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        log.info("ResourceNotFoundException handler:" + ex.getMessage());

        return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    //todo: replace with exception mapping
    public static <T> T checkResourceFound(final T resource) {
        if (resource == null)
            throw new ResourceNotFoundException("resource not found");

        if (resource instanceof User)
            isValidUser((User)resource);

        if (resource instanceof Task)
            isValidTask((Task)resource);

        return resource;
    }


    private static boolean isValidUser(User user) {

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(user.getEmailId());

        if (!(matcher.matches()))
            throw new UserEmailNotValidException();


        pattern = Pattern.compile(PHONE_NUMBER);
        matcher = pattern.matcher(user.getPhone());

        if (!(matcher.matches()))
            throw new PhoneNumberNotValidException();

        return true;
    }

    private static boolean isValidTask(Task task) {
        return true;
    }
}