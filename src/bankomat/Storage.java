package bankomat;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    public Map<String, Integer> balance = new HashMap<>();
    public Map<String, String> logins = new HashMap<>();
    public Storage() {
        balance.put("admin", 1234);
        balance.put("user1", 0);
        balance.put("grisha", 1000);

        logins.put("admin", "ZZZ");
        logins.put("user1", "12345");
        logins.put("grisha", "password");
    }
}