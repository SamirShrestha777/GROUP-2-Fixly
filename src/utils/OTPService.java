package utils;

import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;

public class OTPService {

    private static final Map<String, String> otpStore = new HashMap<>();
    private static final Map<String, Long> otpTimestamp = new HashMap<>();
    private static final long EXPIRY_MS = 5 * 60 * 1000;

    public static String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static void saveOTP(String email, String otp) {
        otpStore.put(email, otp);
        otpTimestamp.put(email, System.currentTimeMillis());
    }

    public static boolean verifyOTP(String email, String enteredOTP) {
        String storedOTP = otpStore.get(email);
        Long timestamp = otpTimestamp.get(email);
        if (storedOTP == null || timestamp == null) return false;
        if (System.currentTimeMillis() - timestamp > EXPIRY_MS) {
            otpStore.remove(email);
            otpTimestamp.remove(email);
            return false;
        }
        if (storedOTP.equals(enteredOTP)) {
            otpStore.remove(email);
            otpTimestamp.remove(email);
            return true;
        }
        return false;
    }
}