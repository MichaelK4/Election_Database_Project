package Elections;

import java.sql.*;
//import java.util.Vector;

import Elections.Kalpi.KalpiType;
import Elections.Party.flag;

public class Database {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/autoelectiondb";
	private static final String USER = "root";
	private static final String PASS = "1963";

	private Connection conn = null;
	private Statement stmt = null;

	public Database() {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected to database successfully...");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	//insert entities methods below
	public void insertElection(Elections e1) 
	{
		try {
			String sql = "INSERT INTO Election_t (ElectionYear, ElectionMonth, numOfKalpies, numOfParties,"
					+ " numOfVoters, numOfCandidates, numOfVotes, ElectedParty) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e1.getYear());
			pstmt.setInt(2, e1.getMonth());
			pstmt.setInt(3, e1.getNumOfKalpies());
			pstmt.setInt(4, e1.getNumOfParties());
			pstmt.setInt(5, e1.getNumOfVoters());
			pstmt.setInt(6, e1.getNumOfCandidates());
			pstmt.setInt(7, e1.getNumOfVotes());
			pstmt.setString(8, e1.getElected());
			pstmt.executeUpdate();
			
			updateNumberOfElections();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertKalpi(Kalpi k1, Elections e1) {
		try {
			String sql = "INSERT INTO Kalpi_t (KalpiID, Location, KalpiType, numOfVoters, numOfVotes,"
					+ " ElectionYear,ElectionMonth) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, k1.getKalpiId());
			pstmt.setString(2, k1.getKalpiLocation());
			pstmt.setString(3, k1.TypeStringForDB(k1.getType()));
			pstmt.setInt(4, k1.getNumOfVoters());
			pstmt.setInt(5, k1.getNumOfVotes());
			pstmt.setInt(6, e1.getYear());
			pstmt.setInt(7, e1.getMonth());
			pstmt.executeUpdate();

			// update the number of Kalpies
			sql = "UPDATE Election_t SET numOfKalpies = numOfKalpies + 1 "
					+ "WHERE ElectionYear = ? AND ElectionMonth = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e1.getYear());
			pstmt.setInt(2, e1.getMonth());
			pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void partyInsert(Party p1, Elections e1) {
		try {
			String sql = "INSERT INTO Party_t (PartyName, flagType, numOfCandidates, numOfVotes,"
					+ " Day, Month, Year, ElectionYear, ElectionMonth) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p1.getPartyName());
			String type = p1.TypeString(p1.getType()).toUpperCase();
			pstmt.setString(2, type);
			pstmt.setInt(3, p1.getNumOfCandidatesPerParty());
			pstmt.setInt(4, p1.getNumOfVotes());
			pstmt.setInt(5, p1.getDay());
			pstmt.setInt(6, p1.getMonth());
			pstmt.setInt(7, p1.getYear());
			pstmt.setInt(8, e1.getYear());
			pstmt.setInt(9, e1.getMonth());
			pstmt.executeUpdate();


			// update the number of parties
			sql = "UPDATE Election_t SET numOfParties = numOfParties + 1 "
					+ "WHERE ElectionYear = ? AND ElectionMonth = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e1.getYear());
			pstmt.setInt(2, e1.getMonth());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void voterInsert(Voter v1, Elections e1) {
		try {
			String sql;
			PreparedStatement pstmt;

			sql = "INSERT INTO Voter_t (VID, Name, BirthYear, isIsolation, hasMask,"
					+ " KalpiID, ElectionYear, ElectionMonth) VALUES (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, v1.getId());
			pstmt.setString(2, v1.getName());
			pstmt.setInt(3, v1.getBirthYear());
			pstmt.setBoolean(4, v1.getIsIsolation());
			pstmt.setBoolean(5, v1.getHasSuit());
			pstmt.setInt(6, v1.getKalpiID());
			pstmt.setInt(7, e1.getYear());
			pstmt.setInt(8, e1.getMonth());
			pstmt.executeUpdate();

			// update the number of Voters
			sql = "UPDATE Election_t SET numOfVoters = numOfVoters + 1 "
					+ "WHERE ElectionYear = ? AND ElectionMonth = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e1.getYear());
			pstmt.setInt(2, e1.getMonth());
			pstmt.executeUpdate();

			if (v1 instanceof Candidate) {
				sql = "INSERT INTO Candidate_t (VID, partyName, numOfDaysSick, ElectionYear, ElectionMonth ) VALUES (?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, v1.getId());
				pstmt.setString(2, ((Candidate)v1).getPartyName());
				pstmt.setInt(3, ((Candidate)v1).getNumOfDaysSick());
				pstmt.setInt(4, e1.getYear());
				pstmt.setInt(5, e1.getMonth());
				pstmt.executeUpdate();
				//insertVoterToKalpi(v1, e1);

				//if(e1.getNumOfParties() * (e1.getNumOfCandidates() - 1) < 120) {
				// update the number of Candidates
				sql = "UPDATE Election_t SET numOfCandidates = numOfCandidates + 1 "
						+ "WHERE ElectionYear = ? AND ElectionMonth = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, e1.getYear());
				pstmt.setInt(2, e1.getMonth());
				pstmt.executeUpdate();	

				for (int i = 0; i < e1.getNumOfParties(); i++) {
					String tempName = e1.getParties().elementAt(i).getPartyName();
					if(((Candidate)v1).getPartyName().contains(tempName))
					{
						insertCandidateToParty(e1.getParties().elementAt(i), e1);
					}
				}
				//}
			}
			if (v1 instanceof SoldierVoter) {
				sql = "INSERT INTO SoldierVoter_t (VID, isCarryingWeapon, isIsolation, hasMask, numOfDaysSick"
						+ " , ElectionYear, ElectionMonth) VALUES (?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, v1.getId());
				pstmt.setBoolean(2, ((SoldierVoter)v1).isCarryWeapon);
				pstmt.setBoolean(3, v1.getIsIsolation());
				pstmt.setBoolean(4, v1.getHasSuit());
				pstmt.setInt(5, ((SoldierVoter)v1).getNumOfDaysSick());
				pstmt.setInt(6, e1.getYear());
				pstmt.setInt(7, e1.getMonth());
				pstmt.executeUpdate();
				//insertVoterToKalpi(v1, e1);
			}
			if (v1 instanceof CoronaVoter) {
				sql = "INSERT INTO CoronaVoter_t (VID, numOfDaysSick, ElectionYear, ElectionMonth) VALUES (?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, v1.getId());
				pstmt.setInt(2, ((CoronaVoter)v1).numOfDaysSick);
				pstmt.setInt(3, e1.getYear());
				pstmt.setInt(4, e1.getMonth());
				pstmt.executeUpdate();
				//insertVoterToKalpi(v1, e1);
			}
			insertVoterToKalpi(v1, e1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// update individual Party number of candidates 
	private void insertCandidateToParty(Party p1, Elections e1) {
		try {
			String sql;
			PreparedStatement pstmt;
				// update the number of Candidates
				sql = "UPDATE Party_t SET numOfCandidates = numOfCandidates + 1 "
						+ "WHERE ElectionYear = ? AND ElectionMonth = ? AND PartyName = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, e1.getYear());
				pstmt.setInt(2, e1.getMonth());
				pstmt.setString(3, p1.getPartyName());
				pstmt.executeUpdate();	

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// update individual Kalpi number of voters
	private void insertVoterToKalpi(Voter v1, Elections e1) {
		try {
			String sql;
			PreparedStatement pstmt;
			// update the number of Candidates
			sql = "UPDATE Kalpi_t SET numOfVoters = numOfVoters + 1 "
					+ "WHERE ElectionYear = ? AND ElectionMonth = ? AND KalpiID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e1.getYear());
			pstmt.setInt(2, e1.getMonth());
			pstmt.setInt(3, v1.getKalpiID());
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// update number of votes to Party
	public void insertVotesToParty(Party p1, Elections e1) {
		try {
			String sql;
			PreparedStatement pstmt;
			// update the number of Candidates
			sql = "UPDATE Party_t SET numOfVotes = numOfVotes + 1 "
					+ "WHERE ElectionYear = ? AND ElectionMonth = ? AND PartyName = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e1.getYear());
			pstmt.setInt(2, e1.getMonth());
			pstmt.setString(3, p1.getPartyName());
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void updateElection(Elections e1) {
		try {
			String sql = "UPDATE Election_t SET ElectedParty = ? "
					+ "WHERE ElectionYear = ? AND ElectionMonth = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "'" + e1.getElected() + "'");
			pstmt.setInt(2, e1.getYear());
			pstmt.setInt(3, e1.getMonth());
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// update number of votes to Election
	public void insertVote(Elections e1)
	{
		try {
			String sql;
			PreparedStatement pstmt;
			// update the number of Candidates
			sql = "UPDATE Election_t SET numOfVotes = numOfVotes + 1 "
					+ "WHERE ElectionYear = ? AND ElectionMonth = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e1.getYear());
			pstmt.setInt(2, e1.getMonth());
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// update number of votes to Kalpi
	public void insertVotesToKalpi(Kalpi k1, int ElectionYear, int ElectionMonth) {
		try {
			String sql;
			PreparedStatement pstmt;
			// update the number of Candidates
			sql = "UPDATE Kalpi_t SET numOfVotes = numOfVotes + 1 "
					+ "WHERE ElectionYear = ? AND ElectionMonth = ? AND KalpiID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ElectionYear);
			pstmt.setInt(2, ElectionMonth);
			pstmt.setInt(3, k1.getKalpiId());
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private KalpiType StringToKalpiT(String str) {
		KalpiType kt;
		String temp = str.trim();
		if (temp.equals("NormalKalpi")) {
			kt = KalpiType.NormalKalpi;
			return kt;
		}
		if (temp.equals("CoronaKalpi")) {
			kt = KalpiType.CoronaKalpi;
			return kt;
		}
		if (temp.equals("ArmyKalpi")) {
			kt = KalpiType.ArmyKalpi;
			return kt;
		}
		if (temp.equals("ArmyCoronaKalpi")) {
			kt = KalpiType.ArmyCoronaKalpi;
			return kt;
		}
		
		return null;
	}
	
	private flag StringToFlagT(String str) {
		flag ft;
		String temp = str.trim();
		if (temp.equals("RIGHT")) {
			ft = flag.RIGHT;
			return ft;
		}
		if (temp.equals("MIDDLE")) {
			ft = flag.MIDDLE;
			return ft;
		}
		if (temp.equals("LEFT")) {
			ft = flag.LEFT;
			return ft;
		}
		
		return null;
	}
	
	
	public void updateNumberOfElections() {
		try {
			String sql;
			PreparedStatement pstmt;
				sql = "UPDATE ElectionSupervisor_t SET numOfElections = numOfElections + 1"
						+ " WHERE ID = ?";
				pstmt = conn.prepareStatement(sql);
				//pstmt.setInt(1, es.getNumOfElections() + 1);
				pstmt.setInt(1, 1);
				pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public int getNumberOfElections(ElectionSupervisor es) {
		try {
			String sql;
			PreparedStatement pstmt;
			ResultSet rs;
				sql = "SELECT * FROM ElectionSupervisor_t WHERE ID = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int numOfElections = rs.getInt("numOfElections");
					//es.setNumOfElections(numOfElections);
					return numOfElections;
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void insertElectionSupervisor() {
		try {
			String sql;
			PreparedStatement pstmt;
				sql = "INSERT INTO ElectionSupervisor_t(ID, numOfElections) VALUES (?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, 0);
				pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public void getAllPastElections(ElectionSupervisor es, int numOfElections) throws Exception {
		try {
			String sql;
			PreparedStatement pstmt;
			ResultSet rs;
				sql = "SELECT * FROM Election_t";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Elections e1 = new Elections();
					e1.setYear(rs.getInt("ElectionYear"));
					e1.setMonth(rs.getInt("ElectionMonth"));
					e1.setNumOfVotes(rs.getInt("numOfVotes"));
					e1.setElectedParty(rs.getString("ElectedParty"));		
					es.addElection(e1);
					
					getAllPartiesFromDB(e1);
					getAllKalpiesFromDB(e1);
					getAllVotersFromDB(e1);
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getAllVotersFromDB(Elections e1) throws Exception {
		try {
			String sql;
			ResultSet rs, checkVoters;
			int normalVoter = 0;
			PreparedStatement pstmt;
			sql = "SELECT * FROM Voter_t Where ElectionYear = " + e1.getYear() + " AND ElectionMonth = "
					+ e1.getMonth();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				normalVoter = 0;
				int vid = rs.getInt("VID");
				String name = rs.getString("Name");
				int birthYear = rs.getInt("BirthYear");
				boolean isIsolation = rs.getBoolean("isIsolation");
				boolean hasMask = rs.getBoolean("hasMask");
				int KalpiID = rs.getInt("KalpiID");
				sql = "SELECT * FROM Candidate_t Where ElectionYear = " + e1.getYear() + " AND ElectionMonth = "
						+ e1.getMonth() + " AND VID = " + vid;
				pstmt = conn.prepareStatement(sql);
				checkVoters = pstmt.executeQuery();
				if(checkVoters.next())
				{
					String temp = checkVoters.getString("partyName");
					String partyName = temp.trim();
					int numOfDaysSick = checkVoters.getInt("numOfDaysSick");
					normalVoter = 1;
					Candidate c1 = new Candidate(name, vid, birthYear, isIsolation, hasMask, KalpiID, numOfDaysSick, partyName);
					e1.addVoter(c1);
				}
				sql = "SELECT * FROM SoldierVoter_t Where ElectionYear = " + e1.getYear() + " AND ElectionMonth = "
						+ e1.getMonth() + " AND VID = " + vid;
				pstmt = conn.prepareStatement(sql);
				checkVoters = pstmt.executeQuery();
				if(checkVoters.next())
				{
					boolean isCarryingWeapon = checkVoters.getBoolean("isCarryingWeapon");
					int numOfDaysSick = checkVoters.getInt("numOfDaysSick");
					normalVoter = 2;
					SoldierVoter s1 = new SoldierVoter(name, vid, birthYear, isIsolation, hasMask, KalpiID, numOfDaysSick, isCarryingWeapon);
					if(e1.getYear() - birthYear < 21)
						e1.addVoter(s1);
					else
						normalVoter = 0;
				}
				sql = "SELECT * FROM CoronaVoter_t Where ElectionYear = " + e1.getYear() + " AND ElectionMonth = "
						+ e1.getMonth() + " AND VID = " + vid;
				pstmt = conn.prepareStatement(sql);
				checkVoters = pstmt.executeQuery();
				if(checkVoters.next())
				{
					int numOfDaysSick = checkVoters.getInt("numOfDaysSick");
					normalVoter = 3;
					CoronaVoter covid = new CoronaVoter(name, vid, birthYear, isIsolation, hasMask, KalpiID, numOfDaysSick);		
					e1.addVoter(covid);
				}
				
				if(normalVoter == 0)
				{
					Voter v1 = new Voter(name, vid, birthYear, isIsolation, hasMask, KalpiID, 0);
					e1.addVoter(v1);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getAllKalpiesFromDB(Elections e1) throws Exception {
		try {
			String sql = "SELECT * FROM Kalpi_t WHERE ElectionYear = " + e1.getYear() + " AND ElectionMonth = "
					+ e1.getMonth();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				int KalpiID = rs.getInt("KalpiID");
				String Location = rs.getString("Location");
				String KalpiType = rs.getString("KalpiType");
				Kalpi k1 = new Kalpi(StringToKalpiT(KalpiType), Location, KalpiID);
				e1.addKalpi(k1);
				for (int i = 0; i < e1.getNumOfKalpies(); i++) {
					if(KalpiID == e1.getKalpies().elementAt(i).getKalpiId())
							e1.getKalpies().elementAt(i).setNumOfVotes(rs.getInt("numOfVotes"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getAllPartiesFromDB(Elections e1) {
		try {
			String sql = "SELECT * FROM Party_t WHERE ElectionYear = " + e1.getYear() + " AND ElectionMonth = "
					+ e1.getMonth();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String temp = rs.getString("PartyName");
				String partyName = temp.trim();
				String flagType = rs.getString("flagType");
				int Year = rs.getInt("Year");
				int Month = rs.getInt("Month");
				int Day = rs.getInt("Day");
				Party p1 = new Party(partyName, StringToFlagT(flagType), Year, Month, Day);
				e1.addParty(p1);
				for (int i = 0; i < e1.getNumOfParties(); i++) {
					if(partyName.equals(e1.getParties().elementAt(i).getPartyName()))
						e1.getParties().elementAt(i).setNumOfVotesDB(rs.getInt("numOfVotes"));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public boolean selectElectionSupervisor() throws SQLException {
			String sql = "SELECT * FROM ElectionSupervisor_t";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
				return false;
		return true;
	}
}
