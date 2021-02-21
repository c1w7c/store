package Store;
import StoreStock.Articles;

public class Store extends Articles {
	private int price;
	private String units;
	private String manufacturer;
	
	public Store() {
		
	}
	
	public Store(String ID,String name,int price,
			String units,int stock, String manufacturer) {
		super(ID,name,stock);
		this.price = price;
		this.units = units;
		this.manufacturer = manufacturer;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	
}
