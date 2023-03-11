import java.io.File;

public class test {

    public static void main(String[] args){
        String path = new File(System.getProperty("user.dir")).toString();
        path += "This\\IS\\A\\test";
        System.out.println(path);
    }
}
