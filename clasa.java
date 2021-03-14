package pachet;

import java.io.*;
import java.util.*;

enum Stare
{
	achizitionat,
	expus,
	vandut
}

enum Mod_de_Scriere
{
	Color,
	AlbNegru
}

enum Format
{
	A4,
	A3
}

enum Sistem_de_Operare
{
	Windows,
	Linux
}

class Echipament implements Serializable
{
	private String denumire;
	private int nr_inv;
	private int pret;
	private String zona_mag;
	Stare stare;
	
	public Echipament(String d,int n_i,int p,String z_m,Stare s)
	{
		denumire = d;
		nr_inv = n_i;
		pret = p;
		zona_mag = z_m;
		stare = s;
	}
	
	public String toString()
	{
		return denumire + "; "+ nr_inv + "; "+ pret + "; "+zona_mag+"; "+stare+"";
	}
	
	public void setStareN(Stare sn) {this.stare = sn;}
	
	public Stare getStare() {return stare;}
	public String getDen() {return denumire;}
}

class Imprimante extends Echipament implements Serializable
{
	private int ppm;
	private String rezolutie;
	private int p_car;
	Mod_de_Scriere scris;
	
	public Imprimante(String d,int n_i,int p,String z_m,Stare s,int ppm,String rez,int pcar,Mod_de_Scriere scris)
	{
		super(d,n_i,p,z_m,s);
		this.ppm = ppm;
		rezolutie = rez;
		p_car = pcar;
		this.scris = scris;
	}
	
	public void setMS(Mod_de_Scriere ms) {this.scris = ms;}
	
	public String toString()
	{
		return super.toString() +"; "+ppm+"; "+rezolutie+"; "+p_car+"; "+scris+"";
	}
}

class Copiatoare extends Echipament implements Serializable
{
	private int ppm;
	private int p_ton;
	Format format;
	
	public Copiatoare(String d,int n_i,int p,String z_m,Stare s,int ppm,int pton,Format format)
	{
		super(d,n_i,p,z_m,s);
		this.ppm = ppm;
		p_ton = pton;
		this.format = format;
	}
	
	public void setF(Format format) {this.format = format;}
	
	public String toString()
	{
		return super.toString() +"; "+ppm+"; "+p_ton+"; "+format+"";
	}
}

class Sisteme_de_Calcul extends Echipament implements Serializable
{
	private String tip_mon;
	private float vit_proc;
	private int c_hdd;
	Sistem_de_Operare sistem;
	
	public Sisteme_de_Calcul(String d,int n_i,int p,String z_m,Stare s,String tipmon,float vitproc,int chdd,Sistem_de_Operare sistem)
	{
		super(d,n_i,p,z_m,s);
		tip_mon = tipmon;
		vit_proc = vitproc;
		c_hdd = chdd;
		this.sistem = sistem;
	}
	
	public void setSO(Sistem_de_Operare so) {this.sistem = so;}
	
	public String toString()
	{
		return super.toString() +"; "+tip_mon+"; "+vit_proc+"; "+c_hdd+"; "+sistem+"";
	}
}

public class clasa {

	public static Echipament[] Adauga(int n,Echipament e[],Echipament aux)
	{
		Echipament temp[] = new Echipament[n+1];
		
		for(int i=0;i<n;i++)
		{
			temp[i] = e[i];
		}
		temp[n] = aux;

		return temp;
	}
	
