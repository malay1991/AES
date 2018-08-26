package com.AES;

import java.util.Arrays;

public class AES {

	int numberRound;
	byte[][] Ke;
	byte[][] Kd;

	public int tracingLevel = 0;
	public String Information = "";

	public static final int NUMBER_ROUNDS = 14, 
							BLOCK_SIZE = 16, 
							KEY_LENGTH = 32; 
	
	/*static final byte[] Sbox = {
			99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, 
			-54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, 
			-73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 
			4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 
			9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 
			83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, 
			-48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 
			81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, 
			-51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 
			96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, 
			-32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, 
			-25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, 
			-70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 
			112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, 
			-31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, 
			-116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22 }; */
	
	
	static final byte[] Sbox = {
			99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, 
			-54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, 
			-73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 
			4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 
			9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 
			83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, 
			-48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 
			81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, 
			-51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 
			96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, 
			-32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, 
			-25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, 
			-70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 
			112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, 
			-31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, 
			-116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22 };
	

	
	/**
	 * AES decryption Sbox-box.
	 */
	static final byte[] Si = { 82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57,
			-126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18,
			76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114,
			-8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71,
			-38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77,
			69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103,
			-36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24,
			28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75,
			-58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16,
			89, 39, -128, -20, 95, 96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32,
			59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31,
			105, 20, 99, 85, 33, 12, 125 };

	
	static final byte[] constant = { 0, 1, 2, 4, 8, 16, 32, 64, -128, 27, 54, 108, -40, -85, 77, -102, 47, 94, -68, 99, -58,
			-105, 53, 106, -44, -77, 125, -6, -17, -59, -111 };

	public static final int COLUMN_SIZE = 4, 
							NUMBER_COLUMNS = BLOCK_SIZE / COLUMN_SIZE, 
							ROOT = 0x11B; 

	static final int[] rowshift = { 0, 1, 2, 3 };
	static final int[] alog = new int[256];
	static final int[] log = new int[256];

	static {
		int i, j;
		alog[0] = 1;
		for (i = 1; i < 256; i++) {
			j = (alog[i - 1] << 1) ^ alog[i - 1];
			if ((j & 0x100) != 0)
				j ^= ROOT;
			alog[i] = j;
		}
		for (i = 1; i < 255; i++) {
			log[alog[i]] = i;
		}
	}

	public AES() {
	}

	public static int getRounds(int keySize) {
		switch (keySize) {
		case 16: 
			return 10;
		
		default: 
			return 14;
		}
	}

	static final int mul(int a, int b) {
		return (a != 0 && b != 0) ? alog[(log[a & 0xFF] + log[b & 0xFF]) % 255] : 0;
	}

