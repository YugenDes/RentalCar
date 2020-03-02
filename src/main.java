import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import database.DAOFactory;
import model.Noleggi;
import model.Utente;

public class main {

	public static void main(String[] args) {

		
		DAOFactory db = DAOFactory.getJpaDaoFactory();
		ArrayList<Noleggi> noleggi = db.getNoleggioDAO().getNoleggio();
		Map<Integer, List<Noleggi>> nollistGrouped = noleggi.stream()
				.collect(Collectors.groupingBy(n -> n.getUtente().getIdUtente()));
		TreeMap<Integer, Integer> sorted = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer int1, Integer int2) {
				return int2.compareTo(int1);
			}
		});

		TreeMap<Utente, Integer> map = new TreeMap<Utente, Integer>();

		for (Map.Entry<Integer, List<Noleggi>> entry : nollistGrouped.entrySet()) {
			int amount = 0;
			for (Noleggi noleggio : entry.getValue()) {
				amount += ChronoUnit.DAYS.between(
						noleggio.getInizioNoleggio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						noleggio.getFineNoleggio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
			sorted.put(entry.getKey(), amount);
		}
		
		

	}
}
