package viri;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Slike {
	
	public static BufferedImage ozadje;
	public static BufferedImage ozadje_nastavitve;
	public static BufferedImage ozadje_meni;
	public static BufferedImage [] pomoc = new BufferedImage[3];

	public static BufferedImage opeka;
	public static BufferedImage spica1;
	public static BufferedImage spica2;
	public static BufferedImage spica3;
	public static BufferedImage spica4;
	public static BufferedImage spica5;
	public static BufferedImage spica6;
	public static BufferedImage spica7;
	public static BufferedImage spica8;
	
	public static BufferedImage [] mirovanjeD = new BufferedImage[2];
	public static BufferedImage [] mirovanjeL = new BufferedImage[2];
	public static BufferedImage [] hojaD = new BufferedImage[4];
	public static BufferedImage [] hojaL = new BufferedImage[4];
	public static BufferedImage [] skokD = new BufferedImage[1];
	public static BufferedImage [] skokL = new BufferedImage[1];
	
	public static BufferedImage [] gumb = new BufferedImage[2];
	public static BufferedImage [] gumb_kvadrat = new BufferedImage[2];
	public static BufferedImage [] spustni_meni = new BufferedImage[2];
	
	public Slike(){
		try {
			Slike.pomoc[0] = ImageIO.read(new File("res\\Slike\\pomoc_slo.png"));
			Slike.pomoc[1] = ImageIO.read(new File("res\\Slike\\pomoc_eng.png"));
			Slike.pomoc[2] = ImageIO.read(new File("res\\Slike\\pomoc_deu.png"));
			
			Slike.ozadje = ImageIO.read(new File("res\\Slike\\ozadje.png"));
			Slike.ozadje_meni = ImageIO.read(new File("res\\Slike\\ozadje_meni.png"));
			Slike.ozadje_nastavitve = ImageIO.read(new File("res\\Slike\\ozadje_nastavitve.png"));
			//kocke
			Slike.opeka = ImageIO.read(new File("res\\Slike\\opeka.png"));
			Slike.spica1 = ImageIO.read(new File("res\\Slike\\spica1.png"));
			Slike.spica2 = ImageIO.read(new File("res\\Slike\\spica2.png"));
			Slike.spica3 = ImageIO.read(new File("res\\Slike\\spica3.png"));
			Slike.spica4 = ImageIO.read(new File("res\\Slike\\spica4.png"));
			Slike.spica5 = ImageIO.read(new File("res\\Slike\\spica5.png"));
			Slike.spica6 = ImageIO.read(new File("res\\Slike\\spica6.png"));
			Slike.spica7 = ImageIO.read(new File("res\\Slike\\spica7.png"));
			Slike.spica8 = ImageIO.read(new File("res\\Slike\\spica8.png"));
			
			//igralec
			Slike.mirovanjeD[0] = ImageIO.read(new File("res\\Slike\\igralec_miruje1.png"));
			Slike.mirovanjeD[1] = ImageIO.read(new File("res\\Slike\\igralec_miruje2.png"));
			Slike.hojaD[0] = ImageIO.read(new File("res\\Slike\\igralec_hodi_desno1.png"));
			Slike.hojaD[1] = ImageIO.read(new File("res\\Slike\\igralec_miruje1.png"));
			Slike.hojaD[2] = ImageIO.read(new File("res\\Slike\\igralec_hodi_desno2.png"));
			Slike.hojaD[3] = ImageIO.read(new File("res\\Slike\\igralec_miruje1.png"));
			
			Slike.mirovanjeL[0] = ImageIO.read(new File("res\\Slike\\igralec_miruje_levo1.png"));
			Slike.mirovanjeL[1] = ImageIO.read(new File("res\\Slike\\igralec_miruje_levo2.png"));
			Slike.hojaL[0] = ImageIO.read(new File("res\\Slike\\igralec_hodi_levo1.png"));
			Slike.hojaL[1] = ImageIO.read(new File("res\\Slike\\igralec_miruje_levo1.png"));
			Slike.hojaL[2] = ImageIO.read(new File("res\\Slike\\igralec_hodi_levo2.png"));
			Slike.hojaL[3] = ImageIO.read(new File("res\\Slike\\igralec_miruje_levo1.png"));
			
			Slike.skokD[0] = ImageIO.read(new File("res\\Slike\\igralec_skok_desno.png"));
			Slike.skokL[0] = ImageIO.read(new File("res\\Slike\\igralec_skok_levo.png"));
			
			//meniji
			Slike.gumb[0] = ImageIO.read(new File("res\\Slike\\gumb1.png"));
			Slike.gumb[1] = ImageIO.read(new File("res\\Slike\\gumb2.png"));
			Slike.gumb_kvadrat[0] = ImageIO.read(new File("res\\Slike\\gumb3.png"));
			Slike.gumb_kvadrat[1] = ImageIO.read(new File("res\\Slike\\gumb4.png"));
			Slike.spustni_meni[0] = ImageIO.read(new File("res\\Slike\\spustni_meni1.png"));
			Slike.spustni_meni[1] = ImageIO.read(new File("res\\Slike\\spustni_meni2.png"));
			
			
			
		} catch (IOException e) {
			System.out.println("Napaka pri nalaganju slik");
			e.printStackTrace();
		}
	}
	
}
