//Kenil Patel


import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

//"1.Selection sort Merge Sort Bubble sort Quicksort 5.Radix sort

public class AlgoSpeedometer {
    private static void createAndWriteSortedFile(int[] array, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int value: array) {
                writer.write(value + "\n");
            }
            writer.close();
            System.out.println("Sorted file '" + fileName + "' created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating sorted file: " + e.getMessage());
        }
    }

    private static void radixSort(int[] arr) {
        // Find the maximum number to know the number of digits
        int max = getMax(arr);

        // Do counting sort for every digit place
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Initialize count array
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[] so that arr[] contains sorted numbers according to current digit
        System.arraycopy(output, 0, arr, 0, n);
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int value: arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    private static int[] readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            int lineCounter = 0;

            // Count the number of lines in the file
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                lineCounter++;
            }

            fileScanner.close();
            fileScanner = new Scanner(file);

            // Create an array to store the integers
            int[] default_array = new int[lineCounter];

            // Read integers from each line and store them in the array
            for (int i = 0; i < lineCounter; i++) {
                if (fileScanner.hasNextInt()) {
                    default_array[i] = fileScanner.nextInt();
                } else {
                    // Handle the case where the file contains non-integer data
                    System.out.println("Error: Non-integer data found in the file.");
                    break;
                }
            }

            fileScanner.close();

            return default_array;

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null; // Return null to indicate an error
        }
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        // Move elements smaller than the pivot to the left
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);

        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int length = array.length;
        int[] auxiliaryArray = new int[length];
        mergeSort(array, auxiliaryArray, 0, length - 1);
    }

    private static void mergeSort(int[] array, int[] auxiliaryArray, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(array, auxiliaryArray, low, middle);
            mergeSort(array, auxiliaryArray, middle + 1, high);
            merge(array, auxiliaryArray, low, middle, high);
        }
    }

    private static void merge(int[] array, int[] auxiliaryArray, int low, int middle, int high) {
        // Copy data to auxiliary arrays
        System.arraycopy(array, low, auxiliaryArray, low, high - low + 1);

        int i = low;
        int j = middle + 1;
        int k = low;

        // Merge the two sub-arrays
        while (i <= middle && j <= high) {
            if (auxiliaryArray[i] <= auxiliaryArray[j]) {
                array[k] = auxiliaryArray[i];
                i++;
            } else {
                array[k] = auxiliaryArray[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            array[k] = auxiliaryArray[i];
            k++;
            i++;
        }

        while (j <= high) {
            array[k] = auxiliaryArray[j];
            k++;
            j++;
        }
    }
    private static void selectionSort(int[] default_array) {
        int n = default_array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (default_array[j] < default_array[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the element at i
            int temp = default_array[minIndex];
            default_array[minIndex] = default_array[i];
            default_array[i] = temp;
        }
    }
    private static void print_10(int[] default_array) {
        int size = default_array.length;
        System.out.println("Shows only 10 entries");
        if (size > 10) {
            for (int i = 0; i < 10; i++) {
                System.out.println(default_array[i]);
            }
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(default_array[i]);
            }
        }
    }
    private static int[] create_array(int size) {
        Scanner scanner = new Scanner(System.in);
        int[] newArray = new int[size];
        for (int i = 0; i < size; i++) {
            int temp = scanner.nextInt();
            newArray[i] = temp;
        }
        return newArray;
    }
    private static void print_info(int value) {
        if (value == 1) {
            System.out.println("Selection Sort has a runtime of O(n^2), meaning that as the number of elements (n) increases, the runtime grows quadratically." + "\nSo, the more elements you have, the longer it takes to sort them. Another way to look at it is that it takes a long time but does not use more memory.");
        }
        if (value == 2) {
            System.out.println("Merge Sort has a runtime of O(n log n) where n is the number of elements." + "\nMerge sort uses a method known as divide-and-conquer, breaking the array into segments and sorting each segment as if it were a small array." + "\nThe runtime of merge sort is significantly shorter than algorithms like bubble sort or selection sort. However, it uses more memory due to recursion.");
        }
        if (value == 3) {
            System.out.println("Bubble Sort has a runtime of O(n^2), meaning that as the number of elements (n) increases, the runtime grows quadratically." + "\nIt is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order." + "\nIf the array is already sorted, the runtime is O(n), which is constant time and very fast.");
        }
        if (value == 4) {
            System.out.println("Quick Sort has an average-case runtime of O(n log n), making it more efficient than many sorting algorithms." + "\nQuicksort is a divide-and-conquer algorithm that selects a 'pivot' element and partitions the other elements into two sub-arrays." + "\nThe elements smaller than the pivot go to the left, and those greater go to the right. The process is then applied recursively to the sub-arrays.");
        }
        if (value == 5) {
            System.out.println("Radix Sort is a linear-time sorting algorithm that works by distributing elements into buckets according to their individual digits." + "\nIt processes the digits from the least significant to the most significant, producing a sorted list. Radix sort is often used for integers or strings of fixed length.");
        } else {
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int dataOption = 0; //User select where to get data for sorting;

        int use_Data = 0; // user chooses to use their own data

        int select_input = 0; // just keeps track of user input

        int[] default_array=null;

        int[] User_array = null; //user manual input data array

        int number_of_elements = 0; // number of elements

        double start; //starts clock to mesure time

        double end; //ends the the clock

        double elapsed; //start-end gives time in MS

        String user_file_path; //for user if they choose to use a file for thir data

        int[] user_file_array = null; //creats a array from file user porvided.

        int default_number_of_elements=0;

        System.out.println("Welcome!");

        System.out.println("This program lets you see run time of some sorting algo, \nThis program will also allow you to either use default data provided by me 2 M numbers \nor use your own data given a file or typing it out (INT ONLY)");

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose what algorithm you want to run.\n");

        while (select_input != 6) {
            default_array = readFile("src/data.txt");
            System.out.print("1.Selection sort \n2.Merge Sort \n3.Bubble sort \n4.Quicksort \n5.Radix sort\n6.EXIT");  //selection
            System.out.println(); //clears the buffer
            select_input = scanner.nextInt();
            // Everything else has similar decelaration. only the soring algorythem changes
            if (select_input == 1) {
                System.out.println("\nYou have selected Selection Sort");

                use_Data = 0;

                System.out.println("\n1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");

                use_Data = scanner.nextInt();

                while (use_Data > 2 || use_Data < 1) { //ask for valid user input if not valid
                    System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");
                    use_Data = scanner.nextInt();
                }
                if (use_Data == 1) { //using default data

                    System.out.println("\nUsing Default Data of 2 Million Entries");

                    System.out.println("This is the before Data\n");

                    print_10(default_array);

                    start = System.currentTimeMillis();  //start timer

                    selectionSort(default_array);   //sends array for sorting

                    end = System.currentTimeMillis(); //after array comes back, timer ends

                    elapsed = end - start;  //calculates total time taken

                    System.out.println("After Sorting\n");

                    print_10(default_array);

                    default_number_of_elements=default_array.length;

                    elapsed = elapsed / 60000;      //divides to get time in Minutes

                    System.out.println("Time in Minutes to Sort : " + default_number_of_elements + " elements " + elapsed);

                } else { //not using default data
                    System.out.println("\nEnter your data");
                    dataOption = 0;
                    System.out.println("1.Enter Manually\n2.From File");
                    dataOption = scanner.nextInt();
                    while (dataOption > 2 || dataOption < 1) {  //checks for valid input and asks again if invalid
                        System.out.println("1.Enter Manually\n2.From File");
                        dataOption = scanner.nextInt();
                    }
                    if (dataOption == 1) { //entering data manually

                        System.out.println("How Many Numbers?");

                        number_of_elements = scanner.nextInt();    // asks user for number of elements to sort

                        System.out.println("Enter Data (INT ONLY)");

                        User_array = create_array(number_of_elements);   //creates an array so it can be sorted

                        System.out.println("This is the before Data\n");

                        print_10(User_array);

                        start = System.currentTimeMillis();

                        selectionSort(User_array);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 60000;

                        System.out.println("After Sorting");

                        print_10(User_array);

                        System.out.println("Time in Minutes to Sort : " + User_array.length + " elements " + elapsed);

                    } else { //using a file
                        System.out.print("Enter File name");

                        user_file_path = scanner.nextLine();
                        user_file_path = scanner.nextLine();   //clears the buffer

                        user_file_array = readFile(user_file_path);

                        System.out.println("This is the before Data");

                        print_10(user_file_array);

                        start = System.currentTimeMillis();

                        selectionSort(user_file_array);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 60000;

                        System.out.println("After Sorting");

                        print_10(user_file_array);

                        createAndWriteSortedFile(user_file_array, user_file_path);   //puts sorted data back to file

                        System.out.println("Time in Minutes to Sort : " + user_file_array.length + " elements " + elapsed);

                    }

                }
                print_info(1);

            } else if (select_input == 2) {
                System.out.println("You have selected Merge Sort");

                use_Data = 0;

                System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");

                use_Data = scanner.nextInt();

                while (use_Data > 2 || use_Data < 1) {
                    System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");
                    use_Data = scanner.nextInt();
                }

                if (use_Data == 1) { //using default data

                    System.out.println("using Default Data of 1 Million Entries");

                    System.out.println("This is the before Data");

                    print_10(default_array);

                    start = System.currentTimeMillis();

                    mergeSort(default_array);

                    end = System.currentTimeMillis();

                    elapsed = end - start;

                    System.out.println("After Sorting");

                    print_10(default_array);

                    default_number_of_elements=default_array.length;

                    elapsed = elapsed / 1000;

                    System.out.println("Time in Seconds to Sort : " + default_number_of_elements + " elements " + elapsed);

                } else { //not using default data

                    System.out.println("Enter your data");

                    dataOption = 0;

                    System.out.println("1.Enter Manually\n2.From File");

                    dataOption = scanner.nextInt();

                    while (dataOption > 2 || dataOption < 1) {
                        System.out.println("1.Enter Manually\n2.From File");
                        dataOption = scanner.nextInt();
                    }
                    if (dataOption == 1) { //entering data manually

                        System.out.println("How Many Numbers?");

                        number_of_elements = scanner.nextInt();

                        System.out.println("Enter Data (INT ONLY)");

                        User_array = create_array(number_of_elements);

                        System.out.println("This is the before Data");

                        print_10(User_array);

                        start = System.currentTimeMillis();

                        mergeSort(User_array);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 1000;

                        System.out.println("After Sorting\n");

                        print_10(User_array);

                        System.out.println("Time in Seconds to Sort : " + User_array.length + " elements " + elapsed);

                    } else { //using a file

                        System.out.println("Enter File name");

                        user_file_path = scanner.nextLine();

                        user_file_path = scanner.nextLine();

                        user_file_array = readFile(user_file_path);

                        System.out.println("This is the before Data\n");

                        print_10(user_file_array);

                        start = System.currentTimeMillis();

                        //sorts using Merge sort
                        mergeSort(user_file_array);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 1000;

                        System.out.println("After Sorting\n");

                        print_10(user_file_array);

                        createAndWriteSortedFile(user_file_array, user_file_path);

                        System.out.println("Time in Seconds to Sort : " + user_file_array.length + " elements " + elapsed);

                    }

                }
                print_info(2);

            } else if (select_input == 3) {

                System.out.println("you have selected Bubble sort\n");

                use_Data = 0;

                System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");

                use_Data = scanner.nextInt();

                while (use_Data > 2 || use_Data < 1) {

                    System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");

                    use_Data = scanner.nextInt();
                }
                if (use_Data == 1) { //using default data

                    System.out.println("using Default Data of 2 Million Entries\n");

                    System.out.println("This is the before Data\n");

                    print_10(default_array);

                    start = System.currentTimeMillis();

                    bubbleSort(default_array);

                    end = System.currentTimeMillis();

                    elapsed = end - start;

                    System.out.println("After Sorting");

                    print_10(default_array);

                    elapsed = elapsed / 60000;

                    default_number_of_elements=default_array.length;

                    System.out.println("Time in Minutes to Sort : " + default_number_of_elements + " elements " + elapsed);

                    select_input = 6;

                } else { //not using default data

                    System.out.println("Enter your data\n");

                    dataOption = 0;

                    System.out.println("1.Enter Manually\n2.From File");

                    dataOption = scanner.nextInt();

                    while (dataOption > 2 || dataOption < 1) {

                        System.out.println("1.Enter Manually\n2.From File");

                        dataOption = scanner.nextInt();
                    }

                    if (dataOption == 1) { //entering data manually

                        System.out.println("How Many Numbers?");

                        number_of_elements = scanner.nextInt();

                        System.out.println("Enter Data (INT ONLY)");

                        User_array = create_array(number_of_elements);

                        System.out.println("This is the before Data\n");

                        print_10(User_array);

                        start = System.currentTimeMillis();

                        bubbleSort(User_array);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 60000;

                        System.out.println("After Sorting\n");

                        print_10(User_array);

                        System.out.println("Time in Minutes to Sort : " + User_array.length + " elements " + elapsed);

                    } else { //using a file

                        System.out.println("\nEnter File name");

                        user_file_path = scanner.nextLine();

                        user_file_path = scanner.nextLine();

                        user_file_array = readFile(user_file_path);

                        System.out.println("This is the before Data\n");

                        print_10(user_file_array);

                        start = System.currentTimeMillis();

                        bubbleSort(user_file_array);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 60000;

                        System.out.println("After Sorting");

                        print_10(user_file_array);

                        createAndWriteSortedFile(user_file_array, user_file_path);

                        System.out.println("Time in Minutes to Sort : " + user_file_array.length + " elements " + elapsed);

                    }

                }
                print_info(3);

            } else if (select_input == 4) {

                System.out.println("\nYou have selected Quicksort\n");

                use_Data = 0;

                System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");

                use_Data = scanner.nextInt();

                while (use_Data > 2 || use_Data < 1) {
                    System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");
                    use_Data = scanner.nextInt();
                }

                if (use_Data == 1) { //using default data

                    System.out.println("using Default Data of 2 Million Entries");

                    System.out.println("This is the before Data\n");

                    print_10(default_array);

                    start = System.currentTimeMillis();

                    quickSort(default_array, 0, default_array.length - 1);

                    end = System.currentTimeMillis();

                    elapsed = end - start;

                    System.out.println("After Sorting\n");

                    print_10(default_array);

                    elapsed = elapsed / 1000;
                    default_number_of_elements=default_array.length;

                    System.out.println("Time in Seconds to Sort : " + default_number_of_elements + " elements " + elapsed);

                } else { //not using default data

                    System.out.println("\nEnter your data");

                    dataOption = 0;

                    System.out.println("1.Enter Manually\n2.From File");

                    dataOption = scanner.nextInt();

                    while (dataOption > 2 || dataOption < 1) {

                        System.out.println("1.Enter Manually\n2.From File");
                        dataOption = scanner.nextInt();
                    }
                    if (dataOption == 1) { //entering data manually


                        System.out.println("\nHow Many Numbers?");

                        number_of_elements = scanner.nextInt();

                        System.out.println("Enter Data (INT ONLY)");

                        User_array = create_array(number_of_elements);

                        System.out.println("This is the before Data");

                        print_10(User_array);

                        start = System.currentTimeMillis();

                        quickSort(User_array, 0, User_array.length - 1);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 1000;

                        System.out.println("After Sorting");

                        print_10(User_array);

                        System.out.println("Time in Seconds to Sort : " + User_array.length + " elements " + elapsed);

                    } else { //using a file

                        System.out.println("\nEnter File name");

                        user_file_path = scanner.nextLine();

                        user_file_path = scanner.nextLine();

                        user_file_array = readFile(user_file_path);

                        System.out.println("This is the before Data\n");

                        print_10(user_file_array);

                        start = System.currentTimeMillis();

                        quickSort(user_file_array, 0, user_file_array.length - 1);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 1000;

                        System.out.println("After Sorting\n");

                        print_10(user_file_array);

                        createAndWriteSortedFile(user_file_array, user_file_path);

                        System.out.println("Time in Seconds to Sort : " + user_file_array.length + " elements " + elapsed);

                    }

                }
                print_info(4);

            } else if (select_input == 5) {

                System.out.println("\nYou have selected Radix sort\n");

                use_Data = 0;

                System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");

                use_Data = scanner.nextInt();

                while (use_Data > 2 || use_Data < 1) {

                    System.out.println("1.Use Default data (2 Million entries)\n2.Use your own data (INT ONLY)");

                    use_Data = scanner.nextInt();
                }

                if (use_Data == 1) { //using default data

                    System.out.println("Using Default Data of 2 Million Entries\n");

                    System.out.println("This is the before Data\n");

                    print_10(default_array);

                    start = System.currentTimeMillis();

                    radixSort(default_array);

                    end = System.currentTimeMillis();

                    elapsed = end - start;

                    System.out.println("After Sorting\n");

                    print_10(default_array);

                    elapsed = elapsed / 1000;

                    default_number_of_elements=default_array.length;


                    System.out.println("Time in Seconds to Sort : " + default_number_of_elements + " elements " + elapsed);


                } else { //not using default data

                    System.out.println("\nEnter your data");

                    dataOption = 0;

                    System.out.println("1.Enter Manually\n2.From File");

                    dataOption = scanner.nextInt();

                    while (dataOption > 2 || dataOption < 1) {

                        System.out.println("1.Enter Manually\n2.From File");

                        dataOption = scanner.nextInt();
                    }
                    if (dataOption == 1) { //entering data manually

                        System.out.println("\nHow Many Numbers?");

                        number_of_elements = scanner.nextInt();

                        System.out.println("Enter Data (INT ONLY)");

                        User_array = create_array(number_of_elements);

                        System.out.println("This is the before Data\n");

                        print_10(User_array);

                        start = System.currentTimeMillis();

                        radixSort(User_array);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 1000;

                        System.out.println("After Sorting\n");

                        print_10(User_array);

                        System.out.println("\nTime in Seconds to Sort : " + User_array.length + " elements " + elapsed);

                    } else { //using a file

                        System.out.println("\nEnter File name");

                        user_file_path = scanner.nextLine();

                        user_file_path = scanner.nextLine();

                        user_file_array = readFile(user_file_path);

                        System.out.println("This is the before Data\n");

                        print_10(user_file_array);

                        start = System.currentTimeMillis();

                        radixSort(user_file_array);

                        end = System.currentTimeMillis();

                        elapsed = end - start;

                        elapsed = elapsed / 1000;

                        System.out.println("After Sorting\n");

                        print_10(user_file_array);

                        createAndWriteSortedFile(user_file_array, user_file_path);

                        System.out.println("Time in Seconds to Sort : " + user_file_array.length + " elements " + elapsed);

                    }

                }
                print_info(5);

            } else {
                select_input = 6;
            }
        }
        System.out.println("Thank you for using AlgoSpeedometer\n");
    }
}
