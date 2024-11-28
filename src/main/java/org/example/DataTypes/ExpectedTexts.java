package org.example.DataTypes;

public enum ExpectedTexts {
    HEADER_LOGIN("Login page"),
    HEADER_BOOKING("Book ticket"),
    HEADER_CHANGE_PASSWORD("Change password"),
    HEADER_MY_TICKET("Manage ticket"),
    HEADER_CHANGE_PASSWORD_FORM("Password Change Form"),
    CHANGE_PASSWORD_ERROR_PASSWORD("Could not reset password. Please correct the errors and try again."),
    CHANGE_PASSWORD_ERROR_CONFIRM("The password confirmation did not match the new password."),
    REGISTER_SUCCESS("Thank you for registering your account"),
    LOGIN_ERROT_NOT_ACTIVATED("Invalid username or password. Please try again."),
    LOGIN_ERROR("There was a problem with your login and/or errors exist in your form."),
    LOGIN_ERROR_ATTEMPT("You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes."),
    LOGIN_ERROR_TIMEOUT("You have used all 5 login attempts. Please try again in 15 minutes."),
    FORM_ERROR("There're errors in the form. Please correct the errors and try again."),
    PASSWORD_LENGTH_ERROR("Invalid password length."),
    PID_LENGTH_ERROR("Invalid ID length."),
    CHANGE_PASSWORD_SUCCESS("Your password has been updated!"),
    TICKET_BOOK_SUCCESS("Ticket booked successfully!");

    private final String text;

    ExpectedTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}