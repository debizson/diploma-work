import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.*;
//import java.util.ListIterator;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MunkaExp2 extends Thread{

	int kapcs;
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
	int partuthossz2;
	String megostrblocks;
	String name3;
	int [] sokas;
	int melikazz;
	int tdk;
	int pasti;
	 int MegO1;
	 int megO2;
	 int [] tomb;
	 String megostr;

	public MunkaExp2(int [] kapcsolatitomb,int n,String name3,int melikazz)

	//kapcsolatitomb: a munkák matrixa, n: páros munkak szama, name3: ha file-bol megnyitott adatokkal dolgozik a program, a file neve, amit tovabbad a grafikus megjelenitesnek
	{
		kapcs=0;
		this.kapcsolatitomb=kapcsolatitomb;
		this.n=n;
		this.name3=name3;
		kulcs=new int[n];
		strcsucsok="";strcsucsokord="";
		this.melikazz=melikazz;
		partuthossz2=100000;
		sokas= new int[n*6];
		 MegO1=100000;
		 megO2=100000;
		 megostr="";
		 megostrblocks="";
		tomb=new int[n*3];
	}

	public void dinkiscsucsmake(String strcsucsok,String fizik)
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
			pasti=csucsmegjegyez;
			String past=String.valueOf(csucsmegjegyez);
			strcsucsok=strcsucsok.concat(" "+past);
			csucsmegjegyezkezdo=csucsmegjegyez;
						rendez2(strcsucsok,past,fizik);
	}//end dinkiscsucsmake()
