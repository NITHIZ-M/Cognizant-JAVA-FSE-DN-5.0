public class Exercise7_FinancialForecasting {
    public static double forecastValue(double currentValue,
                                       double growthRate,
                                       int years) {

        if (years == 0) {
            return currentValue;
        }

        return forecastValue(
                currentValue * (1 + growthRate),
                growthRate,
                years - 1
        );
    }
    public static void main(String[] args) {

        double currentValue = 10000;
        double annualGrowthRate = 0.08;
        int years = 5;
        double futureValue =
                forecastValue(currentValue,
                        annualGrowthRate,
                        years);

        System.out.println("Current Value : ₹" + currentValue);
        System.out.println("Growth Rate   : "
                + (annualGrowthRate * 100) + "%");
        System.out.println("Years         : " + years);

        System.out.printf(
                "Predicted Future Value : ₹%.2f%n",
                futureValue
        );
        System.out.println("\nComplexity Analysis");
        System.out.println("Time Complexity : O(n)");
        System.out.println("Space Complexity: O(n)");
    }
}
