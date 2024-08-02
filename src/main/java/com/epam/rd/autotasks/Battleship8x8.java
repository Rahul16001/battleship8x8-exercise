package com.epam.rd.autotasks;

import java.util.List;

public class Battleship8x8 {
    private final long ships;
    String map;
    char []resultMap;

    String getMap(long ship)
    {
        StringBuilder str= new StringBuilder();

        if(ship < 0)
        {
            ship = ~(-1*ship) + 1;
        }

        for(int i=0;i<64;i++)
        {
            int x = (int)(ship & 1);
            str.append(x);
            ship>>=1;
        }
        str.reverse();
        return str.toString();
    }

    public Battleship8x8(final long ships) {
        this.ships = ships;
        this.map = getMap(ships);
        this.resultMap = new char[64];

        for(int i=0;i<64;i++)
        {
            if(map.charAt(i) == '1') resultMap[i] = '1';
            else resultMap[i] = '0';
        }
    }

    public boolean shoot(String shot) {
        int decodeChar = (int)(shot.charAt(0) - 'A');
        int decodeNum = (int)(shot.charAt(1) - '1');
        int index = (decodeChar) + 8*decodeNum;

        if(map.charAt(index) == '1')
        {
            resultMap[index] = '☒';
            return true;
        }
        else
        {
            resultMap[index] = '×';
        }
        return false;
    }

    public String state() {

        StringBuilder mapState = new StringBuilder();
        for(int i=0;i<64;i++)
        {
            if(resultMap[i] == '1')
            {
                resultMap[i] = '☐';
            }
            else if(resultMap[i] == '0')
            {
                resultMap[i] = '.';
            }
        }

        for(int i=0;i<64;i++)
        {
            if(i != 0 && i % 8 == 0) mapState.append("\n");
            mapState.append(resultMap[i]);
        }

        return mapState.toString();
    }

    public static void main(String[] args) {
        List<String> shots = List.of("A3", "D7", "A5", "F3", "H8", "D3", "B3", "G8", "C3");
        long map = 5783259913403860192L;
        Battleship8x8 obj  = new Battleship8x8(map);

        for(String x : shots)
        {
            obj.shoot(x);
        }

        String res = obj.state();
        System.out.println(res);

    }
}
