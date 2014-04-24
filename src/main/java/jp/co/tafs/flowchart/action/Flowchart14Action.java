package jp.co.tafs.flowchart.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Flowchart14Action {
	public static void main(String[] args) {

		//入力チェック
		DateFormat sdf = new SimpleDateFormat("yyyy/MM");
		sdf.setLenient(false);

		try {
			sdf.parse(args[0]);
		} catch (ParseException e) {
			System.out.println("入力が正しくありません");
			return;
		}

		String[] date = args[0].split("\\/");

		if (date.length >= 3) {
			System.out.println("入力が正しくありません");
			return;
		}
		if (date[1].length() >= 3) {
			System.out.println("入力が正しくありません");
			return;
		}

		//日数計算
		int year = Integer.valueOf(date[0]);
		int mon = Integer.valueOf(date[1]);
		int dSum = 0;

		dSum = change(year, mon);

		//営業日の特定
		int[] end = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		ArrayList<Integer> bDay = new ArrayList<Integer>();
		if (mon == 2) {
			if (year % 4 == 0) {
				if (year % 100 == 0) {
					if (year % 400 == 0) {
						end[mon - 1] = end[mon - 1] + 1;
					}
				} else {
					end[mon - 1] = end[mon - 1] + 1;
				}
			}
		}
		bDay = businessDay(end[mon - 1], dSum);

		//表示
		System.out.println(args[0] + "の営業日は");
		for (int i = 0; i < bDay.size(); i++) {
			System.out.print(bDay.get(i) + "日 ");
		}
	}

	//日数計算の処理
	public static int change(int year, int mon) {

		if (mon < 3) {
			year = year - 1;
			mon = mon + 12;
		}

		int ySum = year + (year / 4) - (year / 100) + (year / 400);
		int mSum = (int) ((2.6 * mon) + 1.6) + 1;
		int dSum = ySum + mSum;

		return dSum;
	}

	//営業日特定の処理
	public static ArrayList<Integer> businessDay(int end, int dSum) {
		int e = 0;
		ArrayList<Integer> bDay = new ArrayList<Integer>();
		for (int i = 1; i < end + 1; i++) {
			if (dSum % 7 != 0 && dSum % 7 != 6) {
				bDay.add(i);
				e = e + 1;
			}
			dSum = dSum + 1;
		}
		return bDay;
	}
}