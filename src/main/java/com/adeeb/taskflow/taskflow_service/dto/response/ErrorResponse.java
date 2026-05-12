package com.adeeb.taskflow.taskflow_service.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timeStamp,
                            int status,
                            String error,
                            String message) {
}
