package com.eklanfar.library.util;

public class Constants {

    public static final String ERROR_MISSING_REQUIRED_PARAMETER = "Missing required parameter";

    public static final String ERROR_USER_NOT_FOUND = "UserNotFound";
    public static final String ERROR_BOOK_NOT_FOUND = "BookNotFound";
    public static final String ERROR_BOOK_COPY_NOT_FOUND = "OneOfBookCopiesNotFound";
    public static final String ERROR_BOOK_COPY_ALREADY_LOANED = "OneOfBookCopiesAlreadyLoaned";
    public static final String ERROR_INTERNAL_SERVER_ERROR = "InternalServerError";

    // MRZ PATTERNS (dates are in format YYMMDD)
    // DOCUMENT_CODE/STATE/DOCUMENT_NUMBER/DOCUMENT_NUMBER_CHECK_DIGIT/OPTIONAL_DATA
    public static final String TD1_LINE1 = "([A|C|I][A-Z0-9<]{1})([A-Z]{3})([A-Z0-9<]{9})([0-9]{1})([A-Z0-9<]{15})";
    // DATE_OF_BIRTH/SEX/DATE_OF_EXPIRY/DATE_OF_EXPIRY_CHECK_DIGIT/NATIONALITY/OPTIONAL_DATA/OVERALL_CHECK_DIGIT
    public static final String TD1_LINE2 = "([0-9]{6})([0-9]{1})([M|F|X|<]{1})([0-9]{6})([0-9]{1})([A-Z]{3})([A-Z0-9<]{11})([0-9]{1})";

    private Constants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
