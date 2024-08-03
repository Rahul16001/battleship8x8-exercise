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

        long shotBit = 1L << (63 - index);
        shotBit = ships & shotBit;

        if(shotBit == 0L) return false;

        shots|=shotBit;
        return true;

    }

    public String state() {

        StringBuilder res = new StringBuilder();

        for(int i = 0 ;i < 64; i++)
        {
            if(i > 0 && i % 8 == 0) res.append("\n");

            long index = 1L << (63- i);

            boolean isShot = (shots & index) != 0L;
            boolean isShip = (ships & index) != 0L;

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