	public byte[] encrypt(byte[] plaintext) {
		byte[] a = new byte[BLOCK_SIZE]; 
		byte[] ta = new byte[BLOCK_SIZE]; 
		byte[] Ker; 
		int i, j, k, row, col;

		Information = ""; 
		if (tracingLevel > 0)
			Information = "encryptAES(" + Util.toHEX1(plaintext) + ")"
						+"\n-----------------------------------------------------------";
	
		Ker = Ke[0];
		for (i = 0; i < BLOCK_SIZE; i++) {
			a[i] = (byte) (plaintext[i] ^ Ker[i]);
		}
		if (tracingLevel > 2) {
			Information += "\n  Round 0 (Key = " + Util.toHEX1(Ker) + ")\n\tAK = " + Util.toHEX1(a);
		} else if (tracingLevel > 1) {
			Information += "\n  Round 0 (Key\t\t= " + Util.toHEX1(Ker) + ")\n\t  (Output\t= " + Util.toHEX1(a) +")\n";
		}

		for (int r = 1; r < numberRound; r++) {
			Ker = Ke[r]; 
			if (tracingLevel > 1){
				Information += "\n  Round " + r + " (Key\t\t= " + Util.toHEX1(Ker) + ")\t";
			}
			
			for (i = 0; i < BLOCK_SIZE; i++)
				ta[i] = Sbox[a[i] & 0xFF];
			if (tracingLevel > 2) {
				Information += "\n\tSB = " + Util.toHEX1(ta);
			}

			for (i = 0; i < BLOCK_SIZE; i++) {
				row = i % COLUMN_SIZE;
				k = (i + (rowshift[row] * COLUMN_SIZE)) % BLOCK_SIZE; 
				a[i] = ta[k];
			}
			if (tracingLevel > 2) {
				Information += "\n\tSR = " + Util.toHEX1(a);
			}

			for (col = 0; col < NUMBER_COLUMNS; col++) {
				i = col * COLUMN_SIZE; 
				ta[i] = (byte) (mul(2, a[i]) ^ mul(3, a[i + 1]) ^ a[i + 2] ^ a[i + 3]);
				ta[i + 1] = (byte) (a[i] ^ mul(2, a[i + 1]) ^ mul(3, a[i + 2]) ^ a[i + 3]);
				ta[i + 2] = (byte) (a[i] ^ a[i + 1] ^ mul(2, a[i + 2]) ^ mul(3, a[i + 3]));
				ta[i + 3] = (byte) (mul(3, a[i]) ^ a[i + 1] ^ a[i + 2] ^ mul(2, a[i + 3]));
			}
			if (tracingLevel > 2)
				Information += "\n\tMC = " + Util.toHEX1(ta);

			for (i = 0; i < BLOCK_SIZE; i++)
				a[i] = (byte) (ta[i] ^ Ker[i]);
			if (tracingLevel > 2)
				Information += "\n\tAK";
			if (tracingLevel > 1)
				Information += "  \n\t  (Output\t= "+ Util.toHEX1(a) +") \n";
		}

		Ker = Ke[numberRound]; 
		if (tracingLevel > 1){
			Information += "\n  Round " + numberRound + " (Key\t\t= "+ Util.toHEX1(Ker) + ")\t";
		}
		
		for (i = 0; i < BLOCK_SIZE; i++)
			a[i] = Sbox[a[i] & 0xFF];
		if (tracingLevel > 2)
			Information += "\n\tSB = " + Util.toHEX1(a);

		for (i = 0; i < BLOCK_SIZE; i++) {
			row = i % COLUMN_SIZE;
			k = (i + (rowshift[row] * COLUMN_SIZE)) % BLOCK_SIZE; 
			ta[i] = a[k];
		}
		if (tracingLevel > 2)
			Information += "\n\tSR = " + Util.toHEX1(a);

		for (i = 0; i < BLOCK_SIZE; i++)
			a[i] = (byte) (ta[i] ^ Ker[i]);
		if (tracingLevel > 2){
			Information += "\n\tAK";
		}
		if (tracingLevel > 1){
			Information += " \n\t   (output\t= " + Util.toHEX1(a) + ") \n";
		}
		
		return (a);
	}

