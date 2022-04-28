/////Szakdolgozat   Dinnyes Balazs Emil Programtervezo informatikus MSC DIBMAAT.SZE  kelt. 2013 05. 11.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import javax.swing.text.*;
import java.lang.*;
import java.io.*;
import javax.swing.filechooser.FileFilter;

class	JavaFilter extends FileFilter
{
	public boolean accept(File f)
	{
		return f.getName().toUpperCase().endsWith(".TXT") || f.isDirectory();
	}

	public String getDescription()
	{
		return "txt-allomamyok";
	}
}//end JavaFilter
class MunkaExp extends Thread{


	int j;
	int n;
	ListIterator iter0;
	ListIterator iter1;
	int[] kapcsolatitomb;
	int lengh;
	int [] kulcs;
	String strcsucsok,strcsucsokord;
	int csucsmegjegyezkezdo;
	LinkedList list0 = new LinkedList();
	LinkedList list1 = new LinkedList();
	int partuthossz;
	String name3;
	int [] sokas;
	int melikazz;
	int tdk;

	public MunkaExp(int [] kapcsolatitomb,int n,String name3,int melikazz)

	//kapcsolatitomb: a munkák matrixa, n: páros munkak szama, name3: ha file-bol megnyitott adatokkal dolgozik a program, a file neve, amit tovabbad a grafikus megjelenitesnek
	{
		this.kapcsolatitomb=kapcsolatitomb;
		this.n=n;
		this.name3=name3;
		kulcs=new int[n];
		strcsucsok="";strcsucsokord="";
		this.melikazz=melikazz;
		partuthossz=0;
		sokas= new int[n*60];
	}

	public void dinkiscsucsmake(String strcsucsok)
	{
		int csucsmegjegyez=csucsmegjegyezkezdo;
		do
		{
			StringTokenizer st = new StringTokenizer(strcsucsok," ");
			boolean bool=false;
			if( csucsmegjegyez>=n)
			{
				csucsmegjegyezkezdo=n+1;
				return;
			}
			while(st.hasMoreTokens())
				{
					String sh=st.nextToken();
					String string=String.valueOf(sh);
					int kezdointcsucs=Integer.parseInt(string);
					if(kezdointcsucs==csucsmegjegyez)
					{
						 csucsmegjegyez++;bool=true;
					}
					if((kezdointcsucs==n-1) && (csucsmegjegyez==n))
						 return;
				 }
				 if(!bool)break;
				 	else if(csucsmegjegyez==n)break;
			}while(true );

			String past=String.valueOf(csucsmegjegyez);
			strcsucsok=strcsucsok.concat(" "+past+" ");
			csucsmegjegyezkezdo=csucsmegjegyez;
			rendez(strcsucsok,past);
	}//end dinkiscsucsmake()
public void dinkiscsucspast(String strcsucsok,String strcsucsokord)
	{
				list1.addLast(strcsucsokord);
				list1.addLast(new Integer(partuthossz));
				list1.addLast(strcsucsok);
	}//end dinkiscsucspast


	public void cserel(int i,int k,int[]kulcs)
	{
		if(i<0 || i>=lengh || k<0 || k>=lengh || i==k) return;
			int l=kulcs[i];
			kulcs[i]=kulcs[k];
			kulcs[k]=l;
	}//end cserel

	public void legkisebb(int i,int[]kulcs)
	{
		if(i<0 || i>=lengh)
		{
			 return;
		}
		for(int k=i+1;k<lengh;k++) {
			if(kulcs[i]>kulcs[k]) cserel(i,k,kulcs);
		}
		legkisebb(i+1,kulcs);
	}//end legkisebb

	public void rendez(String strcsucsok,String utolso)
	{
		int u=0;
		StringTokenizer stv = new StringTokenizer(strcsucsok," ");
			while(stv.hasMoreTokens())
			{
				kulcs[u]=Integer.valueOf(stv.nextToken());
				u++;
			}
		legkisebb(0,kulcs);
		String strcsucsokord="";
		for(int i=0;i<lengh;i++)
	    	strcsucsokord=strcsucsokord.concat(String.valueOf(kulcs[i])+" ");
		strcsucsokord=strcsucsokord.concat(utolso);
		if(melikazz!=5)
				reszuthossz(strcsucsok,strcsucsokord);
		else
			reszuthossz4(strcsucsok,strcsucsokord);
	}//end rendez
	
	public int vegsoreszuthossz4(String strcsucsokvegso)
	{
		boolean nemjo=false;
		for(int i=0;i<n*60;i++){sokas[i]=0;}
		partuthossz=0;
	 	StringTokenizer stz = new StringTokenizer(strcsucsokvegso," ");
 		int hi=0;
 		int hj=0;
 		int hk=0;
 		int ei=0;
 		int ek=0;
 		int e1mun=0;
 		int szun=0;
 		int m2mun=0;
 		int strtotomb1=0;
 		int part1=0;
 		boolean enged = false;
	 	while(stz.hasMoreTokens())
		{
	 		strtotomb1=Integer.valueOf(stz.nextToken());
	 		e1mun=kapcsolatitomb[strtotomb1*3];
	 		szun=kapcsolatitomb[strtotomb1*3+1];
	 		m2mun=kapcsolatitomb[strtotomb1*3+2];

	 		for(hi=0;hi<n*60;hi++){
	 			if(hi==0){
	 				if(sokas[hi]==0)
	 					enged=true;
	 			}
	 			else if(sokas[hi]==0 && sokas[hi-1]!=0)
	 				enged=true;
	 			if(enged){
					enged=false;
					for(hj=0;hj<e1mun;hj++)
						if(sokas[hi+hj]!=0){
							nemjo=true;
							break;
						}
						if(nemjo){
							nemjo=false;
							continue;
						}
				
			for(hk=hi+hj+szun;hk<hi+hj+szun+m2mun;hk++)
				if(sokas[hk]!=0){
					nemjo=true;
					break;
					}
				}
						else
							continue;
					
						if(nemjo){
							nemjo=false;
							continue;
						}
						else
						{
							
							break;
						}
					}
			 	
			 		for(ei=hi;ei<hi+e1mun;ei++)
						sokas[ei]=strtotomb1+1;
				 	
					for(ek=ei+szun;ek<ei+szun+m2mun;ek++)		
						sokas[ek]=strtotomb1+1;
				
				 	if(part1<ek)
				 		partuthossz=ek;
				 	else
				 		partuthossz=part1;
				 	 part1=partuthossz;
				}
	  	return partuthossz;
	}//end vegsoreszuthossz

