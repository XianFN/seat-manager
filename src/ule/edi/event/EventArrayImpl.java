package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ule.edi.model.Configuration.Type;
import ule.edi.model.*;

public class EventArrayImpl implements Event {
	
	
	private String name;
	private Date date;
	
	private Double priceGold;    // precio de entradas tipo GOLD
	private Double priceSilver;  // precio de entradas tipo SILVER
	
	private int nGold;    // Nº de butacas de tipo GOLD
	private int nSilver;  // Nº de butacas de tipo SILVER
	
	private Seat[] gold;
	private Seat[] silver;
	
	
	
   public Double getPriceGold() {
		return priceGold;
	}


	public void setPriceGold(Double priceGold) {
		this.priceGold = priceGold;
	}


	public Double getPriceSilver() {
		return priceSilver;
	}


	public void setPriceSilver(Double priceSilver) {
		this.priceSilver = priceSilver;
	}


public EventArrayImpl(String name, Date date, int nGold, int nSilver){
		this.name=name;
		this.date=date;
		this.nGold=nGold;
		this.nSilver=nSilver;

	   gold= new Seat[nGold];
	   silver= new Seat[nSilver];
	   this.priceGold=Configuration.DEFAULT_PRICE_GOLD;
	   this.priceSilver=Configuration.DEFAULT_PRICE_SILVER;
	   
   }
   
   
   public EventArrayImpl(String name, Date date, int nGold, int nSilver, Double priceGold, Double priceSilver){
	   this.name=name;
		this.date=date;
		this.nGold=nGold;
		this.nSilver=nSilver;

	   gold= new Seat[nGold];
	   silver= new Seat[nSilver];
	   this.priceGold=priceGold;
	   this.priceSilver=priceSilver;
   }
   

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Date getDate() {
		return this.date;
	}

	
	@Override
	public int getNumberOfAttendingChildren() {
		int contador=0;
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]!=null&&gold[i]) {
				contador++;
			}
		}
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]!=null) {
				contador++;
			}
		}
		
		return contador;
	}
		return 0;
	}

	@Override
	public int getNumberOfAttendingAdults() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfAttendingElderlyPeople() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfSoldSeats() {
		int contador=0;
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]!=null) {
				contador++;
			}
		}
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]!=null) {
				contador++;
			}
		}
		
		return contador;
	}

	@Override
	public int getNumberOfSoldGoldSeats() {
		int contador=0;
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]!=null) {
				contador++;
			}
		}

		return contador;
	}

	@Override
	public int getNumberOfSoldSilverSeats() {
		int contador=0;
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]!=null) {
				contador++;
			}
		}
		
		return contador;
	}

	@Override
	public int getNumberOfSeats() {
		return this.nGold+this.nSilver;
	}

	@Override
	public int getNumberOfGoldSeats() {
		return this.nGold;
	}

	@Override
	public int getNumberOfSilverSeats() {
		return this.nSilver;
	}


	@Override
	public int getNumberOfAvailableSeats() {
		int contador=0;
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]==null) {
				contador++;
			}
		}
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]==null) {
				contador++;
			}
		}
		
		return contador;
	}


	@Override
	public Seat getSeat(int pos, Type type) {

		if (type == Type.GOLD) {
			
			return gold[pos-1];
			
		}if (type == Type.SILVER) {
			
			return silver[pos-1];
			
		}
		
		
		return null;
	}


	@Override
	public Person refundSeat(int pos, Type type) {
		Seat a= getSeat(pos,type);
		
	
		if (type == Type.GOLD) {
			if (pos<0||pos> gold.length) {
				return null;
			}
			Person persona= gold[pos-1].getHolder();
			 gold[pos-1]=null;
			 return persona;
			
		}if (type == Type.SILVER) {
			if (pos<0||pos> silver.length) {
				return null;
			}
			Person persona= gold[pos-1].getHolder();
			silver[pos-1]=null;
			return persona;
		}
		return null;
	}


	@Override
	public boolean sellSeat(int pos, Person p, Type type) {
		if (type == Type.GOLD) {
			if (gold[pos-1]==null&&pos>=1&&pos<=nGold+1) {
			
				gold[pos-1]=new Seat(this,pos,type,p);
				return true;
			}
		}else {
			if (silver[pos-1]==null&&pos>=1&&pos<=nSilver+1) {
				
				silver[pos-1]=new Seat(this,pos,type,p);
				return true;
			}
			
		}
		
		
		return false;
	}


	@Override
	public List<Integer> getAvailableGoldSeatsList() {
		List<Integer> salida= new ArrayList<>();
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]==null) {
				salida.add(i-1);
			}
		}
		
		
		
		return null;
	}


	@Override
	public List<Integer> getAvailableSilverSeatsList() {
		List<Integer> salida= new ArrayList<>();
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]==null) {
				salida.add(i-1);
			}
		}
		
		
		
		return null;
	}

	@Override
	public Double getPrice(Seat seat) {
		if (seat.getType()==Type.GOLD&&seat!=null) {
			return priceGold;
			
		}
		if (seat.getType()==Type.SILVER&&seat!=null) {
			return priceSilver;
			
		}
		
		return null;
	}


	@Override
	public Double getCollectionEvent() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getPosPersonGold(Person p) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getPosPersonSilver(Person p) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isGold(Person p) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isSilver(Person p) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
}
