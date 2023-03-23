package cs3500.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * class used to create an animation for assignment 6.
 */
public class ProgrammedAnimation {
  ArrayList<Integer> aList;
  String animate;
  HashMap<Integer, Integer> locations;
  HashMap<Integer, String> colors;
  StringBuilder stringBuilder;


  public ProgrammedAnimation() {
    this.aList = new ArrayList<Integer>();
    animate = "";
    locations = new HashMap<Integer, Integer>();
    colors = new HashMap<Integer, String>();
    stringBuilder = new StringBuilder();

  }


  /**
   * method to move the ovals into place using a gnome sort method.
   *
   * @param arr array of ovals
   * @param j   int
   */
  void gnomeSort(int arr[], int j) {

    int i = 0;
    int time = 0;

    while (i < j) {
      if (i == 0 || arr[i] >= arr[i - 1]) {
        i++;
        time += 2;
      } else {
        int k = 0;
        int l = 0;
        k = arr[i];
        l = arr[i - 1];
        int newxpos = locations.get(l);
        int xpos = locations.get(k);
        animate += "move name " + k + " moveto " + xpos + " 350 " + newxpos + " 350 from " + time
                + " to " + (time + 3) + "\n";
        animate += "move name " + l + " moveto " + newxpos + " 350 " + xpos + " 350 from " + time
                + " to " + (time + 3) + "\n";

        locations.put(k, newxpos);
        locations.put(l, xpos);

        arr[i] = arr[i - 1];
        arr[i - 1] = k;
        i--;
        time += 4;
      }
    }


  }

  /**
   * creates an array and assigns each oval size a name color and position and adds it to the
   * animate string.
   */
  public void makeArray() {
    int[] gnomeArray = {52, 33, 19, 41, 8, 66, 24};
    for (int g = 0; g < 7; g++) {
      int xpos = (g * 100 + 50);
      int swag = gnomeArray[g];
      String color = "";


      animate += "oval name " + swag + " center-x " + xpos + " center-y 350 " +
              "x-radius " + swag + " y-radius " + swag + " color ";

      locations.put(swag, xpos);


      if (swag <= 8) {
        // red
        color = "224 58 105";
        animate += color;
      } else if (swag <= 20) {
        // orange
        color = "224 111 58";
        animate += color;
      } else if (swag <= 26) {
        // yellow
        color = "235 215 42";
        animate += color;
      } else if (swag <= 35) {
        // green
        color = "75 201 77";
        animate += color;
      } else if (swag <= 44) {
        // blue
        color = "91 203 240";
        animate += color;
      } else if (swag <= 53) {
        // indigo
        color = "96 91 245";
        animate += color;
      } else {
        // violet
        color = "189 117 240";
        animate += color;
      }
      animate += " from 0 to 80\n";
      colors.put(swag, color);


    }


    gnomeSort(gnomeArray, gnomeArray.length);
  }


  /**
   * main method to create the string of information for the animation and append it to a txt file.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    ProgrammedAnimation p = new ProgrammedAnimation();
    p.animate += "canvas 700 700 \n";
    p.makeArray();
    p.stringBuilder.append(p.animate);


    try {
      FileWriter myWriter = new FileWriter("gnomesort.txt");
      myWriter.write(p.stringBuilder.toString());
      myWriter.close();
      System.out.println("Successfully wrote to the file.");

    } catch (IOException e) {
      e.printStackTrace();
    }


  }


}
