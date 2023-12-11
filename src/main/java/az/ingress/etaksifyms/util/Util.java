package az.ingress.etaksifyms.util;

public class Util {

    public static final String formatNameSurname(String name, String surname){
        return name != null && surname !=null ? name.toLowerCase()+ "  " + surname.toLowerCase(): "   ";
    }
}
