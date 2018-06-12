package com.cm.mybet.repository.impl;

import static com.cm.mybet.datastore.FileLocations.MY_BETS_CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cm.mybet.Bet;
import com.cm.mybet.repository.BetRepository;;

/**
 * An CSV datastore implementation.
 *
 * @author mcristian
 */
@Repository("csvRepository")
@Transactional
public class CSVRepositoryDatastore implements BetRepository {

	private static final Logger LOG = LogManager.getLogger();

	@Override
	public List<Bet> findAllBets() {

		LOG.info(() -> "Fetching all my Bets from CSV...");

		List<Bet> bets = new ArrayList<Bet>();
		File mybetsCsv = new File(MY_BETS_CSV);
		try (Scanner inputStream = new Scanner(mybetsCsv)) {
			inputStream.useDelimiter(",");
			inputStream.nextLine();
			while (inputStream.hasNextLine()) {
				String betline = inputStream.nextLine();
				String[] bet = betline.split(",");
				bets.add(new Bet(bet[0], Long.parseLong(bet[1]), Long.parseLong(bet[2]), bet[3], Double.parseDouble(bet[4]), Double.parseDouble(bet[5]), bet[6]));

			}
		} catch (FileNotFoundException fne) {
			LOG.error(() -> "Cannot find my bet CSV file " + fne);
		}
		return bets;
	}
}
