package me.upskill.schoolattendance.api;

public class ContextHolder {

    private static final ThreadLocal<Long> CONTEXT = new ThreadLocal<>();

    // save the user id to the thread
    public static void saveUserId(Long userId) {
        CONTEXT.set(userId);
    }

    public static Long getUserId() {
        return CONTEXT.get();
    }

    public static void clearUserId() {
        CONTEXT.remove();
    }
}
