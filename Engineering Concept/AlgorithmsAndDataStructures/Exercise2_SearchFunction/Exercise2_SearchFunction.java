public class Exercise2_SearchFunction {
    static class Product {
        int productId;
        String productName;
        String category;

        Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }
    }
    public static int linearSearch(Product[] products, String target) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch(Product[] products, String target) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int comparison =
                    products[mid].productName.compareToIgnoreCase(target);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {

        Product[] products = {
                new Product(101, "Camera", "Electronics"),
                new Product(102, "Headphones", "Electronics"),
                new Product(103, "Laptop", "Electronics"),
                new Product(104, "Mobile", "Electronics"),
                new Product(105, "SmartWatch", "Electronics")
        };

        String searchItem = "Laptop";

        int linearResult = linearSearch(products, searchItem);

        if (linearResult != -1) {
            System.out.println("Linear Search:");
            System.out.println("Found at Index: " + linearResult);
        } else {
            System.out.println("Product Not Found");
        }

        int binaryResult = binarySearch(products, searchItem);

        if (binaryResult != -1) {
            System.out.println("\nBinary Search:");
            System.out.println("Found at Index: " + binaryResult);
        } else {
            System.out.println("Product Not Found");
        }

        System.out.println("\nComplexity Analysis");
        System.out.println("Linear Search  : O(n)");
        System.out.println("Binary Search  : O(log n)");
    }
}