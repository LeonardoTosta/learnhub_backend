package exceptions;

public class UserNotFoundException extends RuntimeException {
    private final Long userId;

    public UserNotFoundException(Long userId) {
        super("user.not.found");
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Object[] getArgs() {
        return new Object[]{userId};
    }
}