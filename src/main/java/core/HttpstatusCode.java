package core;

public enum HttpstatusCode {
    Success(200, "The request succeeded"),
    Created(201, "A new resource was created"),
    BadRequest(400, "Missing required field: Name"),
    Unauthorized(401, "Invalid access token"),
    NotFound(404, "Cannot find requested resource"),
    NoContent(204, "No content to send in the response body");

    public final int code;
    public final String message;

    HttpstatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

