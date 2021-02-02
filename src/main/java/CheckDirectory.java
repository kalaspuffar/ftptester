import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CheckDirectory {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Choose either data or test as an argument");
            System.exit(0);
        }

        if (args[0].equalsIgnoreCase("test")) {
            FtpTester.main(args);
            System.exit(0);
        }

        if (!args[0].equalsIgnoreCase("data")) {
            System.exit(-1);
        }

        try {
            File dir = new File("data");
            for (File f : dir.listFiles()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String name = br.readLine();
                if(!f.getName().equals(name)) {
                    System.out.println(f.getName() + " != " + name);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
