/**
 ** Program Name: Exercise2
 ** Author: Zulhelmi bin Mohamad
 ** Date: April 11th, 2021
 ** Course: CPSC 1150 
 ** Compiler:JDK 15.0.1
 */

public class Exercise2 {
    public static void main(String[] args) throws Exception {
        for (int size=10; size <1000000; size*=10) {
	
			//How many test cases for each instance
			final int TEST_CASE_NUM = 30;

			for(int i = 0; i < TEST_CASE_NUM; ++i){
				//Generates random integer of given size
				int[] list = genArray(7);
                System.out.print("Before: ");
                for (int k = 0; k < list.length; k++){
                    System.out.print(list[k] + ", ");
                }
                System.out.printf("\n");
                int partitionIndex = partitionInteger(list);

                System.out.print("After:  ");
                int[] sortedList = partition(list);
                for (int j = 0; j < sortedList.length; j++){
                    System.out.print(sortedList[j] + ", ");
                }
                System.out.printf("Partition is at: %d", partitionIndex);

                System.out.printf("\n");
                System.out.printf("==========================");
                System.out.printf("\n");
			}
        }

        /*int[] list = {7, 2, 2, 3, 11, 2};
        System.out.print("Before: ");
        for (int k = 0; k < list.length; k++){
            System.out.print(list[k] + ", ");
        }
        System.out.printf("\n");

        System.out.print("After:  ");
        int[] sortedList = partition(list);
        for (int j = 0; j < sortedList.length; j++){
            System.out.print(sortedList[j] + ", ");
        }

        System.out.printf("\n");
        System.out.printf("==========================");
        System.out.printf("\n");

*/
    }

    public static int[] partition(int[] list){
        int pivot = list[0];
        int countGreater = 0;
        int lastIndex = list.length - 1;
        int temp;

        //Counts number of numbers less than or equal to 
        for (int i = 1; i < list.length; ++i){
            if (pivot < list[i]){
                countGreater++;
            }
        }

        //Place pivotIndex in place that is less than future integers greater than pivot

        int pivotIndex = lastIndex - countGreater;
        
        //Swaps first element with pivotIndex
        temp = list[pivotIndex];
        list[pivotIndex] = pivot;
        list[0] = temp;

        //Checks if left or right side gives the lowest iteration for the worst case
        int countLesser = list.length - countGreater - 1;
        int lowestIterationSide;
        if(countLesser < countGreater)
            lowestIterationSide = countLesser;
        else
            lowestIterationSide = countGreater;
        

        boolean leftRightLastElement = false;
        boolean leftLastElement = false;
        boolean rightLastElement = false;
        //boolean isLeftRightOutOfBounds = false;
        //Swaps numbers greater than pivot on the left with numbers less than pivot on the right
        for(int i = 0, left = 0, right = pivotIndex + 1; i < lowestIterationSide; ++i){

            //Checks if it is last element on both sides, and if so everything is checked
            while (!leftRightLastElement){
                
                //Goes through each left side to find first left number that is greater than pivot
                if (left < pivotIndex){
                    while(list[left] <= list[pivotIndex]){
                        leftLastElement = (left >= (pivotIndex - 1));
                        if(!leftLastElement){ 
                            left++;
                        }
                        else
                            break;
                    }
                }

                //Goes through each right side to find first right number that is less than pivot
                if (right < list.length){ 
                    while (list[right] > list[pivotIndex]){
                        rightLastElement = (right >= list.length - 1);
                        if (!rightLastElement){
                            right++;
                        }
                        else
                            break;
                    }
                }
                leftRightLastElement = (leftLastElement && rightLastElement);

                //Swaps left and right if they are in the wrong place
                if((left < pivotIndex) && (right < list.length)){
                    if (list[left] > list[right]){
                        temp = list[right];
                        list[right] = list[left];
                        list[left] = temp;
                    }
                }
                leftRightLastElement = (leftLastElement && rightLastElement);
            }
        }
        return list;
    }

    public static int partitionInteger(int[] list){
        int pivot = list[0];
        int countGreater = 0;
        int lastIndex = list.length - 1;

        //Counts number of numbers less than or equal to 
        for (int i = 1; i < list.length; ++i){
            if (pivot < list[i]){
                countGreater++;
            }
        }

        //Place pivotIndex in place that is less than future integers greater than pivot

        int pivotIndex = lastIndex - countGreater;
    
        return pivotIndex;
    }
    

    public static int[] genArray(int size){

        final int LOWEST_SIZE = 5;

        int randomSize = (int) ((Math.random() * (size - LOWEST_SIZE)) + LOWEST_SIZE);

        int[] randomArray = new int[randomSize];
        for(int i = 0; i < randomArray.length; ++i){
            //Generates an integer between [0,randomSize]
            randomArray[i] = (int) (Math.random() * ((3*randomSize) + 1));
        }
        return randomArray;
    } 

}