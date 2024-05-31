package com.maps.api.console;

import java.util.ArrayList;
import java.util.List;

public class Display {
    private String name;
    private int height;
    private int width;
    private List<String> screen;

    public Display(String name, int height, int width, String car) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.screen = new ArrayList<>();

        String zeroString = car.repeat(this.width);
        for (int i = 0; i < this.height; i++) {
            this.screen.add(zeroString);
        }
    }

    public String getName() {
        return this.name;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void printScreen() {
        for (String str : this.screen) {
            System.out.println(str);
        }
    }

    public void addPoint(int x, int y, char car) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            String originalString = this.screen.get(x);
            StringBuilder modifiedString = new StringBuilder(originalString);
            modifiedString.setCharAt(y, car);
            this.screen.set(x, modifiedString.toString());
        }
    }

    public void addLine(int x1, int y1, int x2, int y2, char car) {
        if (x1 > x2 || y1 > y2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        if (x2 - x1 > y2 - y1){
            float a = (float) (y2 - y1)/ (float) (x2 - x1);
            float b = y1 - a*x1;
            for (int i = x1; i < x2; i++) {
                //System.out.print(i);
                //System.out.print("\n");
                //System.out.print(a);
                //System.out.print("\n");
                //System.out.print(b);
                //System.out.print("\n");
                //System.out.print(a*i+b);
                this.addPoint(i, (int) (a*i+b), car);
            }
        }else{
            float a = (float) (x2 - x1)/ (float) (y2 - y1);
            float b = x1 - a*y1;
            for (int i = y1; i < y2; i++) {
                //System.out.print(i);
                //System.out.print("\n");
                //System.out.print(a);
                //System.out.print("\n");
                //System.out.print(b);
                //System.out.print("\n");
                //System.out.print(a*i+b);
                this.addPoint((int) (a*i+b), i, car);
            }
        }
    }

    public void addText(String text, int x, int y) {
        for (int i = x; i < x+text.length(); i++) {
            this.addPoint(y, i, text.charAt(i-x));
        }
    }
}
