package org.myexam.commons.validators;

public interface PasswordValidator {

    default boolean alphaCheck(String password, boolean caseIncentive){

        return false;
    }
}