	public void reszuthossz4(String strcsucsok,String strcsucsokord)
	{
		boolean nemjo=false;
		for(int i=0;i<n*60;i++){sokas[i]=0;}
		partuthossz=0;
	 	StringTokenizer stz = new StringTokenizer(strcsucsok," ");
 		int hi=0;
 		int hj=0;
 		int hk=0;
 		int ei=0;
 		int ek=0;
 		int e1mun=0;
 		int szun=0;
 		int m2mun=0;
 		int strtotomb1=0;
 		int part1=0;
 		boolean enged=false;
	 	while(stz.hasMoreTokens())
		{
	 		strtotomb1=Integer.valueOf(stz.nextToken());
	 		e1mun=kapcsolatitomb[strtotomb1*3];
	 		szun=kapcsolatitomb[strtotomb1*3+1];
	 		m2mun=kapcsolatitomb[strtotomb1*3+2];

	 		for(hi=0;hi<n*60;hi++){
	 			if(hi==0){
	 				if(sokas[hi]==0)
	 					enged=true;
	 			}
	 			else if(sokas[hi]==0 && sokas[hi-1]!=0)
	 				enged=true;
				if(enged){
					enged=false;
					for(hj=0;hj<e1mun;hj++)
						if(sokas[hi+hj]!=0){
							nemjo=true;
							break;
						}
						if(nemjo){
							nemjo=false;
							continue;
						}	
			for(hk=hi+hj+szun;hk<hi+hj+szun+m2mun;hk++)
				if(sokas[hk]!=0){
					nemjo=true;
					break;
					}
				}
				else
					continue;
			
				if(nemjo){
					nemjo=false;
					continue;
				}
				else
				{
					break;
				}
			}	
	 		for(ei=hi;ei<hi+e1mun;ei++)
				sokas[ei]=strtotomb1+1; 		
			for(ek=ei+szun;ek<ei+szun+m2mun;ek++)		
				sokas[ek]=strtotomb1+1;
			if(part1<ek)
		 		partuthossz=ek;
		 	else
		 		partuthossz=part1;
		 	 part1=partuthossz;
		}	
	 	levagas(strcsucsok,strcsucsokord);
	}//end reszuthossz
	public void reszuthossz(String strcsucsok,String strcsucsokord)
	{
		boolean nemjo=false;
		for(int i=0;i<n*60;i++){sokas[i]=0;}
		partuthossz=0;	
	 	StringTokenizer stz = new StringTokenizer(strcsucsok," "); 	
 		int hi=0;
 		int hj=0;
 		int hk=0;
 		int ei=0;
 		int ek=0;
 		int e1mun=0;
 		int szun=0;
 		int m2mun=0;
 		int strtotomb1=0;
 		int part1=0;
	 	while(stz.hasMoreTokens())
		{
	 		strtotomb1=Integer.valueOf(stz.nextToken());
	 		e1mun=kapcsolatitomb[strtotomb1*3];
	 		szun=kapcsolatitomb[strtotomb1*3+1];
	 		m2mun=kapcsolatitomb[strtotomb1*3+2];

	 		for(hi=0;hi<n*30;hi++){
				if(sokas[hi]==0){
					for(hj=0;hj<e1mun;hj++)
						if(sokas[hi+hj]!=0){
							nemjo=true;
							break;
						}
						if(nemjo){
							nemjo=false;
							continue;
						}			
			for(hk=hi+hj+szun;hk<hi+hj+szun+m2mun;hk++)
				if(sokas[hk]!=0){
					nemjo=true;
					break;
					}
				}
				else
					continue;
			
				if(nemjo){
					nemjo=false;
					continue;
				}
				else
				{
					
					break;
				}
			}	 		
	 		for(ei=hi;ei<hi+e1mun;ei++)
				sokas[ei]=strtotomb1+1;
	 		
			for(ek=ei+szun;ek<ei+szun+m2mun;ek++)		
				sokas[ek]=strtotomb1+1;
			if(part1<ek)
		 		partuthossz=ek;
		 	else
		 		partuthossz=part1;
		 	 part1=partuthossz;
		}		
	  	levagas(strcsucsok,strcsucsokord);
	}//end reszuthossz

	public void elsoszintmake()
		{
			for(int i=0;i<n;i++)    //jav1********************************
			{
			   list0.add(String.valueOf(i));
			   list0.add(kapcsolatitomb[i*3]+kapcsolatitomb[i*3+1]+kapcsolatitomb[i*3+2]);     //jav3**********************
			   list0.add(String.valueOf(i));
			}
	}
	public void levagas(String strcsucsok,String strcsucsokord)
	{
		if(j==n-1)        ///////////////////jav4
		{
			dinkiscsucspast(strcsucsok,strcsucsokord);
		 	return;
		}
		if(list1.size()<=3)
		{
		   	dinkiscsucspast(strcsucsok,strcsucsokord);
			return;
		}
		int index;
		index=list1.indexOf(strcsucsokord);
		if(index!=-1)
		{
			if(((Integer)list1.get(index+1))>=partuthossz)
			{
				list1.remove(index);
				list1.remove(index);
				list1.remove(index);
			}
				else
				return;
		}
		dinkiscsucspast(strcsucsok,strcsucsokord);
	}//end levagas
	public void KiScSuCsok(String strcsucsok)
	{
		csucsmegjegyezkezdo=0;              
		while(csucsmegjegyezkezdo<n)
		{
			iter1=list1.listIterator();
			dinkiscsucsmake(strcsucsok);
			csucsmegjegyezkezdo++;
		}

	}//end KiScSuCsok


