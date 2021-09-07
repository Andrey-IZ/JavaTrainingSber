import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Task3Test {
    private static void readContent(String url) throws IOException {
        Object content = new URL(url).getContent();

        if (content instanceof InputStream) {
            InputStream contentStream = (InputStream) content;
            try (Scanner contentScanner = new Scanner(contentStream)) {
                while (contentScanner.hasNext()) {
                    System.out.println(contentScanner.next());
                }
            }
        } else {
            throw new IOException("Unsupported content kind");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in); // System.in закрывать не нужно

        do {
            System.out.println("Type url");
            String url = scanner.next();

            try {
                readContent(url);

                break;
            } catch (MalformedURLException e) {
                System.out.println("Malformed URL specified!");
            } catch (UnknownHostException e) {
                System.out.println("Unknown host!");
            } catch (IOException e) {
                System.out.println("Unable to get site content. " + e.getMessage());
                throw e;
            }

            System.out.println("Try again");
        } while (true);
    }
}
