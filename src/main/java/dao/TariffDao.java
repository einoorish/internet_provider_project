package dao;

import java.util.List;

import model.Tariff;
import model.TariffType;

public interface TariffDao {

	public int add(Tariff tariff);
	
	public Tariff getTariffById(int id);
	
	public List<Tariff> getTariffListByType(TariffType type);
        
}