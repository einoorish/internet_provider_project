package dao;

import java.util.List;

import model.Tariff;
import model.TariffType;

public interface TariffDao {

	public int add(Tariff tariff);
	
	public Tariff getTariffById(long id);
	
	public List<Tariff> getTariffListByType(TariffType type);

	public void update(Tariff tariff);

	public void delete(long valueOf);

	public List<Tariff> getSortedTariffList(String sort);
        
}
