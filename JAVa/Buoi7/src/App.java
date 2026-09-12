import frontend.QLApplication;
import models.ConnectionDetail;

public class App {
    public static void main(String[] args) throws Exception {
        ConnectionDetail detail = new ConnectionDetail("URL","USERNAME","PASSWORD"); //TODO fill the param first.
        QLApplication application = new QLApplication(detail);
        application.mainApplication();
    }
}