	public void NAgYCSuCSoK(int lenght)
	{
		iter0=list0.listIterator();
		while(iter0.hasNext())
		{
			iter0.next();
			iter0.next();
			String strcsucsok=(String)iter0.next();
			lengh=lenght;
			KiScSuCsok(strcsucsok);
		}
	}//end NAgYCSuCSoK

	public int vegsoreszuthossz(String strcsucsokvegso)
	{
		
		boolean nemjo=false;
		for(int i=0;i<n*60;i++){sokas[i]=0;}
		partuthossz=0;
		
	 	StringTokenizer stz = new StringTokenizer(strcsucsokvegso," ");
	 
 		int hi=0;
 		int hj=0;
 		int hk=0;
 		int ei=0;
 		int ek=0;
 		int e1mun=0;
 		int szun=0;
 		int m2mun=0;
 		int strtotomb1=0;
 		int part1=0;
	 	while(stz.hasMoreTokens())
		{
	 		strtotomb1=Integer.valueOf(stz.nextToken());
	 		e1mun=kapcsolatitomb[strtotomb1*3];
	 		szun=kapcsolatitomb[strtotomb1*3+1];
	 		m2mun=kapcsolatitomb[strtotomb1*3+2];
	 		for(hi=0;hi<n*60;hi++){
				if(sokas[hi]==0){
					for(hj=0;hj<e1mun;hj++)
						if(sokas[hi+hj]!=0){
							nemjo=true;
							break;
						}
						if(nemjo){
							nemjo=false;
							continue;
						}	
			for(hk=hi+hj+szun;hk<hi+hj+szun+m2mun;hk++)
				if(sokas[hk]!=0){
					nemjo=true;
					break;
					}
				}
				else
					continue;
			
				if(nemjo){
					nemjo=false;
					continue;
				}
				else
				{
					break;
				}
			}
	 	 		for(ei=hi;ei<hi+e1mun;ei++)
				sokas[ei]=strtotomb1+1;
			for(ek=ei+szun;ek<ei+szun+m2mun;ek++)		
				sokas[ek]=strtotomb1+1;
				 	if(part1<ek)
		 		partuthossz=ek;
		 	else
		 		partuthossz=part1;
		 	 part1=partuthossz;
		}
	  	return partuthossz;
	}//end vegsoreszuthossz
	public void run()
	{
		Date currentDate = new Date();
		long msec1 = currentDate.getTime();
		elsoszintmake();
		for(j=1;j<n;j++)
		{
			NAgYCSuCSoK(j);
			list0.clear();
			list0.addAll(list1);
			list1.clear();
		}
		ListIterator iterresult= list0.listIterator();
		while(iterresult.hasNext())
		{
			iterresult.next();
			iterresult.next();
			String strstr;	
			strstr=(String)iterresult.next();
			if(melikazz==5)//jav6***********************
				list1.add(new Integer(vegsoreszuthossz4(strstr)));
			else
				list1.add(new Integer(vegsoreszuthossz(strstr)));
			list1.add(strstr);
		}
		ListIterator iterresult1= list1.listIterator();
		int numas=200000000;
		String strvegsomego="";
		while(iterresult1.hasNext())
		{
			int szama=(Integer)iterresult1.next();	
			if(numas>szama)
			{
				numas=szama;
				strvegsomego=(String)iterresult1.next();
			}
			else
				iterresult1.next();
		}		
		new MunkaGraphic22(strvegsomego,n*3,kapcsolatitomb,name3,melikazz,numas);
		name3="";
		Date currentDate2 = new Date();
		long msec2 = currentDate2.getTime();
		long msecr =msec2-msec1;
		System.out.println("Pmu: "+msecr+"");
		System.out.println(100/10);
		// strvegsomego: a legrovidebb lefeketetese a vegso mego, n: munkak szama, kapcsolatitomb: a kapcsolatitomb a matrix, name3: ha file-bol megnyitott adatokkal dolgozik a program, a file neve, amit tovabbad a grafikus megjelenitesnek
	}//end SZINTEK
}//class MunkaEXP

//************

class JTextFieldLimit extends PlainDocument
{
	private int limit;
	private boolean toUppercase = false;
	JTextFieldLimit(int limit)
	{
		super();
		this.limit = limit;
	}
	JTextFieldLimit(int limit, boolean upper)
	{
    	super();
    	this.limit = limit;
    	toUppercase = upper;
	}

   public void insertString
    (int offset, String  strcsucsok, AttributeSet attr)throws BadLocationException
    {
   		if(strcsucsok == null) return;
		if((getLength() + strcsucsok.length()) <= limit)
		{
    		if(toUppercase) strcsucsok = strcsucsok.toUpperCase();
      		super.insertString(offset, strcsucsok, attr);
      	}
    }
}//class JTextFieldLimit

//************

class HaromPanel extends JPanel implements ActionListener, FocusListener
{
      JPanel Panel1 = new JPanel();
      JPanel Panel2 = new JPanel();
      JPanel Panel3 = new JPanel();

	//Panel1 komponensei :
    private JTextArea lbInfo1;
	JTextField tfvn;
	final int vn=101;
	JTextField [][] tfAdatok = new JTextField[vn][vn] ;
    JLabel  jlFigy;
    int vint;
    int varossz;
    JButton btEredmeny;
    int[][]utakTomb   = new int[vn][vn];
    JTextArea  uzenet;
    JButton btOk;
    Random generator = new Random();
    JLabel [][] tfJLab=new JLabel[vn][vn];
	JButton bt1open;
	JButton bt1save;
	private JFileChooser fco1 = new JFileChooser();
	boolean b1Open=false;
	String [][]tomBopen=new String[vn][vn];
	JPanel mentok1=new JPanel();
	private JFileChooser fcs1 = new JFileChooser();
	String name1;
	int varossz2;
	JScrollPane scrollbar1;
	int lost=0;
	boolean ugras=false;
	Point pontmemory=new Point(0,0);
	Thread TSPopti;
	
