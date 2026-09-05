package springwebmvc.exception;

public record ErrorResponseDto(
        ErrorCode errorCode,
        String message
) {
}
