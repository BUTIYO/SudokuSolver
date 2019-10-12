import java.io.*;
import java.util.*;

public class Suudoku{

	Suudoku(int[][] data){
		solve(data);
	}

	public void solve(int[][] data) {
		if (isAllFilled(data) == true) {
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
						System.out.print(" " + data[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}

		for (int i=0; i<9; i++) {
		for (int j=0; j<9; j++) {
			if (data[i][j] == 0) {
				for (int k=1; k<10; k++) {
					data[i][j] = k;
					if (lowCheck(data, i, k) && colCheck(data, j, k)
																   && blCheck(data, i, j, k)) solve(data);
					data[i][j] = 0;
				}
				if (data[i][j] == 0) return;
			}
		}
		}
	}


	public boolean lowCheck(int[][] data, int i, int k) {
		int count=0;
			for (int j=0; j<9; j++) {
				if (data[i][j] == k) count++;
			}
			if (count == 1) return true;
		return false;
	}

	public boolean colCheck(int[][] data, int j, int k) {
		int count=0;
		for (int i=0; i<9; i++) {
			if (data[i][j] == k) count++;
		}
		if (count == 1) return true;
		return false;
	}

	public boolean blCheck(int[][] data, int i, int j, int k) {
		int count = 0;
		for (int l=0; l<3; l++) {
			for (int m=0; m<3; m++) {
				if(data[l+(i/3)*3][m+(j/3)*3] == k) count ++;
			}
		}
		if (count == 1) return true;
		return false;
	}

	public boolean isAllFilled(int[][] data) {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (data[i][j] == 0) return false;
			}
		}
		return true;
	}


	public static void main(String[] args){
		// データの読み込み
		int[][] data = new int[9][9];
		if(args.length!=1){
			System.err.println("need 1 argument");
			System.exit(1);	// 異常終了
		}

		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader(args[0]));
			for(int i=0;i<9;i++){
				String tmp = br.readLine();
				StringTokenizer stn = new StringTokenizer(tmp," ",false);
					for(int j=0;j<9;j++){
					data[i][j]=Integer.parseInt(stn.nextToken());
				}
			}
		} catch(Exception e) {
			System.err.println(""+e);
			System.exit(1);	// 異常終了
		}

		// 問題表示
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(data[i][j]==0){
					System.out.print("( )");
				}else{
					System.out.print(" " + data[i][j] + " ");
				}
			}
			System.out.println();
		}

		new Suudoku(data);
}
}
