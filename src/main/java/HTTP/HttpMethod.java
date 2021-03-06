package HTTP;


public enum HttpMethod {

    GET, HEAD, UNKNOWN;
    public static final int MAX_LENGTH;
    static{
        int maxLength = -1;
        for(HttpMethod method : values()){
            if(method.name().length() > maxLength){
                maxLength = method.name().length();
            }
        }
        MAX_LENGTH = maxLength;
    }
}