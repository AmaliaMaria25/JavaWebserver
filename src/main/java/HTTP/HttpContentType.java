package HTTP;

public enum HttpContentType {
    CSS("CSS"),
    GIF("GIF"),
    HTML("HTML"),
    JPG("JPG"),
    JPEG("JPEG"),
    PNG("PNG"),
    TXT("TXT"),
    XML("XML");

    private final String fileType;

    HttpContentType(String fileType){
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        switch (this) {
            case CSS:
                return "Content-Type: text/css";
            case GIF:
                return "Content-Type: image/gif";
            case HTML:
                return "Content-Type: text/html; charset=UTF-8";
            case JPG:
            case JPEG:
                return "Content-Type: image/jpeg";
            case PNG:
                return "Content-Type: image/png";
            case TXT:
                return "Content-type: text/plain; charset=UTF-8";
            case XML:
                return "Content-type: text/xml; charset=UTF-8";
            default:
                return null;
        }
    }
}
