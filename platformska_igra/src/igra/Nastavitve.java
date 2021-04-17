package igra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Nastavitve {
	
	private final String pot = "res\\Nastavitve.txt";
	
	private double glasnost;
	private int jezik;
	
	private Igra igra;
	
	public Nastavitve(Igra igra) throws IOException {
		this.igra = igra;
		
		BufferedReader br = new BufferedReader(new FileReader(pot));
		
		String [] niz = br.readLine().split(":");
		glasnost = Double.parseDouble(niz[1]);
		
		niz = br.readLine().split(":");
		jezik = Integer.parseInt(niz[1]);
		
		br.close();
		
	}
	
	public void shraniGlasnost(double glasnost) throws IOException {
		this.glasnost = glasnost;
		this.igra.getZvok().setGlasnost(glasnost);
		shraniDatoteko();
	}
	
	public void shraniJezik(int jezik) throws IOException {
		this.jezik = jezik;
		this.igra.getJezik().setJezik(jezik);
		shraniDatoteko();
	}
	
	
	public void shraniDatoteko() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(pot));
		bw.write("Glasnost:"+glasnost);
		bw.newLine();
		bw.write("Jezik:"+jezik);
		bw.close();
	}
	
	public double getGlasnost() {
		return glasnost;
	}
	
	public int getJezik() {
		return jezik;
	}

}
