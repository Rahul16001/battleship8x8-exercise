package com.epam.rd.autotasks;

import java.util.List;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        int col = shot.charAt(0) - 'A';
        int row = shot.charAt(1) - '1';
        int index = (col) + 8*row;

        long shotBit = 1L << (63 - index); //Here we are shifting bits to left....
        shots|=shotBit; // here we are storing shot at a particular index in shots
        return (ships & shotBit) != 0L; // if there is a ship at the index where we shot return true else false
                                       //here we are checking if value from ships & shotBit is not 0L then there must be
    }                                  //a ship at that location which is contra-positive of equal to some positive value

    public String state() {

        StringBuilder res = new StringBuilder();

        for(int i = 0 ;i < 64; i++)
        {
            if(i > 0 && i % 8 == 0) res.append("\n");// after every 8 bits we are appending a new line

            long index = 1L << (63- i); // this line gets bit from left side

            boolean isShot = (shots & index) != 0L; // this line checks if we have shot at the particular index
            boolean isShip = (ships & index) != 0L;// this line checks if there is a ship at the particular index
            System.out.println(isShot +" "+isShip);

            if(isShot)
            {
                if(isShip) res.append('☒');
                else res.append('×');
            }
            else
            {
                if(isShip) res.append('☐');
                else res.append('.');
            }

        }
        return res.toString();
    }

    public static void main(String[] args) {
        List<String> shots = List.of("A3", "D7", "A5", "F3", "H8", "D3", "B3", "G8", "C3");
        long map = 5783259913403860192L;
        Battleship8x8 obj  = new Battleship8x8(map);

        for(String x : shots)
        {
            obj.shoot(x);
        }
        System.out.println(obj.state());

    }
}