	Color focuslost;
	/****************/
	//Panel2 komponensei :
	final int db=16;
	JLabel jlRakasNum;
	JTextField tfRakasN;
	JButton btTorol;
	JTextArea uzenet2;
	JButton btEredmeny2;
	int vint2;
	JButton bt2open;
	JButton bt2save;
	JLabel jlFigy2;
	long [] fileTomb= new long[db];
	JLabel [] tfJLab2=new JLabel[db];
	JTextField [] tfAdatok2 = new JTextField[db];
	int ladaNum;
	int rakasNum;
	JPanel mentokL=new JPanel();
	private JFileChooser fco2 = new JFileChooser();
	private JFileChooser fcs2 = new JFileChooser();
	boolean boolte;
	int rnum=15;
	JScrollPane scrollbar2;
	/****************/

	//Panel3 komponensei :
	final int db3=61;
	JTextArea uzenet3;
	JButton btEredmeny3;
	JButton btEredmeny3_2;
	JButton btEredmeny3_3;
	JButton btEredmeny3_5;
	JButton btEredmeny3_6;
	int melyik;
	Thread Munkopti;
	Thread Munkopti2;
	Thread Munkopti3;
	int vint3;
	JLabel jl100;
	JLabel jlFigy3;
	JButton bt3open;
	JButton bt3save;
	long [] targyTomb= new long[db3];
	JLabel [] tfJLab3=new JLabel[db3];
	JTextField [] tfAdatok3 = new JTextField[db3] ;
	int [] tombA = new int [db3];
	JPanel mentokT=new JPanel();
	private JFileChooser fco3 = new JFileChooser();
	private JFileChooser fcs3 = new JFileChooser();
	boolean boolt;
	JButton btTorol2;
	int tdk2=6;
	int n20=tdk2*3;
	int t=0;
	int nyers;
	public HaromPanel()
	{
		setBackground(Color.BLUE);
		
	    setLayout(new GridLayout(1,1,10,10));
	    add(Panel3);
	    scrollbar1 = new JScrollPane(Panel1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //add(scrollbar1);
		//add(Panel2);
		
    	
		/*********************/
		Panel1.setLayout(new FlowLayout());
		JTextArea lbInfo1 = new JTextArea("Ebben a reszben megkeresheted(TSP) - a varosok kozt levo utak hosszaval megadva - a minden varost erinto legrovidebb korutat. Az 'a'(1) varos a start es a cel hely. Ez a program nagy segitseg egy szallitmanyozo vallalat szamara. Hasznalja egezseggel!    A VAROSOK SZAMA MEGADASA UTAN ENTER-t v. OK NYOMJON!!! A VAROS HOSSZADATAI UTAN PEDIG RESULT!!!",8,27);
		lbInfo1.setBackground(new Color(250,221,182));
		lbInfo1.setLineWrap(true);
		lbInfo1.setWrapStyleWord(true);
		lbInfo1.setEditable(false);
		mentok1.setLayout(new GridLayout(3,1,5,5));
		mentok1.add(bt1open=new JButton("Open"));
		mentok1.add(bt1save=new JButton("Save"));
		Panel1.add(mentok1);
		Panel1.add(lbInfo1);
		Panel1.add(new JLabel("varosok szama(max.100):"));
		Panel1.add(tfvn = new JTextField(3));
		tfvn.setDocument(new JTextFieldLimit(3));
		Panel1.add(btOk=new JButton("OK"));
		Panel1.add(btEredmeny=new JButton("Result"));
		btEredmeny.addActionListener(this);
		btEredmeny.setEnabled(false);
		tfvn.addActionListener(this);
		btOk.addActionListener(this);
		bt1open.addActionListener(this);
		bt1save.addActionListener(this);
		bt1save.setEnabled(false);
		Panel1.add(jlFigy = new JLabel(""));
		Panel1.add(uzenet=new JTextArea("",1,18));
		uzenet.setForeground(new Color(0,0,255));
		uzenet.setEditable(false);
		uzenet.setLineWrap(true);
		uzenet.setWrapStyleWord(true);
		jlFigy.setForeground(new Color(255,0,0));
		fco1.setCurrentDirectory(new File("."));
		fco1.setFileFilter(new JavaFilter());
		fcs1.setCurrentDirectory(new File("."));
		fcs1.setFileFilter(new JavaFilter());

		//end konstruktor Panel1

		/*************************************/

		//Panel2 konstruktor DEKLARACIOI :

		mentokL.setLayout(new GridLayout(3,1,5,5));
		mentokL.add(bt2open=new JButton("Open"));
	    mentokL.add(bt2save=new JButton("Save"));
		Panel2.add(mentokL);
	    JTextArea lbInfo2 = new JTextArea("Egy kontener raktarban egy sorban egymas mellett tarolnak n darab kontenert. A raktaros at akarja rendezni a kontenereket ugy, hogy nehanyat egymasra rak. Az atrendezest bizonyos kontenerek egyesevel torteno atrakasaval lehet vegezni. Ha az atrendezes soran az i-edik kontenerhelyen levo kontenert a j-edik kontenerhelyre rakja at, ennek koltsege si|i - j|, ha a kontener sulya si. A raktaros olyan atrendezest akar, amely utan pontosan k kontenerhelyen lesz kontener (esetleg egymasra rakva). A raktaros az optimalis atrendezest keresi, tehat amelyre az osszkoltseg minimalis. Ez a panel kiszamitja a raktar optimalis atrendezesenek koltseget, es a rakasok HELYEIT is megmutatja! A PARAMETER BEVITELE UTAN ADJA MEG A LADAK SULYAT, UTANNA A RESULT GOMBOT KELL MEGNYOMNI!! ",8,54);
	   	lbInfo2.setBackground(new Color(250,221,182));
	   	lbInfo2.setLineWrap(true);
	   	lbInfo2.setWrapStyleWord(true);
	   	lbInfo2.setEditable(false);
		Panel2.add(lbInfo2);
		Panel2.add(jlRakasNum=new JLabel("Az keletkezett lefoglalt rakas helyek:(Max 15):"));
	    Panel2.add(tfRakasN=new JTextField(3));
	    tfRakasN.setDocument(new JTextFieldLimit(2));
	    Panel2.add(btTorol = new JButton("Torol"));
	    Panel2.add(btEredmeny2=new JButton("Result"));
	    Panel2.add(jlFigy2 = new JLabel(""));
	    Panel2.add(uzenet2=new JTextArea("",2,36));
	    jlFigy2.setForeground(new Color(255,0,0));
	    uzenet2.setForeground(new Color(0,0,255));
		uzenet2.setEditable(false);
		uzenet2.setLineWrap(true);
		uzenet2.setWrapStyleWord(true);
		btEredmeny2.addActionListener(this);
		tfRakasN.addActionListener(this);
		tfRakasN.addFocusListener(this);
		bt2open.addActionListener(this);
		btTorol.addActionListener(this);
		bt2save.addActionListener(this);
		fco2.setCurrentDirectory(new File("."));
		fco2.setFileFilter(new JavaFilter());
		fcs2.setCurrentDirectory(new File("."));
		fcs2.setFileFilter(new JavaFilter());

		//Panel3 konstruktor DEKLARACIOI :
		mentokT.setLayout(new GridLayout(3,1,5,5));
		mentokT.add(bt3open=new JButton("Open"));
		mentokT.add(bt3save=new JButton("Save"));
		Panel3.add(mentokT);
		JTextArea lbInfo3 = new JTextArea("UDVOZOLLEK A SZAKDOLGOZATOM PROGRAMJABAN. Az utemezesi problemak a kombinatorikus optimalizalas egyik jol ismert teruletet jelentik. Altalaban az utemezes celja, hogy adott munkakat utemezzunk adott gepeken ugy, hogy az utoljara vegrehajtando munka a leheto leghamarabb befejezodjon. A problemanak nagyon sokfele valtozata letezik. A munkak ket reszbol allnak, a reszek kozott fix hosszusagu szunettel, es csupan egyetlen gepet hasznalunk. Ugy probalom mukodtetni a heurisztikakat, hogy a szuneteket kihasznalva egymasba agyazom a munkakat, igy nincs folosleges varakozas.",6,54);
		lbInfo3.setBackground(Color.RED);
		lbInfo3.setLineWrap(true);
		lbInfo3.setWrapStyleWord(true);
		lbInfo3.setEditable(false);
		Panel3.add(lbInfo3);
		melyik=1;
		Panel3.add(btTorol2 = new JButton("Torol"));
		Panel3.add(btEredmeny3=new JButton("pol_res-1"));
		Panel3.add(btEredmeny3_2=new JButton("pol_res-2"));
		Panel3.add(btEredmeny3_3=new JButton("exp_res-1"));
		Panel3.add(btEredmeny3_5=new JButton("exp_res-2"));
		Panel3.add(btEredmeny3_6=new JButton("exp_opt_res"));
		Panel3.add(jlFigy3 = new JLabel(""));
		Panel3.add( jl100 = new JLabel("Csak max 19 egys.-nyi reszek:"));
		Panel3.add(uzenet3=new JTextArea("",2,36));
		jlFigy3.setForeground(new Color(255,0,0));
		uzenet3.setForeground(new Color(0,0,255));
		uzenet3.setEditable(false);
		uzenet3.setLineWrap(true);
		uzenet3.setWrapStyleWord(true);
		btEredmeny3.addActionListener(this);
		btEredmeny3_2.addActionListener(this);
		btEredmeny3_3.addActionListener(this);
		btEredmeny3_5.addActionListener(this);
		btEredmeny3_6.addActionListener(this);
		btTorol2.addActionListener(this);
		bt3open.addActionListener(this);
		bt3save.addActionListener(this);
		fco3.setCurrentDirectory(new File("."));
		fco3.setFileFilter(new JavaFilter());
		fcs3.setCurrentDirectory(new File("."));
		fcs3.setFileFilter(new JavaFilter());
		mezok3();
		for(int i=1; i<=n20;i++)
		{
			tfAdatok3[i].addActionListener(this);
			tfAdatok3[i].addFocusListener(this);
		}
		//end Panel3 konstruktor DEKLARACIOI :
	}// end HaromPanel

	public void focusGained(FocusEvent e)
	{
		for(int i=1;i<=varossz;i++)
			for(int j=i+1;j<=varossz;j++)
			if(e.getComponent()==tfAdatok[i][j])
			{
				Point pont0=tfJLab[varossz-1][varossz].getLocation();
				tfAdatok[i][j].selectAll();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Point pont=tfJLab[i][j].getLocation();
				if(pont0.getX()+tfAdatok[varossz-1][varossz].getWidth()+tfJLab[varossz-1][varossz].getWidth()-pont.getX()>screenSize.width)
					scrollbar1.getViewport().setViewPosition(new Point((int)pont.getX()-150,(int)pont.getY()-70));
				else
					scrollbar1.getViewport().setViewPosition(new Point((int)(pont0.getX()-screenSize.width+tfAdatok[varossz-1][varossz].getWidth()+tfJLab[varossz-1][varossz].getWidth()),(int)pont.getY()-70));
			}
		for(int i=1; i<=rnum;i++)
			if(e.getComponent()==tfAdatok2[i])
			{
				tfAdatok2[i].selectAll();
			}
		for(int i=1; i<=n20;i++)
			if(e.getComponent()==tfAdatok3[i])
			{
				tfAdatok3[i].selectAll();
			}
	}//end focusGained

	public void focusLost(FocusEvent e)
	{
	}
	public void actionPerformed(ActionEvent ev)
	{
		String str2;
		String bevitel2_2;
		for(int i=1;i<=varossz;i++)
			for(int j=i+1;j<=varossz;j++)
			if(ev.getSource()==tfAdatok[i][j])
			btEredmeny.doClick();
		for(int i=1; i<=rnum;i++)
			if(ev.getSource()==tfAdatok2[i])
					btEredmeny2.doClick();
		for(int i=1; i<=n20;i++)
				if(ev.getSource()==tfAdatok3[i])
					btEredmeny3.doClick();


		if(ev.getSource()==tfvn || ev.getSource()==btOk)
		{
			pontmemory=new Point(0,0);
			String str0=tfvn.getText();
	   		String bevitel1=str0.trim();
			if (!isNumber(bevitel1,4,100))
			{
	   			jlFigy.setText("Nem jo szam!!");
	   			tfvn.requestFocus();
	  			tfvn.selectAll();
			}
				else
			{
				varossz = Integer.parseInt(bevitel1);
		 		varossz2=varossz;
				uzenet.setText("Alap varosok szama:  "+varossz2);
				bt1save.setEnabled(true);
				btEredmeny.setEnabled(true);
				b1Open=true;
	    		jlFigy.setText("Jo szam   !!");
				for(int sor=1; sor<=vint;sor++)
					for(int oszlop=sor+1;oszlop<=vint;oszlop++)
					{
						Panel1.remove(tfJLab[sor][oszlop]);
						Panel1.remove(tfAdatok[sor][oszlop]);
					}
				
					jlFigy.setText("");
						 		}
		}
		else if (ev.getSource()==btEredmeny)
		{
			String str0=tfvn.getText();
			String bevitel1=str0.trim();
			jlFigy.setText("");
			if (!isNumber(bevitel1,3,101))
			{
				jlFigy.setText("Nem jo szam!!");
				tfvn.requestFocus();
	  			tfvn.selectAll();
				return;
			}
			if(varossz2<Integer.valueOf(str0))
			{
				tfvn.setText(String.valueOf(varossz2));
				varossz=varossz2;
			}
			else
				varossz=Integer.valueOf(str0);
			//MezokKezel1(false);
		}
	
		else if(ev.getSource()==btTorol)
			for(int i=1; i<=rnum;i++)
				tfAdatok2[i].setText("0");
		else if(ev.getSource()==btTorol2)
			for(int sor=1; sor<=n20;sor++)
				tfAdatok3[sor].setText("1");
		else if(ev.getSource()==btEredmeny3 )
		{
			uzenet3.setText("");
			jlFigy3.setText("");
			 MezokKezel3(false,1);
		}

		else if(ev.getSource()==btEredmeny3_2 )
		{
			uzenet3.setText("");
			jlFigy3.setText("");
			 MezokKezel3(false,2);
		}
		else if(ev.getSource()==btEredmeny3_3 )
		{
			uzenet3.setText("");
			jlFigy3.setText("");
			 MezokKezel3(false,3);
		}
		else if(ev.getSource()==btEredmeny3_5 )
		{
			uzenet3.setText("");
			jlFigy3.setText("");
			 MezokKezel3(false,5);
		}
		else if(ev.getSource()==btEredmeny3_6 )
		{
			uzenet3.setText("");
			jlFigy3.setText("");
			 MezokKezel3(false,6);
		}
		

		else if(ev.getSource()==bt3save)
		{
			jlFigy3.setText("");
			MezokKezel3(true,1);
			if(!boolt && fcs3.showSaveDialog(this)==JFileChooser.APPROVE_OPTION )
			{
				update3save();
			}
		}

		else if (ev.getSource()==bt3open)
		{
			if(fco3.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
			{
				update3open();
			}
		}
	}//end actionPerformed

	boolean isNumber(String strcsucsok,int numVarMin,int numVarMax)
	{
		try
		{
			int tfvsza = Integer.parseInt(strcsucsok);
			if( tfvsza<numVarMin || tfvsza>numVarMax)
			throw new NumberFormatException();
			return true;
		}
			  catch( NumberFormatException ex)
	 	{
	   		  return false;
		}
	}//end isNumber


	void  update3open()
	{
		File sfo3=fco3.getSelectedFile();
		if(sfo3!=null)
		{
			try
			{
		  		BufferedReader in = new BufferedReader(new FileReader(sfo3));
				for(int sor=1; sor<=n20;sor++)
					tfAdatok3[sor].setText(in.readLine());
					boolean bool=false;
					for(int sor=1; sor<=n20;sor++)
					{
					    String str2 =(tfAdatok3[sor].getText());
				        if (!isNumber(str2,0,100) && bool==false)
				        {
				          	JOptionPane.showMessageDialog(this,"Nincs, vagy nem jo adat!");
				          	tfAdatok3[sor].requestFocus();
				          	tfAdatok3[sor].selectAll();
				          	bool=true;
					 	}
				          	else
							  tombA[sor-1]=Integer.parseInt(str2);
					}
				if(!bool) munka(sfo3.getName(),1);
		     	in.close();
		  	}catch (IOException ex) {
			}
		 }
	 }//end update3open


	void update3save()
	{
		File sfs3=fcs3.getSelectedFile();
		if(sfs3!=null)
	   	{
	  		try
	  		{
				String fname=sfs3.getAbsolutePath();
				if(!(sfs3.getName().toUpperCase().endsWith(".TXT")))
				{
					fname=fname+".txt";
				}
		  		BufferedWriter out3 = new BufferedWriter(new FileWriter(fname));
		    	for(int sor=1; sor<=n20; sor++)
				{
	  				out3.write(tfAdatok3[sor].getText());
	  		 		out3.newLine();
				}
			out3.close();
		    }catch (IOException ex){
	  		}
	  	}
	}//end update3save

	void mezok3()
	{
		int sorolo=0;
		for(int sor=1;sor<=n20; sor++)
		{
			if(sor%3==1)
				sorolo++;
			Panel3.add(tfJLab3[sor]=new JLabel(sorolo+"."));
			Panel3.add(tfAdatok3[sor]=new JTextField(4));
			tfAdatok3[sor].setDocument(new JTextFieldLimit(3));
			if(sor%3==0)
				tfAdatok3[sor].setBackground(new Color(255,255,50));
			else if(sor%3==1)
				tfAdatok3[sor].setBackground(new Color(255,255,50));
			else
	           tfAdatok3[sor].setBackground(new Color(255,255,220));
		 
		}
		
		 Color szin;
		 Panel3.setBackground(szin=new Color(255,generator.nextInt(254)+1,230));
		 mentokT.setBackground(szin);
		 tfAdatok3[1].requestFocus();
		 btTorol2.doClick();
	}//end  mezok3



	void MezokKezel3(boolean isOpen,int melyikaz)
	{
		boolt=false;
		for(int sor=1; sor<=n20;sor++)
		{
			String str2 =(tfAdatok3[sor].getText().trim());
			if (!isNumber(str2,1,19) && boolt==false)
			{
				JOptionPane.showMessageDialog(this,"Nincs, vagy nem jo adat!");
				tfAdatok3[sor].requestFocus();
				tfAdatok3[sor].selectAll();
				boolt=true;
			}
			else
			tombA[sor-1]=Integer.parseInt(str2);
			}
	     if(!boolt && !isOpen) munka("",melyikaz);
	}//end MezokKezel3


	void munka(String name3,int melikaz)
	//name3: Ha megyitott file-bol dolgozik a program, a file neve, amit tovabbad a grafikus megjelenitesnek
	{
		Date currentDate224 = new Date();
		long msec11 = currentDate224.getTime();
		System.out.println("msec11: "+msec11);
		int [] tombB = new int [n20];
		int [] tombC = new int [n20];

	// a program lelke : a tombA[j]-ben taroltam a beolvasott adatokat
	// es hasonlitom mindig ossze a forciklusok segitsegevel a 19
	// szamokat tartalmazo tombB[i]-vel. Tehat mindig az elejerol
	// vizsgalom az elferhetoseget.
		for(int j=0;j<n20;j++)
		{
			tombB[j]=0;
			tombC[j]=1;
		}
		int minii=0;
		int minij=0;
		int minicheck=65000;
		float elozoarany = 200;
		nyers=0;
		for(int i=0;i<n20;i++)
			nyers+=tombA[i];
		int osszmun=0;
		for(int i=0; i<n20; i+=3){
			if(tombC[i]==0)
				continue;
			int minivolt=65000;
			float elozoaranyvolt=200;
			boolean bool=false;
			boolean minibool= false;
			for(int j=0; j<n20; j+=3){
				if(i==j || tombC[j]==0)
					continue;
				if((tombA[i+1] >= tombA[j]) && (tombA[i+1] + tombA[i+2] <= tombA[j]+tombA[j+1])){
					minicheck = tombA[i]+tombA[j]+tombA[j+1]+tombA[j+2];
					if(minicheck==0)
						minicheck=1;
					elozoarany=(tombA[j+1]-tombA[i+2])/minicheck;
					 minibool = true;		
				}
				
				if(melikaz==2){
					if((minicheck < minivolt) && minibool && (elozoarany < elozoaranyvolt)){
						minii=i;
						minij=j;
						bool=true;
						minivolt=minicheck;
						minibool= false;
						elozoaranyvolt=elozoarany;
					}
					else
						minii=i;
				}
					
					else if(melikaz==1)
					{
				if((minicheck < minivolt) && minibool ){
					minii=i;
					minij=j;
					bool=true;
					minivolt=minicheck;
					minibool= false;
				}
				else
					minii=i;
					}
					else if(melikaz==3){
						Munkopti=new Thread( new MunkaExp(tombA,(n20/3),name3,3));
						Munkopti.setPriority(2);
						Munkopti.start();
						return;
					}
						
					else if(melikaz==5){
						Munkopti2=new Thread( new MunkaExp(tombA,(n20/3),name3,5));
						Munkopti2.setPriority(2);
						Munkopti2.start();
						return;
					}	
					else if(melikaz==6)
					{
						Munkopti3=new Thread( new MunkaExp2(tombA,(n20/3),name3,6));
						Munkopti3.setPriority(2);
						Munkopti3.start();
						return;
					}
				}
			
				if(bool==true){
					tombB[t] = minii;
					tombB[t+1]=1;
					tombB[t+2]=minij;
					osszmun += tombA[minii] + tombA[minij] + tombA[minij+1] + tombA[minij+2];
					tombC[minii]=0;
					tombC[minij]=0;
					t+=3;
				}
	}	
		for(int i=0; i<n20; i+=3){
		if(tombC[i]!=0)	{
			tombB[t] = i;
			tombB[t+1]=0;
			tombB[t+2]=0;
			osszmun +=tombA[i] + tombA[i+1] + tombA[i+2];
			tombC[i] = 0;
			t=t+3;
			}
		}
		int val=t;
		t=0;	
			int nyersparam=nyers;
			nyers=0;
		
		uzenet3.setText("Az osszes munka ideje "+(osszmun)+" egyseg.");
		Date currentDate34 = new Date();
		long msec22 = currentDate34.getTime();
		long msecr22 =msec22-msec11;
		System.out.println("msec22: "+msec22+"");
		System.out.println("Pmup: "+msecr22+"");
		new MunkaGraphic(melikaz,osszmun,nyersparam,tombA,tombB,n20,val,name3);
		
	}//end munka
}//class HaromPanel

//*****************

class Munkak extends JPanel
{
	private Color color;
	private int nagysag;
	private int nta;
  	public Munkak(Color color,int nagysag,int nta)
	{
	 	this.color=color;
	 	this.nagysag=nagysag;
	 	this.nta=nta;
	 }
	 protected void paintComponent(Graphics gr)
	 {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		super.paintComponent(gr);
		gr.setColor(color);
		int szeleseg=0;
		if(nta>10)
		szeleseg=screenSize.width/nta-12;
		else
		szeleseg=80;
		gr.fillRect(5,5,szeleseg,(int)(nagysag*3));
		gr.setColor(Color.BLACK);
		gr.drawRect(5,5,szeleseg,(int)(nagysag*3));
	}
}//class Munkak

class MunkaGraphic22 extends JFrame
{
  	private Container cp= getContentPane();
	private int nt;
	private int[]tombGrA;
	private String nev3;
	private int mejikaz;
	static int szamlalo=1;
	int numas;

	public MunkaGraphic22(String strvegsostr,int nt,int[]kapcsolatitombos,String nev3,int melikazz,int numas)
	{
		mejikaz=melikazz;
  		this.tombGrA=kapcsolatitombos;
  		this.nt=nt;
  		this.numas=numas;
  		this.nev3=nev3;
  		setDefaultCloseOperation(HIDE_ON_CLOSE);
  		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		int magasag=(int)(screenSize.height/3);
  		setBounds(0,magasag,screenSize.width,(int)(screenSize.height/3));
  		if(mejikaz==3)
  			setTitle((szamlalo++)+". jobb heurisztikával 3."+nev3);
  		else if(mejikaz==5)
  			setTitle((szamlalo++)+". jobb heurisztikaval 4."+nev3);
  		JPanel work=new JPanel();
  		JPanel workAdat=new JPanel();
  		cp.setLayout(new GridLayout(3,0));
  		work.setLayout(new GridLayout(1,nt,3,10));
  		workAdat.setLayout(new GridLayout(1,nt,3,10));
  		int nw=0;
  		for(int i=0;i<nt;i++)
  		{
  			if(i%3==0){
  				nw++;
  			}
 
  			if(i%3==0)
  				work.add(new Munkak(Color.RED,tombGrA[i],nt));
  			else if(i%3==1)
  				work.add(new Munkak(Color.GREEN,tombGrA[i],nt));
  			else
  				work.add(new Munkak(Color.RED,tombGrA[i],nt));
  			workAdat.add(new JLabel((nw)+":"+tombGrA[i]));
		}
  		nw=0;
		cp.add(work);
		cp.add(workAdat);
		StringTokenizer stgg = new StringTokenizer(strvegsostr," ");
		int s=1;
		JLabel Jlabl=new JLabel(nt/3+" paros munka, legrovidebb:  ");
		Jlabl.setForeground(Color.RED);
		while(stgg.hasMoreTokens())
		{
			int res= Integer.valueOf(stgg.nextToken());
			if(s==nt+1)
				Jlabl.setText(Jlabl.getText()+(res+1));
			else
				Jlabl.setText(Jlabl.getText()+(res+1)+"  ->   ");
			s++;
		}		
		cp.add(new JLabel("A megoldas : "+Jlabl.getText()+": "+numas));
		show();
	}
}//class MunkaGraphic22

class MunkaGraphic extends JFrame
{
  	private Container cp= getContentPane();
	private int[]tombGrB;
	private int nt;
	private int[]tombGrA;
	private String nev3;
	private int mejikaz;
	static int szamlalo=1;

	public MunkaGraphic(int mejikaz,int egy,int two,int []tombGrA,int[]tombGrB,int nt,int tt,String nev3)
	{
  		this.tombGrB=tombGrB;
  		this.mejikaz=mejikaz;
  		this.tombGrA=tombGrA;
  		this.nt=nt;
  		this.nev3=nev3;
  		setDefaultCloseOperation(HIDE_ON_CLOSE);
  		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		int magasag=(int)(screenSize.height/3*2);
  		int magasag2=(int)(screenSize.height-magasag);
  		setBounds(0,magasag,screenSize.width,magasag2-20);
  		if(mejikaz==2)
  			setTitle((szamlalo++)+".  2.heurisztikaval "+nev3);
  		else if(mejikaz==1)
  			setTitle((szamlalo++)+".  1.heurisztikaval "+nev3);
  	
  		JPanel work=new JPanel();
  		JPanel workAdat=new JPanel();
  		JLabel JLadat=new JLabel();
  		cp.setLayout(new GridLayout(3,0));
  		work.setLayout(new GridLayout(1,nt,3,10));
  		workAdat.setLayout(new GridLayout(1,nt,3,10));
  		int nw=0;
  		for(int i=0;i<nt;i++)
  		{
  			if(i%3==0){
  				nw++;
  			}
 
  			if(i%3==0)
  				work.add(new Munkak(Color.RED,tombGrA[i],nt));
  			else if(i%3==1)
  				work.add(new Munkak(Color.GREEN,tombGrA[i],nt));
  			else
  				work.add(new Munkak(Color.RED,tombGrA[i],nt));
  			workAdat.add(new JLabel((nw)+":"+tombGrA[i]));
		}
  		nw=0;
		cp.add(work);
		cp.add(workAdat);
		String ends="";
		for(int i=0; i < tt; i+=3)
			if(tombGrB[i+1] == 1)
				 ends+=(tombGrB[i]/3+1) + "(" +(tombGrB[i+2]/3+1) + ")"+"   ";
				else
				 ends+=(tombGrB[i]/3+1) + "   ";
			ends+="Heurisztikával az osszes munka : "+egy+" ";
		cp.add(new JLabel("A megoldas : "+ends));
		show();
	}
}//class MunkaGraphic

class StrCim extends JPanel
{
	private String Strint;
	private Color color;
	public StrCim(Color color,String Strint)
	{
		this.color=color;
		this.Strint=Strint;
	}
	protected void paintComponent(Graphics gr)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		super.paintComponent(gr);
		gr.setColor(color);
		gr.setFont(new Font("TimesRoman",Font.ITALIC,50));
		gr.drawString(Strint,0,100);
	}
}//end class StrCim

public class szdJava1 extends JFrame implements MouseListener
{
	JWindow win;
	public szdJava1()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, (int)(screenSize.height/3));
		setTitle("Munka utemezes 3.0");
		HaromPanel hpan= new HaromPanel();
		getContentPane().add(hpan);
		win = new JWindow(this);
		win.addMouseListener(this);
		win.setLayout(new GridLayout(3,1));
		win.getContentPane().add(new StrCim(new Color(0,0,255),"Munka Utemezes 3.0 "));
		JPanel JPkoz=new JPanel();
		JPkoz.setLayout(new GridLayout(1,3));
		win.getContentPane().add(JPkoz);
		win.getContentPane().add(new StrCim(new Color(255,0,0),"Szakdolgozat: Dinnyes Balazs; dibmaat.sze"));
		win.setBounds(0,0,screenSize.width, screenSize.height);
		win.show();
		setEnabled(false);
		show();
		}

	
	

	public void mouseClicked(MouseEvent ev)
	{
		if(ev.getSource()==win)
		{
		win.hide();
		setEnabled(true);
		}
	}
	public void mousePressed(MouseEvent ev)
	{
	}
	public void mouseReleased(MouseEvent ev)
	{
	}
	public void mouseEntered(MouseEvent ev)
	{
	}
	public void mouseExited(MouseEvent ev)
	{
	}

 	public static void main(String args[])
 	{
		new szdJava1();
	}
}// class szdJava1

//*****************************************************************************************************************//
