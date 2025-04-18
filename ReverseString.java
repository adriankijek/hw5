public class ReverseString {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a string.");
            return;
        }

        String input = args[0];
        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }

        System.out.println(reversed);
    }
}
