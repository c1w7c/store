package Store;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
	static List<Store> articles = new ArrayList<>(); 
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int inputNum=0;
		showMenu();
		inputDataToStore();
		while((inputNum = input()) != 7) {
			switch(inputNum) {
				case Action.Show :
					show();
					//showMenu();
					break;
				case Action.Add :
					add();
					//showMenu();
					break;
				case Action.Modify:
					modify();
					//showMenu();
					break;
				case Action.Store:
					store();
					//showMenu();
					break;
				case Action.Purchase:
					purchase();
					break;
			}
			showMenu();
		}
	}
	
	public static int input() {
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	public static String inputStr() {
		Scanner input = new Scanner(System.in);
		return input.next();
	}
	public static void out(String str) {
		System.out.printf("%s\n",str);
	}
	
 	public static void showMenu() {
		System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s","*******吳家商店*******",
				"(1)顯示所有資料","(2)增加商品資料","(3)修改商品資料","(4)進貨資料登陸","(5)出貨資料登錄","(6)儲存資料"
				,"請輸入選項\n");
	}
 	
	public static void inputDataToStore() throws Exception { 
		//List<Store> temp = new ArrayList<>();
		StringBuilder s = new StringBuilder();
		String[] storeStr; 
		try(InputStreamReader ip = new InputStreamReader(new FileInputStream("elements.txt"))) {
			while(ip.ready()) {
				s .append(String.valueOf((char)ip.read()));
			}
			storeStr = s.toString().split("\n");
			String[] item; ;
			for(String str : storeStr) {
				item = str.split(" ");
				articles.add(new Store(item[0],item[1],Integer.parseInt(item[2]),item[3],
							           Integer.parseInt(item[4]),item[5]));
			}
		}
		
	}
	
	public static void show() throws Exception {
		System.out.printf("代號\t品名\t單價\t單位\t安全庫存量\t製造商\n");
		//inputDataToStore();
		for(Store s : articles) {
			System.out.printf("%s\t%s\t%d\t%s\t%d\t%s\n", s.getID(),s.getName(),
														  s.getPrice(),s.getUnits(),
														  s.getStock(),s.getManufacturer());
		}
	}
	
	public static void add() throws Exception {
		Store store = new Store();
		out("請輸入商品編號");store.setID(inputStr());
		out("請輸入商品名稱");store.setName(inputStr());
		out("請輸入商品單價");store.setPrice(input());
		out("請輸入商品單位");store.setUnits(inputStr());
		out("請輸入安全庫存量");store.setStock(input());
		out("請輸入製造商");store.setManufacturer(inputStr());
		articles.add(store);
	}
	
	public static void store() throws Exception {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("elements.txt"))){
			for(Store s : articles) {
			bw.write(s.getID() + " "+ s.getName() + " " + s.getPrice() + " " +
					 s.getUnits() + " " + s.getStock() + " " + s.getManufacturer()+"\n");
			}
			
		}
		
	}

	public static void modify() throws Exception{
		out("請輸入修改代號列號碼 ex 第一列輸入1");
		int row = input();
		out("輸入修改選項 (1)代號 (2)品名..");
		int item = input();
		out("更改為:");
		switch(item) {
			case 1 : articles.get(row-1).setID(inputStr());break;
			case 2 : articles.get(row-1).setName(inputStr());break;
			case 3 : articles.get(row-1).setPrice(input());break;
			case 4 : articles.get(row-1).setUnits(inputStr());break;
			case 5 : articles.get(row-1).setStock(input());break;
			case 6 : articles.get(row-1).setManufacturer(inputStr());break;
			default : 
				out("輸入錯誤");
		}
	}

	public static void purchase() throws Exception{
		int countLine = 0;
		for(int i=0;i<articles.size();i++) {
			if(countLine % 3 != 0 || countLine == 0)
				System.out.printf("(%d)%s\t  %d\t", i+1,articles.get(i).getName(),
											  articles.get(i).getStock());
			else
				System.out.printf("\n(%d)%s\t  %d\t", i+1,articles.get(i).getName(),
						  articles.get(i).getStock());
			countLine++;
		}
		System.out.println();
		int inputNum=0;
		out("輸入進貨項目");
		while((inputNum=input()) != 0) {
			out("輸入進貨數量");
			articles.get(inputNum-1).setStock((
			articles.get(inputNum-1).getStock() + input()));
			out("輸入進貨項目");
		}
		
	}
}
