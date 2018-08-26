package com.AES;

public class Util {

    public static byte[] short2byte (short[] sh) {
        int length = sh.length;
        byte[] by = new byte[length * 2];
        for (int i = 0, j = 0, k; i < length; ) {
            k = sh[i++];
            by[j++] = (byte)((k >>> 8) & 0xFF);
            by[j++] = (byte)( k        & 0xFF);
        }
        return (by);
    }

    public static short[] byte2short (byte[] by) {
        int length = by.length;
        short[] sh = new short[length / 2];
        for (int i = 0, j = 0; j < length / 2; ) {
            sh[j++] = (short)(((by[i++] & 0xFF) <<  8) |
	               ((by[i++] & 0xFF)      ));
        }
        return (sh);
    }

    public static byte[] int2byte (int[] ia) {
        int length = ia.length;
        byte[] by = new byte[length * 4];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            by[j++] = (byte)((k >>>24) & 0xFF);
            by[j++] = (byte)((k >>>16) & 0xFF);
            by[j++] = (byte)((k >>> 8) & 0xFF);
            by[j++] = (byte)( k        & 0xFF);
        }
        return (by);
    }

    public static int[] byte2int (byte[] by) {
        int length = by.length;
        int[] ia = new int[length / 4];
        for (int i = 0, j = 0; j < length / 4; ) {
            ia[j++] = (((by[i++] & 0xFF) << 24) |
	               ((by[i++] & 0xFF) << 16) |
	               ((by[i++] & 0xFF) <<  8) |
	               ((by[i++] & 0xFF)      ));
        }
        return (ia);
    }


    public static final char[] HEXADECIMAL_D = {
        '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
    };

    public static String toHEX (byte[] by) {
        int length = by.length;
        char[] buffer = new char[length * 3];
        for (int i = 0, j = 0, k; i < length; ) {
            k = by[i++];
            buffer[j++] = HEXADECIMAL_D[(k >>> 4) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[ k        & 0x0F];
	    buffer[j++] = ' ';
        }
        return new String(buffer);
    }

    public static String toHEX (short[] ia) {
        int length = ia.length;
        char[] buffer = new char[length * 5];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buffer[j++] = HEXADECIMAL_D[(k >>>12) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>> 8) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>> 4) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[ k        & 0x0F];
	    buffer[j++] = ' ';
        }
        return new String(buffer);
    }


    public static String toHEX (int[] ia) {
        int length = ia.length;
        char[] buffer = new char[length * 10];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buffer[j++] = HEXADECIMAL_D[(k >>>28) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>>24) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>>20) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>>16) & 0x0F];
	    buffer[j++] = ' ';
            buffer[j++] = HEXADECIMAL_D[(k >>>12) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>> 8) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>> 4) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[ k        & 0x0F];
	    buffer[j++] = ' ';
        }
        return new String(buffer);
    }

    public static String toHEX1 (byte b) {
        char[] buffer = new char[2];
	int j = 0;
        buffer[j++] = HEXADECIMAL_D[(b >>> 4) & 0x0F];
        buffer[j++] = HEXADECIMAL_D[ b        & 0x0F];
        return new String(buffer);
    }

    public static String toHEX1 (byte[] by) {
        int length = by.length;
        char[] buffer = new char[length * 2];
        for (int i = 0, j = 0, k; i < length; ) {
            k = by[i++];
            buffer[j++] = HEXADECIMAL_D[(k >>> 4) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[ k        & 0x0F];
        }
        return new String(buffer);
    }

    public static String toHEX1 (short[] ia) {
        int length = ia.length;
        char[] buffer = new char[length * 4];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buffer[j++] = HEXADECIMAL_D[(k >>>12) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>> 8) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>> 4) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[ k        & 0x0F];
        }
        return new String(buffer);
    }

    public static String toHEX1 (int i) {
        char[] buffer = new char[8];
	int j = 0;
        buffer[j++] = HEXADECIMAL_D[(i >>>28) & 0x0F];
        buffer[j++] = HEXADECIMAL_D[(i >>>24) & 0x0F];
        buffer[j++] = HEXADECIMAL_D[(i >>>20) & 0x0F];
        buffer[j++] = HEXADECIMAL_D[(i >>>16) & 0x0F];
        buffer[j++] = HEXADECIMAL_D[(i >>>12) & 0x0F];
        buffer[j++] = HEXADECIMAL_D[(i >>> 8) & 0x0F];
        buffer[j++] = HEXADECIMAL_D[(i >>> 4) & 0x0F];
        buffer[j++] = HEXADECIMAL_D[ i        & 0x0F];
        return new String(buffer);
    }

    public static String toHEX1 (int[] ia) {
        int length = ia.length;
        char[] buffer = new char[length * 8];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buffer[j++] = HEXADECIMAL_D[(k >>>28) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>>24) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>>20) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>>16) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>>12) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>> 8) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[(k >>> 4) & 0x0F];
            buffer[j++] = HEXADECIMAL_D[ k        & 0x0F];
        }
        return new String(buffer);
    }


    public static byte[] hex2byte(String hex) {
        int length = hex.length();
        byte[] buffer = new byte[((length + 1) / 2)];

        int i = 0, j = 0;
        if ((length % 2) == 1)
            buffer[j++] = (byte) hexDigit(hex.charAt(i++));

        while (i < length) {
            buffer[j++] = (byte) ((hexDigit(hex.charAt(i++)) << 4) |
                                hexDigit(hex.charAt(i++)));
        }
        return buffer;
    }

    
    public static int hexDigit(char ch) {
        if (ch >= '0' && ch <= '9')
            return ch - '0';
        if (ch >= 'A' && ch <= 'F')
            return ch - 'A' + 10;
        if (ch >= 'a' && ch <= 'f')
            return ch - 'a' + 10;

        return(0);	
    }
}