	static void scrie(Object o,String fis)
	{
		try {
			FileOutputStream f = new FileOutputStream(fis);
			ObjectOutputStream oos = new ObjectOutputStream(f);
			oos.writeObject(o);
			oos.close();
			f.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	static Object citeste(String fis)
	{
		try {
			FileInputStream f = new FileInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(f);
			Object o = ois.readObject();
			ois.close();
			f.close();
			return o;
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader flux_in = new BufferedReader(new FileReader("in.txt"));
		Scanner in = new Scanner(System.in);
		String linie;
		String[] s;
		boolean prim_el = true;
		Echipament[] e = new Echipament[1];
		Echipament aux;
		int opt=0;
		
		while((linie=flux_in.readLine())!=null)
		{
			s = linie.split(";");
			Stare S = Stare.valueOf(s[4]);
			
			if(s[5].contains("imprimanta"))
			{
				Mod_de_Scriere MS = Mod_de_Scriere.valueOf(s[9]);
				Imprimante imp = new Imprimante(s[0],Integer.parseInt(s[1]),Integer.parseInt(s[2]),s[3],S,Integer.parseInt(s[6]),s[7],Integer.parseInt(s[8]),MS);
				aux = imp;
				if(prim_el == true)
				{
					e[0] = aux;
					prim_el = false;
					continue;
				}
				e = Adauga(e.length,e,aux);
			}
			if(s[5].contains("copiator"))
			{
				Format F = Format.valueOf(s[8]);
				Copiatoare cop = new Copiatoare(s[0],Integer.parseInt(s[1]),Integer.parseInt(s[2]),s[3],S,Integer.parseInt(s[6]),Integer.parseInt(s[7]),F);
				aux = cop;
				if(prim_el == true)
				{
					e[0] = aux;
					prim_el = false;
					continue;
				}
				e = Adauga(e.length,e,aux);
			}
			if(s[5].contains("sistem de calcul"))
			{
				Sistem_de_Operare SO = Sistem_de_Operare.valueOf(s[9]);
				Sisteme_de_Calcul sc = new Sisteme_de_Calcul(s[0],Integer.parseInt(s[1]),Integer.parseInt(s[2]),s[3],S,s[6],Float.parseFloat(s[7]),Integer.parseInt(s[8]),SO);
				aux = sc;
				if(prim_el == true)
				{
					e[0] = aux;
					prim_el = false;
					continue;
				}
				e = Adauga(e.length,e,aux);
			}
		}
		
		do
		{
			System.out.println("0.Iesire.");
			System.out.println("1.Afisarea imprimantelor.");
			System.out.println("2.Afisarea copiatoarelor.");
			System.out.println("3.Afisarea sistemelor de calcul.");
			System.out.println("4.Modificarea starii in care se afla un echipament.");
			System.out.println("5.Setarea unui anumit mod de scriere pentru o imprimanta.");
			System.out.println("6.Setarea unui format de copiere pentru copiatoare.");
			System.out.println("7.Instalarea unui anumit sistem de operare pe un sistem de calcul.");
			System.out.println("8.Afisarea echipamentelor vandute.");
			System.out.println("9.Serializarea/Deserializarea colectiei de obiecte.");
			System.out.print("Dati optiunea: ");
			opt = in.nextInt();System.out.println();
			
			switch(opt)
			{
			case 0:break;
			case 1:for(int i=0;i<e.length;i++)
			{
				if(e[i] instanceof Imprimante)
				{
					System.out.println(e[i].toString());
				}
			}
			System.out.println();
				break;
			case 2:for(int i=0;i<e.length;i++)
			{
				if(e[i] instanceof Copiatoare)
				{
					System.out.println(e[i].toString());
				}
			}
			System.out.println();
				break;
			case 3:for(int i=0;i<e.length;i++)
			{
				if(e[i] instanceof Sisteme_de_Calcul)
				{
					System.out.println(e[i].toString());
				}
			}
			System.out.println();
				break;
			case 4:{
			String cauta, stare;
			Stare sn;
			BufferedReader in_temp = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Dati nume echipament: "); cauta = in_temp.readLine();
			System.out.print("Dati stare noua: "); stare = in_temp.readLine(); System.out.println();
			sn = Stare.valueOf(stare);
			for(int i=0;i<e.length;i++)
			{
				if(cauta.contains(e[i].getDen()))
				{
					if(stare.contains("achizitionat"))
					{
						e[i].setStareN(sn);
					}
					if(stare.contains("expus"))
					{
						e[i].setStareN(sn);
					}
					if(stare.contains("vandut"))
					{
						e[i].setStareN(sn);
					}
				}
			}
			}
				break;
			case 5:
			{Imprimante imp;
			Mod_de_Scriere ms;
			String cauta,mod_de_scriere;
			BufferedReader in_temp = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Dati nume imprimanta: "); cauta = in_temp.readLine();
			System.out.print("Dati mod de scriere nou: "); mod_de_scriere = in_temp.readLine(); System.out.println();
			ms = Mod_de_Scriere.valueOf(mod_de_scriere);
			for(int i=0;i<e.length;i++)
			{
				if(cauta.contains(e[i].getDen()) && e[i] instanceof Imprimante)
				{
					if(mod_de_scriere.contains("Color"))
					{
						imp = (Imprimante)e[i];
						imp.setMS(ms);
						e[i] = imp;
					}
					if(mod_de_scriere.contains("AlbNegru"))
					{
						imp = (Imprimante)e[i];
						imp.setMS(ms);
						e[i] = imp;
					}
				}
			}
			}
				break;
			case 6:
			{
				Copiatoare cop;
				String format;
				Format f;
				BufferedReader in_temp = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Dati format nou: "); format = in_temp.readLine(); System.out.println();
				f = Format.valueOf(format);
				for(int i=0;i<e.length;i++)
				{
					if(e[i] instanceof Copiatoare)
					{
						if(format.contains("A4"))
						{
							cop = (Copiatoare)e[i];
							cop.setF(f);
							e[i] = cop;
						}
						if(format.contains("A3"))
						{
							cop = (Copiatoare)e[i];
							cop.setF(f);
							e[i] = cop;
						}
					}
				}
			}
				break;
			case 7:
			{
				Sisteme_de_Calcul sc;
				String cauta,sistem_operare;
				Sistem_de_Operare so;
				BufferedReader in_temp = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Dati nume sistem de calcul: "); cauta = in_temp.readLine();
				System.out.print("Dati sistem de operare nou: "); sistem_operare = in_temp.readLine(); System.out.println();
				so = Sistem_de_Operare.valueOf(sistem_operare);
				for(int i=0;i<e.length;i++)
				{
					if(cauta.contains(e[i].getDen()) && e[i] instanceof Sisteme_de_Calcul)
					{
						if(sistem_operare.contains("Windows"))
						{
							sc = (Sisteme_de_Calcul)e[i];
							sc.setSO(so);
							e[i] = sc;
						}
						if(sistem_operare.contains("Linux"))
						{
							sc = (Sisteme_de_Calcul)e[i];
							sc.setSO(so);
							e[i] = sc;
						}
					}
				}
			}
				break;
			case 8:for(int i=0;i<e.length;i++)
			{
				if(e[i].getStare().compareTo(Stare.vandut) == 0)
				{
					System.out.println(e[i].toString());
				}
			}
			System.out.println();
				break;
			case 9:
			{
				String sel;
				BufferedReader in_temp = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("a.Serializarea colectiei de obiecte.");
				System.out.println("b.Deserializarea colectiei de obiecte.");
				System.out.print("Dati optiunea: "); sel = in_temp.readLine(); System.out.println();
				
				switch(sel)
				{
				case "a": scrie(e,"echipamente.bin");
					break;
				case "b":Echipament[] echipamente_bin;
				echipamente_bin = (Echipament[])citeste("echipamente.bin");
				for(Echipament eq:echipamente_bin)
				{
					System.out.println(eq);
				}
				System.out.println();
					break;
				default:System.out.println("Optiune gresita !");
					break;
				}
			}
				break;
			default:System.out.println("Optiune gresita !");
				break;
			}
		}while(opt!=0);
		
		flux_in.close();
		in.close();
	}
}