public void dinkiscsucspast2(String strcsucsok,String strcsucsokord,String fizik)
	{
				list1.addLast(strcsucsokord);
				list1.addLast(partuthossz2);
				list1.addLast(strcsucsok);
				list1.add(fizik);
	}//end dinkiscsucspast


	public void cserel(int i,int k,int[]kulcs)
	{
		if(i<0 || i>=lengh+1 || k<0 || k>=lengh+1 || i==k) return;
			int l=kulcs[i];
			kulcs[i]=kulcs[k];
			kulcs[k]=l;
	}//end cserel

	public void cserel2(int i,int k,int[]tomb,int hossz2)
	{
		if(i<0 || i>=hossz2 || k<0 || k>=hossz2 || i==k) return;
			int l=tomb[i];
			tomb[i]=tomb[k];
			tomb[k]=l;
	}
	
	public void legkisebb2(int i,int[]tomb,int hossz3)
	{
		if(i<0 || i>=hossz3)
		{
			 return;
		}
		for(int k=i+1;k<hossz3;k++) {
			if(tomb[i]>tomb[k]) cserel2(i,k,tomb,hossz3);
		}
		legkisebb2(i+1,tomb,hossz3);
	}
	
	public void legkisebb(int i,int[]kulcs)
	{
		if(i<0 || i>=lengh+1)
		{
			 return;
		}
		for(int k=i+1;k<=lengh;k++) {
			if(kulcs[i]>kulcs[k]) cserel(i,k,kulcs);
		}
		legkisebb(i+1,kulcs);
	}//end legkisebb

	public void rendez2(String strcsucsok,String utolso,String fizik)   
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
		for(int i=0;i<=lengh;i++)
	    	strcsucsokord=strcsucsokord.concat(String.valueOf(kulcs[i])+" ");
		if(melikazz==6)
		{
			reszuthossz6(strcsucsok,strcsucsokord,fizik);
		}
	}//end rendez
	
	public void rendez2(int [] tomb,int hossznodes)    
	{
		legkisebb2(0,tomb,hossznodes);

	}
	public void reszuthossz6(String strcsucsok,String strcsucsokord,String fizik)//**vvvv
	{
		int vege=0;
		int vege2=0;
		int first2=0;
		int o=0;
		int [] strcsucsoktomb =new int [11];
		int []fizitomb= new int [41];
		int []igazifizitomb=new int[n*60];
		int []bestigazifizit=new int[n*60];
		int []bestigazifizit2=new int[n*60];
		//int checkglobalmerce=0;
		int [] segedosszcsucsok = new int [50];
		int [] segedosszcsucsok2 = new int [50];
		int k=0;
		StringTokenizer stz = new StringTokenizer(strcsucsok," ");
 		while(stz.hasMoreTokens())
		{
	 		strcsucsoktomb[o]=Integer.valueOf(stz.nextToken());
	 		o++;
		}
 		int utsoelsotask=strcsucsoktomb[o-2]*1000+100;
 		//if (o==5)
 		//	  return;
 		for(int ou=0;ou<n;ou++)
 			segedosszcsucsok[ou]=ou;
 		for(int no=0;no<n;no++)
 			for(int nu=0;nu<=j;nu++)
 			{
 				if(strcsucsoktomb[nu]==no)
 				{
 					segedosszcsucsok[no]=10000;
 				}
 			}
 		StringTokenizer fiziktoken = new StringTokenizer(fizik,".");
 		for(int jj=0; jj<n*60;jj++)
 			igazifizitomb[jj]=bestigazifizit[jj]=bestigazifizit2[jj]=-1;
 		String vanmegostr="";
 		partuthossz2=100000;
 		int globalmerce=100000;
 		while(fiziktoken.hasMoreTokens())
		{
 			int checkglobalmerce=0;
			int seged=0;
			int seged100;
			String fizikreszek="";
			fizikreszek=fiziktoken.nextToken();
	 		StringTokenizer fizikkisresz = new StringTokenizer(fizikreszek,"_");
	 		int l=0;
	 		boolean ezaz=false;
	 		int tl_utanossz=0;
	 		while(fizikkisresz.hasMoreTokens())
	 		{
	 			fizitomb[l]=Integer.valueOf(fizikkisresz.nextToken());
	 			fizitomb[l+1]=Integer.valueOf(fizikkisresz.nextToken());
	 			if(fizitomb[l]>0 && fizitomb[l] % 100 == 0)
	 			{
	 				seged = fizitomb[l];
	 				seged100=seged;
	 				seged100-=100;
	 				seged100/=1000;
	 				
	 				
	 				if (ezaz)
		 			{
		 				tl_utanossz+=kapcsolatitomb[seged100*3];
		 			}
	 				for(int i=fizitomb[l+1];i<fizitomb[l+1]+kapcsolatitomb[seged100*3];i++)
	 				{
	 					igazifizitomb[i]=seged;
	 					k=i+1;
	 					//System.out.print("igazifizitomb["+i+"]: "+igazifizitomb[i]);
 					}	
	 				//if(o>2)
	 				if(utsoelsotask==fizitomb[l])
	 				{
	 					first2=k;			
	 					ezaz=true;//az elso kiindulasi beszuró pont
	 				}
	 				
	 				
	 				
	 				
	 				
	 				
	 			}										 
	 			else if(l % 2 == 0)
	 				
	 			{
	 				if(ezaz)
		 				tl_utanossz+=kapcsolatitomb[fizitomb[l]*3+2];
	 				for(int i=fizitomb[l+1];i<fizitomb[l+1]+kapcsolatitomb[fizitomb[l]*3+2];i++)
	 				{
	 					igazifizitomb[i]=fizitomb[l];
	 					vege=i+1;
	 				}
	 			}
	 			l+=2;
	 		}
	 		//System.out.println("first2: "+first2);
	 		int tl_utanosszossz=first2+tl_utanossz;
	 		checkglobalmerce=tl_utanosszossz+kapcsolatitomb[pasti*3]+kapcsolatitomb[pasti*3+2];
	 		for(int ppr=0;ppr<n;ppr++)
	 				segedosszcsucsok2[ppr]=segedosszcsucsok[ppr];
	 		for(int cc=0;cc<n*60;cc++)
	 		{
	 			bestigazifizit[cc]=bestigazifizit2[cc]=igazifizitomb[cc];
	 		}
	 		vege2=vege;
	 		for(int uu=0;uu<n*60;uu++)
	 			igazifizitomb[uu]=-1;
	 		for(int od=0;od<n;od++)
	 			if(segedosszcsucsok2[od]!=10000)
	 				checkglobalmerce+=(kapcsolatitomb[od*3]+kapcsolatitomb[od*3+2]);
	 		int s=0;;
	 		int p=0;;
	 		int pp=0;;
			 boolean belefer=true;
			 for(int h=first2;h<=vege2;h++)
			 {
				 if(bestigazifizit[h]!=-1)
			 	{
					 continue;
		 		}
					 for(s=h;s<h+kapcsolatitomb[pasti*3];s++)
						 if(bestigazifizit[s]!=-1)
						 {
							 belefer=false;
							 break;
						 }
						 else if(pasti==0)
			 				bestigazifizit[s]=100;
			 			else
			 				bestigazifizit[s]=pasti*1000+100;
		 		if(!belefer)
		 		{
		 			for(int i=h;i<=s;i++)
		 				bestigazifizit[i]=bestigazifizit2[i];
		 				belefer=true;
		 				continue;
	 			}
	 			for(p=s+kapcsolatitomb[pasti*3+1];
	 				p<s+kapcsolatitomb[pasti*3+1]+
	 				kapcsolatitomb[pasti*3+2];p++)
	 			{
	 				pp=p;
	 				if(bestigazifizit[p]!=-1)
	 				{
	 					belefer=false;
	 					break;
	 				}
	 				else bestigazifizit[p]=pasti;
	 			}
	 			if(!belefer)
	 			{

	 				for(int i=h;i<=pp+1;i++)
	 					bestigazifizit[i]=bestigazifizit2[i];
	 				belefer=true;
	 				continue;
	 			}
	 			String currentfizistring="";
	 			int szamolo=0;
	 			int r=-1;
	 			int f=bestigazifizit[0];
	 			int q;
	 			int nagyobb=0;;
	 			if(pp>vege2)
	 				nagyobb=pp;
	 			else
	 				nagyobb=vege2;
		 		for(q=0;q<=nagyobb+1;q++)
		 		{
		 			r=bestigazifizit[q];
	 				if(r==-1 && f!=-1)
	 				{
	 					currentfizistring+=f+"_"+(q-szamolo)+"_";
	  						szamolo=0;
	 						f=r;
	 				}
	 				else if(r!=f && r!=-1 && f!=-1)
		 			{
		 				currentfizistring+=f+"_"+(q-szamolo)+"_";
		 				szamolo=0;
		 				szamolo++;
		 				f=r;
		 			}
		 			else if(r!=-1 && f==-1)
		 			{
		 				szamolo++;
		 				f=r;
		 			}
		 			else if(r==-1 && f==-1)
		 			{
		 				f=r;
		 			}
		 			else if(r==f && r!=-1 && f!=-1)
		 			{
		 				szamolo++;
 						f=r;
		 			}
		 		}
		 		
		 			currentfizistring+=".";
		 			if(belefer)
		 			{	//checkglobalmerce=nagyobb;
		 				if(checkglobalmerce<globalmerce)
		 					globalmerce=checkglobalmerce;
		 				if(j==n-1)
		 				{
				 			megO2=nagyobb;
				 			if(megO2<MegO1)
				 			{
				 				MegO1=megO2;
				 				megostr=currentfizistring;
				 			}
		 				}
		 				vanmegostr+=currentfizistring;
		 			}
		 		for(int i=h;i<=nagyobb+1;i++)
 				bestigazifizit[i]=bestigazifizit2[i];
		 		belefer=true;	
			}
		}
 		partuthossz2=globalmerce;
	 	levagas2(strcsucsok,strcsucsokord,vanmegostr);
	}//end reszuthossz


	public void elsoszintmake(){
		for(int i=0;i<n;i++)  
		{
			int osszkezdo=0;
			for(int y=0;y<n*3;y++)
				if(y % 3 ==0 || y % 3 ==2)
					osszkezdo+=kapcsolatitomb[y];
			if(i==0)
			{
			list0.add(String.valueOf(i));
			list0.add(osszkezdo); //(100+" "+0);    
			list0.add(String.valueOf(i));
			list0.add("100"+"_0_"+i+"_"+(kapcsolatitomb[i*3]+kapcsolatitomb[i*3+1])+"_.");  
			}
			else
			{
		    list0.add(String.valueOf(i));
		    list0.add(osszkezdo); 
		    list0.add(String.valueOf(i));
		    list0.add(i+"100"+"_0_"+i+"_"+(kapcsolatitomb[i*3]+kapcsolatitomb[i*3+1])+"_."); 
			}
		}
	}

	public void levagas2(String strcsucsok,String strcsucsokord,String fizik)
	{
		if(j==n-1)       
		{
			dinkiscsucspast2(strcsucsok,strcsucsokord,fizik);
		 	return;
		}
		if(list1.size()<=4)                 
		{
		   	dinkiscsucspast2(strcsucsok,strcsucsokord,fizik);
			return;
		}
		
		int index;
		index=list1.indexOf(strcsucsokord);
		
		if(kapcs!=0)
			if(index!=-1 && j>=2)	
			{
				if(((Integer)list1.get(index+1))>=partuthossz2)
				{
					list1.remove(index);
					list1.remove(index);
					list1.remove(index);
					list1.remove(index);//javitas
				}
				else
					return;
			}
		
		dinkiscsucspast2(strcsucsok,strcsucsokord,fizik);
	}//end levagas

	public void KiScSuCsok(String strcsucsok,String fizik)
	{
		csucsmegjegyezkezdo=0;                
		while(csucsmegjegyezkezdo<n)
		{
			iter1=list1.listIterator();
			dinkiscsucsmake(strcsucsok,fizik);
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
			String fizik=(String)iter0.next();
			lengh=lenght;
			KiScSuCsok(strcsucsok,fizik);
		}
	}//end NAgYCSuCSoK


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
		new MunkaGraphic26(megostr,n*3,kapcsolatitomb,name3,melikazz,(MegO1+1));
		if(j==n)
			System.out.println("MegO1: "+ (MegO1+1)+ "megostr: "+megostr);
		Date currentDate2 = new Date();
		long msec2 = currentDate2.getTime();
		long msecr =msec2-msec1;
		System.out.println("Pmu: "+msecr+"");
		}//end SZINTEK
}//class MunkaEXP2


