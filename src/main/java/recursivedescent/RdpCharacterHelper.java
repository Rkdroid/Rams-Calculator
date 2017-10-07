package recursivedescent;

/**
 * Created by ram on 10/7/17.
 */
public class RdpCharacterHelper {
    public static boolean isCharacter(char c){
        return (c >= 'a' && c <= 'z');
    }

    public static boolean isNumber(char c) {
        return (c>='0' && c<='9');
    }
}
