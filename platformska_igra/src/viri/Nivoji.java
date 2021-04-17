package viri;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Nivoji {
	
	private String [] pot = {
			"res\\Nivoji\\svet1.csv",
			"res\\Nivoji\\svet2.csv",	
			"res\\Nivoji\\svet3.csv",
			"res\\Nivoji\\svet4.csv",
			"res\\Nivoji\\svet5.csv",
			"res\\Nivoji\\svet6.csv",
			"res\\Nivoji\\svet7.csv",
			"res\\Nivoji\\svet8.csv",
			"res\\Nivoji\\svet9.csv",
			"res\\Nivoji\\svet10.csv"
	};
	
	private ArrayList<Integer[][]> nivoji = new ArrayList<Integer[][]>();
	private ArrayList<Integer> igralecX = new ArrayList<Integer>();
	private ArrayList<Integer> igralecY = new ArrayList<Integer>();
	
	
	public Nivoji() throws NumberFormatException, IOException {
		for(int z=0; z<pot.length; z++) {
			ArrayList<ArrayList<Integer>> datoteka = new ArrayList<ArrayList<Integer>>();
			String niz = null;
			BufferedReader br = new BufferedReader(new FileReader(pot[z]));
			
			//v prvi vrstici sta zapisani startni koordinati
			niz = br.readLine();
			String [] vrstica0 = niz.split(";");
			igralecX.add(Integer.parseInt(vrstica0[0]));
			igralecY.add(Integer.parseInt(vrstica0[1]));
			
			while((niz = br.readLine())!=null) {
				String [] vrstica_String = niz.split(";");
				if(vrstica_String.length == 0)
					break;
				ArrayList<Integer> vrstica = new ArrayList<Integer>();
				for(int i=0; i<vrstica_String.length; i++) {
					vrstica.add(Integer.parseInt(vrstica_String[i]));
				}
				datoteka.add(vrstica);
			}
			br.close();
			
			//izraèunamo število vrstic in stolpcev
			int yVelikost = datoteka.size();
			int xVelikost = 0;
			for(int i=0; i<datoteka.size(); i++) {
				if(datoteka.get(i).size()>xVelikost)
					xVelikost = datoteka.get(i).size();
			}
			
			Integer [][] nivo = new Integer[xVelikost][yVelikost];
			for(int x=0; x<xVelikost; x++) {
				for(int y=0; y<yVelikost; y++) {
					nivo[x][y] = datoteka.get(y).get(x);
				}
			}
			nivoji.add(nivo);
		}
		
	}
	public Integer[][] getNivo(int index){
		return nivoji.get(index);
	}
	public int getIgralecX(int index) {
		return igralecX.get(index);
	}
	public int getIgralecY(int index) {
		return igralecY.get(index);
	}
	
	public int getStNivojev() {
		return nivoji.size();
	}
}
