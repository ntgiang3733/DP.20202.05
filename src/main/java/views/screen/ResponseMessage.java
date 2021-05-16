package views.screen;

// cleancode: Tao doi tuong response de khong phai truyen qua hashmap
public class ResponseMessage {
    private String result;
    private String message;

    public ResponseMessage(String result, String message) {
        this.message = message;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
