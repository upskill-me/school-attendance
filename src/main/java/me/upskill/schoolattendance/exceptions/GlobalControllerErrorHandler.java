package me.upskill.schoolattendance.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import me.upskill.schoolattendance.api.ApiError;
import me.upskill.schoolattendance.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static me.upskill.schoolattendance.api.ErrorCodes.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalControllerErrorHandler {

    @Autowired
    private AppProperties properties;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleThrowable(HttpServletRequest request, Throwable th) {
        ApiError apiError = ApiError.builder()
                .code(INTERNAL_SERVER_ERROR)
                .message(th.getMessage())
                .serviceName(properties.getServiceName())
                .serviceVersion(properties.getServiceVersion())
                .build();

        return ResponseEntity.status(500).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest request, Exception ex) {
        return handleThrowable(request, ex);
    }

    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> handleMyException(HttpServletRequest request, MyException myEx) {
        return handleException(request, myEx);
    }

    @ExceptionHandler(StatusCodeMyException.class)
    public ResponseEntity<?> handleStatusCodeMyException(HttpServletRequest request, StatusCodeMyException ex) {
        ApiError apiError = ApiError.builder()
                .code(INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .serviceName(properties.getServiceName())
                .serviceVersion(properties.getServiceVersion())
                .build();

        return ResponseEntity.status(ex.getStatusCode()).body(apiError);
    }
}
