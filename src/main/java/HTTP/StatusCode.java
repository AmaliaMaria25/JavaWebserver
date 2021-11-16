package HTTP;

public enum StatusCode {

    STATUS_200_OK(200, "OK"),

    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_404_FILE_NOT_FOUND(404, "File Not Found"),
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED(401, "Method Not Allowed"),
    CLIENT_ERROR_414_URI_TOO_LONG(414, "URI Too Long"),

    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501, "Not Implemented"),
    SERVER_ERROR_505_VERSION_NOT_SUPPORTED(505, "Version Not Supported");

    public final int STATUS_CODE;
    public final String MESSAGE;

    StatusCode(int STATUS_CODE, String MESSAGE){
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }

}