class MunkaGraphic26 extends JFrame
{
  	private Container cp= getContentPane();
	private int nt;
	private int[]tombGrA;
	private String nev3;
	private int mejikaz;
	static int szamlalo=1;
	int numas;
	public MunkaGraphic26(String strvegsostr,int nt,int[]kapcsolatitombos,String nev3,int melikazz,int numas)
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
  		if(mejikaz==6)
  			setTitle((szamlalo++)+". 5. optimális megoldással "+nev3);
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
		StringTokenizer stgg = new StringTokenizer(strvegsostr,"_,.");
		JLabel Jlabl=new JLabel(nt/3+" paros munka, legrovidebb:  ");
		Jlabl.setForeground(Color.RED);
		int gnum=0;
		if(mejikaz==6)
		{
			int	seged100;
			while(stgg.hasMoreTokens())
			{  
				gnum++;
				String res=stgg.nextToken();
				int resint= Integer.valueOf(res);
				if(gnum%2==1)
					if(resint>0 && resint % 100 == 0 )
					{
						
						int	seged = resint;
						seged100=seged;
						seged100-=100;
						seged100/=1000;
						seged100++;
						res=seged100+"\"";
					
					}else 
					res=(resint+1)+"";					
				if (gnum%2==1)
					Jlabl.setText(Jlabl.getText()+(res)+" -> ");
				else
					Jlabl.setText(Jlabl.getText()+res+"  ");
			}
		}
		cp.add(new JLabel("A megoldas : "+Jlabl.getText()+": "+numas));
		show();
	}
}//class MunkaGraphic22

