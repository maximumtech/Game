package Game.interaction;

import java.util.HashMap;

/**
 *
 * @author maximumtech
 */
public class KeyConfig {
    
    public static KeyConfig instance = new KeyConfig();
    
    private HashMap<String, String> keys = new HashMap<>();
    
    public void save(){
        
    }
    
    public void load() {
        
    }
    
    public String getKeyFromName(String name, String def) {
        String key = keys.get(name);
        if(key==null) {
            setKey(name, def);
            return def;
        }else{
            return key;
        }
    }
    
    public void setKey(String name, String key) {
        keys.put(name, key);
    }
}