	public byte[] decrypt(byte[] ciphertext) {
		byte[] a = new byte[BLOCK_SIZE]; 
		byte[] ta = new byte[BLOCK_SIZE]; 
		byte[] Kdr; 
		int i, j, k, row, col;

		Information = ""; 
		if (tracingLevel > 0) {
			Information = "decryptAES(" + Util.toHEX1(ciphertext) + ")";
		}

		if (ciphertext == null) {
			throw new IllegalArgumentException("Empty ciphertext");
		}
		if (ciphertext.length != BLOCK_SIZE) {
			throw new IllegalArgumentException("Incorrect ciphertext length");
		}

		Kdr = Kd[0];
		for (i = 0; i < BLOCK_SIZE; i++)
			a[i] = (byte) (ciphertext[i] ^ Kdr[i]);
		if (tracingLevel > 2) {
			Information += "\n  R0 (Key = " + Util.toHEX1(Kdr) + ")\n\t AK = " + Util.toHEX1(a);
		} else if (tracingLevel > 1) {
			Information += "\n  R0 (Key = " + Util.toHEX1(Kdr) + ")\t = " + Util.toHEX1(a);
		}

		for (int r = 1; r < numberRound; r++) {
			Kdr = Kd[r]; 
			if (tracingLevel > 1) {
				Information += "\n  R" + r + " (Key = " + Util.toHEX1(Kdr) + ")\t";
			}

			for (i = 0; i < BLOCK_SIZE; i++) {
				row = i % COLUMN_SIZE;
				k = (i + BLOCK_SIZE - (rowshift[row] * COLUMN_SIZE)) % BLOCK_SIZE;
				ta[i] = a[k];
			}
			if (tracingLevel > 2) {
				Information += "\n\tISR = " + Util.toHEX1(ta);
			}

			for (i = 0; i < BLOCK_SIZE; i++)
				a[i] = Si[ta[i] & 0xFF];
			if (tracingLevel > 2) {
				Information += "\n\tISB = " + Util.toHEX1(a);
			}

			for (i = 0; i < BLOCK_SIZE; i++)
				ta[i] = (byte) (a[i] ^ Kdr[i]);
			if (tracingLevel > 2)
				Information += "\n\t AK = " + Util.toHEX1(ta);

			for (col = 0; col < NUMBER_COLUMNS; col++) {
				i = col * COLUMN_SIZE; 
				a[i] = (byte) (mul(0x0e, ta[i]) ^ mul(0x0b, ta[i + 1]) ^ mul(0x0d, ta[i + 2]) ^ mul(0x09, ta[i + 3]));
				a[i + 1] = (byte) (mul(0x09, ta[i]) ^ mul(0x0e, ta[i + 1]) ^ mul(0x0b, ta[i + 2])
						^ mul(0x0d, ta[i + 3]));
				a[i + 2] = (byte) (mul(0x0d, ta[i]) ^ mul(0x09, ta[i + 1]) ^ mul(0x0e, ta[i + 2])
						^ mul(0x0b, ta[i + 3]));
				a[i + 3] = (byte) (mul(0x0b, ta[i]) ^ mul(0x0d, ta[i + 1]) ^ mul(0x09, ta[i + 2])
						^ mul(0x0e, ta[i + 3]));
			}
			if (tracingLevel > 2) {
				Information += "\n\tIMC";
			}
			if (tracingLevel > 1) {
				Information += " = " + Util.toHEX1(a);
			}
		}

		Kdr = Kd[numberRound]; 
		if (tracingLevel > 1)
			Information += "\n  R" + numberRound + " (Key = " + Util.toHEX1(Kdr) + ")\t";

		for (i = 0; i < BLOCK_SIZE; i++) {
			row = i % COLUMN_SIZE;
			k = (i + BLOCK_SIZE - (rowshift[row] * COLUMN_SIZE)) % BLOCK_SIZE;
			ta[i] = a[k];
		}
		if (tracingLevel > 2)
			Information += "\n\tISR = " + Util.toHEX1(a);

		for (i = 0; i < BLOCK_SIZE; i++)
			ta[i] = Si[ta[i] & 0xFF];
		if (tracingLevel > 2)
			Information += "\n\tISB = " + Util.toHEX1(a);

		for (i = 0; i < BLOCK_SIZE; i++)
			a[i] = (byte) (ta[i] ^ Kdr[i]);
		if (tracingLevel > 2)
			Information += "\n\t AK";
		if (tracingLevel > 1)
			Information += " = " + Util.toHEX1(a) + "\n";
		if (tracingLevel > 0)
			Information += " = " + Util.toHEX1(a) + "\n";
		return (a);
	}

	
	public void setKey(byte[] key, byte[] plaintext) {

		final int BC = BLOCK_SIZE / 4;
		final int keylength = key.length;
		final int NumberKey = keylength / 4;

		int i, j, r;

		Information = ""; 
		if (tracingLevel > 0){
			Information = "----------------------------------------------------------\n"
					+ "Original Plaintext and Key:\n"+
					"Input\t : "+ Util.toHEX1(plaintext) +" \n"+
					 "Key\t : "+ Util.toHEX1(key) +" \n"+
					 "----------------------------------------------------------\n"+
					 "Key Schedule and Data Results for Each Round:\n"+
					 "----------------------------------------------------------\n";
		}
		numberRound = getRounds(keylength);
		final int RKEY_COUNT = (numberRound + 1) * BC;

		byte[] word0 = new byte[RKEY_COUNT];
		byte[] word1 = new byte[RKEY_COUNT];
		byte[] word2 = new byte[RKEY_COUNT];
		byte[] word3 = new byte[RKEY_COUNT];

		Ke = new byte[numberRound + 1][BLOCK_SIZE]; 
		Kd = new byte[numberRound + 1][BLOCK_SIZE]; 

		for (i = 0, j = 0; i < NumberKey; i++) {
			word0[i] = key[j++];
			word1[i] = key[j++];
			word2[i] = key[j++];
			word3[i] = key[j++];
		}

		byte temp0, temp1, temp2, temp3, old0; 

		for (i = NumberKey; i < RKEY_COUNT; i++) {
			temp0 = word0[i - 1];
			temp1 = word1[i - 1];
			temp2 = word2[i - 1];
			temp3 = word3[i - 1]; 
			if (i % NumberKey == 0) {
				old0 = temp0; 
				temp0 = (byte) (Sbox[temp1 & 0xFF] ^ constant[i / NumberKey]); 
				temp1 = (byte) (Sbox[temp2 & 0xFF]);
				temp2 = (byte) (Sbox[temp3 & 0xFF]); 
				temp3 = (byte) (Sbox[old0 & 0xFF]);
			} else if ((NumberKey > 6) && (i % NumberKey == 4)) {
				temp0 = Sbox[temp0 & 0xFF];
				temp1 = Sbox[temp1 & 0xFF];
				temp2 = Sbox[temp2 & 0xFF];
				temp3 = Sbox[temp3 & 0xFF];
			}
			word0[i] = (byte) (word0[i - NumberKey] ^ temp0);
			word1[i] = (byte) (word1[i - NumberKey] ^ temp1);
			word2[i] = (byte) (word2[i - NumberKey] ^ temp2);
			word3[i] = (byte) (word3[i - NumberKey] ^ temp3);
		}

		for (r = 0, i = 0; r < numberRound + 1; r++) { 
			for (j = 0; j < BC; j++) { 
				Ke[r][4 * j] = word0[i];
				Ke[r][4 * j + 1] = word1[i];
				Ke[r][4 * j + 2] = word2[i];
				Ke[r][4 * j + 3] = word3[i];
				Kd[numberRound - r][4 * j] = word0[i];
				Kd[numberRound - r][4 * j + 1] = word1[i];
				Kd[numberRound - r][4 * j + 2] = word2[i];
				Kd[numberRound - r][4 * j + 3] = word3[i];
				i++;
			}
		}

		if (tracingLevel > 3) {
			Information += "  Encryption Round keys:\n";
			for (r = 0; r < numberRound + 1; r++)
				Information += "  Round" + r + "\t = " + Util.toHEX1(Ke[r]) + "\n";
			Information += "  Decryption Round keys:\n";
			for (r = 0; r < numberRound + 1; r++)
				Information += "  Round" + r + "\t = " + Util.toHEX1(Kd[r]) + "\n";
		}
	}

	
	public static void test_manual(String hkey, String hplaintext, String hcipher, int lev) {

		byte[] key = Util.hex2byte(hkey);
		byte[] plaintext = Util.hex2byte(hplaintext);
		byte[] ciphertext = Util.hex2byte(hcipher);
		byte[] answer;

		AES testingAES = new AES(); 
		testingAES.tracingLevel = lev; 
		testingAES.setKey(key,plaintext); 
		System.out.print(testingAES.Information);

		answer = testingAES.encrypt(plaintext);
		System.out.print(testingAES.Information);
		if (Arrays.equals(answer, ciphertext))
			System.out.print("Testing success\n");
		else
			System.out.print("Testing Failed. Your answer was " + Util.toHEX(answer) + "\n");

		/*answer = testingAES.decrypt(ciphertext); 
		System.out.print(testingAES.Information);
		if (Arrays.equals(answer, plaintext))
			System.out.print("Testing success\n");
		else
			System.out.print("Testing Failed. Answer was " + Util.toHEX(answer) + "\n");
		System.out.println();*/
	}

	public static void main(String[] args) {
		int lev = 2;

		switch (args.length) {
		case 0:
			break;
		case 1:
			lev = Integer.parseInt(args[0]);
			break;
		case 3:
			test_manual(args[0], args[1], args[2], lev);
			System.exit(0);
			break;
		case 4:
			lev = Integer.parseInt(args[3]);
			test_manual(args[0], args[1], args[2], lev);
			System.exit(0);
			break;
		default:
			System.exit(0);
		}
		
	  test_manual("000102030405060708090a0b0c0d0e0f",
		  "00112233445566778899aabbccddeeff",
		  "69c4e0d86a7b0430d8cdb78070b4c55a", lev);
	}

}
