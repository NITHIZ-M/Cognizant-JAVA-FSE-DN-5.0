public class SingletonPatternExample {
    static class Logger {
        private static Logger instance;
        private Logger() {
        }
        public static Logger getInstance() {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }
        public void log(String message) {
            System.out.println("[LOG] " + message);
        }
    }
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        logger1.log("Application Started");
        System.out.println("Same Instance: " + (logger1 == logger2));
    }
}