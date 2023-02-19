//Name: Sagie Hershko ID:313333619//
//Name: Michael Kogan ID:318974672//

package Elections;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import Elections.Kalpi.KalpiType;
import Elections.Party.flag;

public class Main {
	public static final Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws IOError, Exception, IOException, EOFException, FileNotFoundException, ClassNotFoundException, InvalidClassException {

		int choice = 0;
		boolean exit = false;
		boolean isValidInput = false;
		boolean didYouDoIt = false;

		Elections e1 = new Elections();
 
		Party p1 = new Party("Corleone", Party.flag.RIGHT, 1934, 4, 21);
		Party p2 = new Party("Good Boys", Party.flag.LEFT, 2019, 3, 11);
		Party p3 = new Party("Inglourious Basterds", Party.flag.MIDDLE, 2009, 5, 20);

		e1.addParty(p1);
		e1.addParty(p2);
		e1.addParty(p3);

		Kalpi k1 = new Kalpi(Kalpi.KalpiType.NormalKalpi, "Hogwards Street");
		Kalpi k2 = new Kalpi(Kalpi.KalpiType.CoronaKalpi, "Tuborg Street");
		Kalpi k3 = new Kalpi(Kalpi.KalpiType.ArmyKalpi, "Los Santos Air Base");
		Kalpi k4 = new Kalpi(Kalpi.KalpiType.NormalKalpi, "Haha Street");
		Kalpi k5 = new Kalpi(Kalpi.KalpiType.ArmyCoronaKalpi, "Making Hitmen Base");

		e1.addKalpi(k1);
		e1.addKalpi(k2);
		e1.addKalpi(k3);
		e1.addKalpi(k4);
		e1.addKalpi(k5);

		Voter v1 = new SoldierVoter("Stewie Griffin", 127834679, 2000, false, false, 1002,0,false);// Army
		Voter v2 = new CoronaVoter("Abraham Lincoln", 123411119, 1809, true, true, 1001, 4);// Corona
		Voter v3 = new CoronaVoter("LeBron James", 423356789, 1984, true, true, 1001, 20);																			
		Voter v4 = new Voter("Bruce Wayne", 123456789, 1939, false, true, 1000,0);// Normal
		Voter v5 = new SoldierVoter("Dekel Vaknin", 123432781, 2001, true, true, 1004 ,5 ,true);

		e1.addVoter(v1);
		e1.addVoter(v2);
		e1.addVoter(v3);
		e1.addVoter(v4);
		e1.addVoter(v5);

		Candidate c1 = new Candidate("Vito Corleone", 982225845, 1891, false, false, 1003, 0, "Corleone");// normal
		Candidate c2 = new Candidate("Michael Corleone", 981712345, 1920, false, false, 1000, 0, "Corleone");// normal
		Candidate c3 = new Candidate("Snoop Dogg", 999725845, 1971, false, false, 1003, 0, "Good Boys");// normal
		Candidate c4 = new Candidate("Seth Rogen", 981711345, 1982, true, true, 1001, 2, "Good Boys");// normal
		Candidate c5 = new Candidate("Quentin Tarantino", 999915845, 1963, false, true, 1000, 0, "Inglourious Basterds");// normal
		Candidate c6 = new Candidate("Samuel L. Jackson", 441225845, 1948, false, true, 1003, 0, "Inglourious Basterds");// normal

		e1.addVoter(c1);
		e1.addVoter(c2);
		e1.addVoter(c3);
		e1.addVoter(c4);
		e1.addVoter(c5);
		e1.addVoter(c6);

		File f = new File("Elections.dat");
		File f1 = new File("WorkAgainFile.dat");
		StringBuffer sb=new StringBuffer();
		System.out.println("Hello and welcome to our project:\r\n"
				+ "We would like to Address you that when you write names of places or parties that are longer then one word\r\n"
				+ "Write them with '-' for example 'Inglourious-Basterds' or 'Los-Santos'");

		do {
			while(!didYouDoIt) {
				char YesOrNo;
				System.out.println("Did you alraedy exit the program and saved to file? type 'Y' as of Yes or 'N' as of No");
				YesOrNo = s.next().charAt(0);
				if (YesOrNo != 'Y' && YesOrNo != 'N') {
					do {
						System.out.println("You may have a typo try again..");
						YesOrNo = s.next().charAt(0);
					} while (YesOrNo != 'Y' && YesOrNo != 'N');
				}	
				if (YesOrNo == 'Y') {
					try {
						ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(f1));
						Vector<Voter> v = new Vector<Voter>();
						v= (Vector<Voter>) inFile.readObject();
						Vector <Kalpi> k = new Vector<Kalpi>();
						k = (Vector<Kalpi>) inFile.readObject();
						Vector<Party> p = new Vector<Party>();
						p = (Vector<Party>) inFile.readObject();
						inFile.close();
						e1.updatePartiesFromFile(p);
						e1.updateKalpiesFromFile(k);
						e1.updateVotersFromFile(v);
						didYouDoIt = true;
					}catch(FileNotFoundException | InvalidClassException e) {
						System.out.println("File does not exist you lied to us!!");
						didYouDoIt = true;
					}
				}else {
					didYouDoIt = true;
				}
				
			}
			while(!isValidInput) {
				try {	
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("Please select an option:\r\n" 
							+ " 1 - Add Kalpi \r\n" 
							+ " 2 - Add Voter\r\n"
							+ " 3 - Add Party\r\n" 
							+ " 4 - Add Candidate\r\n" 
							+ " 5 - Show all Kalpies\r\n"
							+ " 6 - Show all Voters\r\n" 
							+ " 7 - Show all Parties\r\n" 
							+ " 8 - Who you vote for?\r\n"
							+ " 9 - Election Results\r\n"
							+ "10 - Save to file\r\n"
							+ "11 - Load from files\r\n"
							+ "12 - Exit");
					choice = s.nextInt();
					isValidInput = true;
				} catch(InputMismatchException e) {
					System.out.println("You didn't enter a number or the number is too big");
					s.next();
				}
			}
			switch (choice) {
			//Save no matter what
			case 1:
				addKalp(s, e1);
				break;
			case 2:
				addVoter(s, e1);
				break;
			case 3:
				addPart(s, e1);
				break;
			case 4:
				addCandi(s, e1);
				break;
				//
			case 5:
				e1.showAllKalpies();
				break;
			case 6:
				e1.showAllVoters();
				break;
			case 7:
				e1.showAllParties();
				break;
				//Save if want to
			case 8:
				isVotingInComingElections(e1, s);
				break;
			case 9:
				showElectionsResult(e1, s,sb);
				break;
				//
			case 10:
				writeToFile(e1,f,sb);
				break;
			case 11: 
				readFromFile(e1,f,sb);
				break;
			case 12:
				char YesOrNo;
				System.out.println("Do you  want to save to file? type 'Y' as of Yes or 'N' as of No ");
				YesOrNo = s.next().charAt(0);
				if (YesOrNo != 'Y' && YesOrNo != 'N') {
					do {
						System.out.println("You may have a typo try again..");
						YesOrNo = s.next().charAt(0);
					} while (YesOrNo != 'Y' && YesOrNo != 'N');
				}	
				if (YesOrNo == 'Y') {
					ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(f1));
					outFile.writeObject(e1.getVoters().collection);
					outFile.writeObject(e1.getKalpies());
					outFile.writeObject(e1.getParties());
					outFile.writeObject(e1.getCandidates());
					outFile.close();
				}
				System.out.println("Do you want to run the Elections again? type 'Y' as of Yes or 'N' as of No ");
				YesOrNo = s.next().charAt(0);
				if (YesOrNo != 'Y' && YesOrNo != 'N') {
					do {
						System.out.println("You may have a typo try again..");
						YesOrNo = s.next().charAt(0);
					} while (YesOrNo != 'Y' && YesOrNo != 'N');
				}if (YesOrNo == 'Y') {
					e1.getVotes().clear();
					exit = false;
					didYouDoIt = false;
				}else {
					exit = true;
					System.out.println("Thanks for checking the answers!! We hope all of them are good enough :)");
				}	
				break;
			default:
				System.out.println("Wrong number try again..");
				break;
			}
			isValidInput = false;
		} while (!exit);
		s.close();
	}
	//case 1
	private static void addKalp(Scanner s, Elections e1) throws Exception, InputMismatchException {
		int Type = 0;
		boolean isValid = false;
		String type;

		do {
			while(!isValid) {
				try {
					System.out.println("Please select Kalpi type:");
					for (int i = 1; i <= KalpiType.values().length; i++) {
						type = getString(KalpiType.values()[i - 1], null);
						System.out.println(i + " - " + type);
					}
					Type = s.nextInt();
					isValid = true;
				}catch(InputMismatchException  e) { // | numberDoesntMatchInt
					System.out.println("You didn't enter a number");
					s.next();
				}
			}
			if (Type >= 1 && Type <= KalpiType.values().length) {
				type = getString(KalpiType.values()[Type - 1], null);
				System.out.println("Your Kalpi type is " + type);
				String streetOrBase = kalpiLocation(s, KalpiType.values()[Type - 1]);
				Kalpi k4 = new Kalpi(Kalpi.KalpiType.values()[Type - 1], streetOrBase);
				e1.addKalpi(k4);
			} else {
				System.out.println("Wrong number try again..");
				System.out.println();
			}
			isValid = false;
		} while (Type < 1 || Type > KalpiType.values().length);
	}
	// case 2
	private static void addVoter(Scanner s, Elections e1) throws Exception, InputMismatchException {
		String name;
		String sureName;
		String fullName;
		Vector<Kalpi> tempCorona = e1.getKalpiByType(KalpiType.values()[0]);
		Vector<Kalpi> tempArmy = e1.getKalpiByType(KalpiType.values()[1]);
		Vector<Kalpi> tempNormal = e1.getKalpiByType(KalpiType.values()[2]);
		Vector<Kalpi> tempArmyCorona = e1.getKalpiByType(KalpiType.values()[3]);
		boolean isValid = false;
		char isIsolated = 0;
		char hasSuit = 'N';
		char carryWeapon = 0;
		int KalpiID = 0;
		int numOfDaysSick = 0;
		int voterID = checkID(s, e1);
		if (voterID != -1) {
			int birthYear = canBeVoter(s);
			if (birthYear != -1) {
				isIsolated = checkIsolation(s);
				if (isIsolated == 'Y') {
					hasSuit = hasSuit(s);
				}
				if (isSoldier(birthYear) == true) {
					carryWeapon = isCarryWeapon(s);
					if (isIsolation(isIsolated) == true) {
						while(!isValid) {
							try {
								System.out.println("How many days are you sick?");
								numOfDaysSick = s.nextInt();
								isValid = true;
								if (numOfDaysSick < 0) {
									System.out.println("You can not enter a negative number of days");
									isValid = false;
									s.next();
								}
								
							}catch(InputMismatchException e) {
								System.out.println("You didn't enter a number or the number is too big");
								s.next();
							}
						}
						if (isIsolation(hasSuit) == true) {
							KalpiID = getKalpiIDforVoter(s, tempArmyCorona);
						}else {
							System.out.println("You are isolated but don't have a suit.\n"
									+ "You have the right to vote but can't come to any Corona Kalpies");
							KalpiID = -1;
						}
					}else {
						//regular soldier
						KalpiID = getKalpiIDforVoter(s, tempArmy);
					}
				}else {
					if (isIsolation(isIsolated) == true) {
						while(!isValid) {
							try {
								System.out.println("How many days are you sick?");
								numOfDaysSick = s.nextInt();
								isValid = true;
								if (numOfDaysSick < 0) {
									System.out.println("You can not enter a negative number of days");
									isValid = false;
									s.next();
								}
								
							}catch(InputMismatchException e) {
								System.out.println("You didn't enter a number or the number is too big");
								s.next();
								
							}
						}
						if (isIsolation(hasSuit) == true) {
							KalpiID = getKalpiIDforVoter(s, tempCorona);
						}else {
							System.out.println("You are isolated but don't have a suit.\n"
									+ "You have the right to vote but can't come to any Corona Kalpies");
							KalpiID = -1;
						}
					}else {
						KalpiID = getKalpiIDforVoter(s, tempNormal);
					}
				}
				if (KalpiID != -1) {
					System.out.println("Enter your first name: ");
					name = s.next();
					System.out.println("Enter your surename: ");
					sureName = s.next();
					fullName = name + " " + sureName;
					if (isSoldier(birthYear) == true) {
						Voter s1 = new SoldierVoter(fullName, voterID, birthYear,
								isIsolation(isIsolated), isIsolation(hasSuit), KalpiID,numOfDaysSick,isIsolation(carryWeapon));		
						e1.addVoter(s1);
					}else if(isSoldier(birthYear) == false && isIsolation(isIsolated) == true) {
						Voter coviVo1 = new CoronaVoter(fullName, voterID, birthYear,
								isIsolation(isIsolated), isIsolation(hasSuit), KalpiID, numOfDaysSick);
						e1.addVoter(coviVo1);
					}else if (isIsolation(isIsolated) == false && isSoldier(birthYear) == false) {
						Voter v1 = new Voter(fullName, voterID, birthYear,
								isIsolation(isIsolated), isIsolation(hasSuit), KalpiID, numOfDaysSick);
						e1.addVoter(v1);
					}
				}
			}
		}
	}
	// case 3
	private static void addPart(Scanner s, Elections e1) throws InputMismatchException{
		int flagType = 0;
		boolean isValid = false;
		String type;
		do {
			while(!isValid) {
				try {
					System.out.println("Please select the flag of your party:");
					for (int i = 1; i <= flag.values().length; i++) {
						type = getString(null ,flag.values()[i - 1]);
						System.out.println(i + " - " + type);
					}
					flagType = s.nextInt();
					if (flagType >= 1 && flagType <= flag.values().length) {
						type = getString(null, flag.values()[flagType - 1]);
						System.out.println("Your party type is " + type + " party.");
						System.out.println("Enter party name:");
						String partyName = s.next();
						while(!isValid) {
							try {
								System.out.println("Enter the year that the party was created: ");
								int year = s.nextInt();
								System.out.println("Enter the month that the party was created: ");
								int month = s.nextInt();
								System.out.println("Enter the day that the party was created: ");
								int day = s.nextInt();
								isValid = true;
								Party p4 = new Party(partyName, Party.flag.values()[flagType - 1], year, month, day);
								e1.addParty(p4);
							}catch(InputMismatchException e) {
								System.out.println("You didn't enter a number or the number is too big");
								s.next();
							}
						}
					} else {
						System.out.println("Wrong number try again..");
						System.out.println();
					}
				}catch(InputMismatchException e) {
					System.out.println("You didn't enter a number or the number is too big");
					s.next();
				}
			}
		} while (flagType < 1 || flagType > flag.values().length);
	}
	// case 4
	private static void addCandi(Scanner s, Elections e1) throws Exception, InputMismatchException {
		String name;
		String sureName;
		String fullName = null;
		String partyName;
		Vector<Kalpi> tempCorona = e1.getKalpiByType(KalpiType.values()[0]);
		Vector<Kalpi> tempNormal = e1.getKalpiByType(KalpiType.values()[2]);
		boolean isValid = false;
		char isIsolated = 0;
		char hasSuit = 'N';
		int KalpiID = 0;
		int numDaysSick = 0;
		int candidateID = checkID(s, e1);
		if (candidateID != -1) {
			int birthYear = canBeCandidate(s);
			if (birthYear != -1) {
				isIsolated = checkIsolation(s);
				if (isIsolation(isIsolated) == true) {
					hasSuit = hasSuit(s);
				}	
				if (isIsolation(isIsolated) == true) {
					while(!isValid) {
						try {
							System.out.println("How many days are you sick?");
							numDaysSick = s.nextInt();
							isValid = true;
							if (numDaysSick < 0) {
								System.out.println("You can not enter a negative number of days");
								isValid = false;
								s.next();
							}
							
						}catch(InputMismatchException e) {
							System.out.println("You didn't enter a number or the number is too big");
							s.next();
						}
					}
					if (isIsolation(hasSuit) == true) {
						KalpiID = getKalpiIDforVoter(s, tempCorona);
					} else {
						System.out.println("You are isolated but don't have a suit.\n"
								+ "You have the right to vote but can't come to any Corona Kalpies.\n");
						KalpiID = getKalpiIDforVoter(s, tempCorona);
					}
				} else if (isIsolation(isIsolated) == false) {
					KalpiID = getKalpiIDforVoter(s, tempNormal);
				}
				System.out.println("Enter your first name: ");
				name = s.next();
				System.out.println("Enter your surename: ");
				sureName = s.next();
				fullName = name + " " + sureName;
				partyName = getPartyName(s, e1);
				Candidate c7 = new Candidate(fullName, candidateID, birthYear, isIsolation(isIsolated),
						isIsolation(hasSuit), KalpiID, numDaysSick, partyName);
				e1.addVoter(c7);
			}
		}
	}
	// case 8
	private static void isVotingInComingElections(Elections e1, Scanner Vote) {
		Set <Voter> voters = e1.getVoters();
		Vector<Kalpi> kalpies = e1.getKalpies();
		char choice;
		for (int i = 0; i < e1.getNumOfVoters(); i++) {
			if (checkCanVote(voters.collection.elementAt(i)) == true) {
				System.out.println("Hello " + voters.collection.elementAt(i).getName());
				choice = checkWantToVote(Vote);
				if (choice == 'Y') {
					voters.collection.elementAt(i).setWantToVote(true);
					System.out.println("vote in kalpi: " + e1.getKalpiAdressByid(voters.collection.elementAt(i).getKalpiID()));
					System.out.println("------------------------------------");
					String partyName = e1.printPartiesName(Vote, voters.collection.elementAt(i).getKalpiID());
					e1.addVote(partyName);
				} else if(choice == 'N'){
					voters.collection.elementAt(i).setWantToVote(false);
					continue;
				}
			}
		}
		for (int i = 0; i < e1.getNumOfKalpies(); i++) {
			e1.AddVotesToKalpi(kalpies.elementAt(i));
		}
	}
	// case 9
	private static void showElectionsResult(Elections e1, Scanner s,StringBuffer StoreResults)  throws ArrayIndexOutOfBoundsException{
		try {
		Vector<Party> parties = e1.getParties();
		Vector<String> votes = e1.getVotes();
		Vector<String> partiesName = new Vector<String>(e1.getNumOfParties());
		Vector<Kalpi> kalpies = e1.getKalpies();
		Vector<Integer> counterVotesForParty = new Vector<Integer>(e1.getNumOfParties());
		Vector<Integer> counterForEachKalpi = new Vector<Integer>(e1.getNumOfParties());
		int indexElection = 0;
		int partyIndex = 0;
		int numOfVotes = e1.getNumOfVotes();
		for (int i = 0; i < e1.getNumOfParties(); i++) {
			partiesName.add(i,parties.elementAt(i).getPartyName());
		}
		for (int i = 0; i < numOfVotes; i++) {
			while (partyIndex < partiesName.size()) {
				counterVotesForParty.add(indexElection, countVotesForElection(partiesName.elementAt(partyIndex), votes,numOfVotes));
				partyIndex++;
				indexElection++;
			}
		}
		partyIndex=0;
		for (int i = 0; i < e1.getNumOfKalpies(); i++) {
			counterForEachKalpi = initArray(counterForEachKalpi);  
			votes=kalpies.elementAt(i).getVotesInKalpi();
			for (int j = 0; j < e1.getNumOfParties(); j++) {
				counterForEachKalpi.add(j, countVotesForParty(partiesName.elementAt(j), votes, kalpies.elementAt(i).getNumOfVotes()));	
			}
			StoreResults.append("Kalpi ID: "+ kalpies.elementAt(i).getKalpiId() + "\n");
			System.out.println("Kalpi ID: "+ kalpies.elementAt(i).getKalpiId() );
			StoreResults.append("Percentage of votes in Kalpi " + kalpies.elementAt(i).getKalpiId() + ": " + kalpies.elementAt(i).getPercentOfVoters()+"%" + "\n");
			System.out.println("Percentage of votes in Kalpi " + kalpies.elementAt(i).getKalpiId() + ": " + kalpies.elementAt(i).getPercentOfVoters()+"%" + "\n");
			printCounterArray(counterForEachKalpi, partiesName,StoreResults);
			StoreResults.append("------------------------- \n");
		}
		StoreResults.append("------------------------- \n");
		System.out.println("-------------------------");
		StoreResults.append("Final Results:  \n");
		System.out.println("Final Results: ");
		printCounterArray(counterVotesForParty, partiesName,StoreResults);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("You have not entered votes in case 8");
		}
	}
	//case 10
	private static void writeToFile(Elections e1,File f,StringBuffer StoreResult) throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(f));
		outFile.writeObject(e1.getVoters().collection);
		outFile.writeObject(e1.getKalpies());
		outFile.writeObject(e1.getParties());
		outFile.writeObject(e1.getCandidates());
		outFile.writeObject(StoreResult);
		outFile.close();

	}
	//case 11
	private static void readFromFile(Elections e1, File f,StringBuffer StoreResult) throws FileNotFoundException, IOException, ClassNotFoundException {
		//check if we need to load from file after runnig again
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(f));
		Vector<Voter> v = new Vector<Voter>();
		v= (Vector<Voter>) inFile.readObject();
		System.out.println("Voters: ");
		System.out.println(v);
		System.out.println("~~~~~~~~~");
		Vector <Kalpi> k = new Vector<Kalpi>();
		k = (Vector<Kalpi>) inFile.readObject();
		System.out.println("Kalpies: ");
		System.out.println(k);
		System.out.println("~~~~~~~~~");
		Vector<Party> p = new Vector<Party>();
		p = (Vector<Party>) inFile.readObject();
		System.out.println("Parties: ");
		System.out.println(p);
		System.out.println("~~~~~~~~~");
		Vector<Candidate> c = new Vector<Candidate>();
		c = (Vector<Candidate>) inFile.readObject();
		System.out.println("Candidates");
		System.out.println(c);
		System.out.println("~~~~~~~~~");
		System.out.println("Election Results: ");
		StoreResult= (StringBuffer) inFile.readObject();
		System.out.println(StoreResult);
		inFile.close();	
	}
	// Methods
	private static void printCounterArray(Vector<Integer> counterForEachKalpi,Vector<String> partiesName,StringBuffer StoreResults) {
		for (int i = 0; i < counterForEachKalpi.size(); i++) {
			StoreResults.append(partiesName.elementAt(i) + " Party got " + counterForEachKalpi.elementAt(i) + " Vote(s) \n");
			System.out.println(partiesName.elementAt(i) + " Party got " + counterForEachKalpi.elementAt(i) + " Vote(s) \n");
		}
	}
	private static Vector<Integer> initArray(Vector<Integer> counterForEachKalpi) {
		int size = counterForEachKalpi.size();
		Vector<Integer> temp = new Vector<Integer>(size);

		return temp; 
	}
	private static int countVotesForParty(String PartyName, Vector<String> votes,int numOfVotes) {//KALPI
		int counter = 0;
		String PartyP;
		for (int indexVotes = 0; indexVotes < numOfVotes; indexVotes++) {
			String[] tempsplit = votes.elementAt(indexVotes).split(" ");// SPLIT
			Vector<String> vec = new Vector<String>();
			vec.addAll(Arrays.asList(tempsplit));	
			if(vec.size() == 2) {
				PartyP = vec.elementAt(0)+ " " + vec.elementAt(1);
			}else {
				PartyP = vec.elementAt(0);
			}
			if (PartyName.equals(PartyP)) {
				counter++;
			}
		}
		return counter;
	}/////
	private static int countVotesForElection(String PartyName, Vector<String> votes,int numOfVotes) {//ELECTION
		int counter = 0;
		String PartyP;
		for (int indexVotes = 0; indexVotes < numOfVotes; indexVotes++) {
			String[] tempsplit = votes.elementAt(indexVotes).split(" ");
			Vector<String> vec =new Vector<String>();
			vec.addAll(Arrays.asList(tempsplit));
			if(vec.size() > 2) {
				PartyP=vec.elementAt(0)+ " " + vec.elementAt(1);
			}
			else {
				PartyP = vec.elementAt(0);
			}
			if (PartyName.equals(PartyP)) {
				counter++;
			}
		}
		return counter;
	}
	private static boolean checkCanVote(Voter voter) {
		if (voter.getIsIsolation() == true && voter.getHasSuit() == true || voter.getIsIsolation() == false) {
			return true;
		}
		return false;
	}
	private static int checkID(Scanner s, Elections e1) throws InputMismatchException {
		int ID = 0;
		int length = 0;
		int copyID = 0;
		boolean isValid = false;
		do {
			while(!isValid) {
				try {
					System.out.println("Enter your voter's ID(Must be 9 digits and can not start with a zero if will you will have to input again):");
					ID = s.nextInt();
					isValid = true;
					length = String.valueOf(ID).length();
					if (length != 9) {
						System.out.println("Number ID should be only 9 digits long try again");
						isValid = false;
					}
				}catch(InputMismatchException e) {
					System.out.println("You didn't enter a number or the number is too big");
					s.next();
				}
			}
		} while (length != 9 || copyID == ID);
		for (int i = 0; i < e1.getNumOfVoters(); i++) {
			if (e1.getVoters().collection.elementAt(i).getId() == ID) {
				copyID = e1.getVoters().collection.elementAt(i).getId();
				System.out.println("This ID is already in use");
				return copyID = -1;
			}
		}
		return ID;
	}
	private static boolean isSoldier(int voterBirthYear) {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		if (year - voterBirthYear >= 18 && year - voterBirthYear <= 21) {
			return true;
		}
		return false;
	}
	private static boolean isIsolation(char isolation) {
		if (isolation == 'Y') {
			return true;
		} else {
			return false;
		}

	}
	private static int canBeVoter(Scanner s) throws InputMismatchException {
		System.out.println("Enter birth year of voter:");
		LocalDate localDate = LocalDate.now();
		boolean isValid = false;
		int voterBirthYear = 0;
		int year = localDate.getYear();
		while(!isValid) {
			try {
				voterBirthYear = s.nextInt();
				isValid = true;
			}catch(InputMismatchException e) {
				System.out.println("You didn't enter a number or the number is too big");
				s.next();
			}
		}


		if (year - voterBirthYear < 18) {
			System.out.println("You should be 18 years old to vote come back in " + (18 - (year - voterBirthYear))
					+ " year/s you are older.");
			voterBirthYear = -1;
			return voterBirthYear;
		}
		return voterBirthYear;
	}
	private static int canBeCandidate(Scanner s) throws InputMismatchException {
		System.out.println("Enter birth year of candidate:");
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		boolean isValid = false;
		int candiBirthYear = 0;

		while(!isValid) {
			try {
				candiBirthYear = s.nextInt();
				isValid = true;
			}catch(InputMismatchException e) {
				System.out.println("You didn't enter a number or the number is too big");
				s.next();
			}
		}
		if (year - candiBirthYear < 18) {
			System.out.println("You are only " + (year - candiBirthYear) + " years old you can't even be a voter.");
			return candiBirthYear = -1;
		}
		if (year - candiBirthYear <= 21) {
			System.out.println("You are " + (year - candiBirthYear)
					+ " years old and probably still in the army(unless you escaped or something).\r\n"
					+ "You should be 22 or older to be a candidate.\r\n" + "Maybe in " + (22 - (year - candiBirthYear))
					+ " year(s) you will be a candidate.");
			return candiBirthYear = -1;
		}
		return candiBirthYear;
	}
	private static int getKalpiIDforVoter(Scanner s, Vector<Kalpi> kalpArray) throws InputMismatchException {
		int KalpiID = 0;
		int choice = 0;
		int length = 0;
		boolean isValid = false;
		for (int i = 0; i < kalpArray.size(); i++) {
			if(kalpArray.elementAt(i) != null){
				length++;
			}
		}
		if (length == 1) {
			KalpiID = kalpArray.elementAt(0).getKalpiId();
			return KalpiID;
		} else if (length > 1) {

			System.out.println("Please select the Kalpi you want to come to:");
			for (int i = 1; i <= length; i++) {
				System.out.println(i + " - Kalpi Location: " + kalpArray.elementAt(i - 1).getKalpiLocation());
			}
			do {
				while(!isValid) {
					try {
						choice = s.nextInt();
						isValid = true;
						if (choice >= 1 && choice <= length) {
							KalpiID =  kalpArray.elementAt(choice - 1).getKalpiId();
						} else {
							System.out.println("Wrong number try again..");
							System.out.println();
						}
					}catch(InputMismatchException e) {
						System.out.println("You didn't enter a number or the number is too big");
						s.next();
					}

				}

			} while (choice < 1 || choice > length);

		}
		return KalpiID;
	}
	private static char hasSuit(Scanner s) {
		char hasSuit;
		System.out.println("Do you have a Suit?");
		System.out.println("Please enter 'Y' as of Yes and 'N' as of No");
		hasSuit = s.next().charAt(0);
		if (hasSuit != 'Y' && hasSuit != 'N') {
			do {
				System.out.println("You may have a typo try again..");
				hasSuit = s.next().charAt(0);
			} while (hasSuit != 'Y' && hasSuit != 'N');
		}
		return hasSuit;
	}
	private static char checkIsolation(Scanner s) {
		char isIsolated;
		System.out.println("Are you in isolation?");
		System.out.println("Please enter 'Y' as of Yes and 'N' as of No");
		isIsolated = s.next().charAt(0);
		if (isIsolated != 'Y' && isIsolated != 'N') {
			do {
				System.out.println("You may have a typo try again..");
				isIsolated = s.next().charAt(0);
			} while (isIsolated != 'Y' && isIsolated != 'N');
		}

		return isIsolated;
	}
	private static char isCarryWeapon(Scanner s) {
		char carryWeapon;
		System.out.println("Do you carry a weapon?");
		System.out.println("Please enter 'Y' as of Yes and 'N' as of No");
		carryWeapon = s.next().charAt(0);
		if (carryWeapon != 'Y' && carryWeapon != 'N') {
			do {
				System.out.println("You may have a typo try again..");
				carryWeapon = s.next().charAt(0);
			} while (carryWeapon != 'Y' && carryWeapon != 'N');
		}

		return carryWeapon;
	}
	private static String getPartyName(Scanner s2, Elections e1) throws InputMismatchException {
		int num = 0;
		boolean isValid = false;
		do {
			while(!isValid) {
				try {
					System.out.println("Select party you want your candidate to join");
					for (int i = 1; i <= e1.getNumOfParties(); i++) {
						System.out.println(i + " - " + e1.getParties().elementAt(i - 1).getPartyName());
					}
					num = s.nextInt();
					isValid = true;
					if (num < 1 || num > e1.getNumOfParties()) {
						System.out.println("Wrong number try again..");
						System.out.println();
					}
				}catch(InputMismatchException e) {
					System.out.println("You didn't enter a number or the number is too big");
					s.next();
				}
			}
		} while (num < 1 || num > e1.getNumOfParties());
		String partyName = e1.getParties().elementAt(num - 1).getPartyName();
		System.out.println("You joined the " + e1.getParties().elementAt(num - 1).getPartyName() + " party");
		return partyName;
	}
	private static String kalpiLocation(Scanner s ,KalpiType kalpiType) throws InputMismatchException{
		String streetOrBase;
		String typeOfLocation = null;
		Vector<String> Base=new Vector<String>();
		boolean isValid= false;
		Base.add(" Air Base");
		Base.add(" Naval Base");
		if (kalpiType == KalpiType.ArmyKalpi || kalpiType == KalpiType.ArmyCoronaKalpi) {
			System.out.println("Enter your base name: ");
			streetOrBase = s.next();
			int n = 0;
			do{
				while(!isValid) {
					try {
						System.out.println("Pick what kind of base is it(if not any of them match you enter '0'):");
						for (int i = 1; i <= Base.size(); i++) {
							System.out.println(i + " - " + Base.elementAt(i - 1));
						}
						n = s.nextInt();
						isValid = true;
						if(n == 0 || (n <=2 && n >= 1)) {
							if(n == 0) {
								typeOfLocation = " Base";
								return streetOrBase + typeOfLocation;
							}else {
								System.out.println("Your base type is " + Base.elementAt(n - 1));
								typeOfLocation = Base.elementAt(n - 1);
								System.out.println("Your base name is " + streetOrBase + typeOfLocation);
								return streetOrBase + typeOfLocation;
							}
						}else{
							System.out.println("Wrong number try again");
						}
					}catch(InputMismatchException e) {
						System.out.println("You didn't enter a number or the number is too big");
						s.next();
					}
				}
			}while(n < 1 || n != 0  || n > 2);
		}else {
			System.out.println("Enter your street name: ");
			streetOrBase = s.next();
			typeOfLocation = " Street";
			System.out.println("Your street name is " + streetOrBase + typeOfLocation);
			return streetOrBase + typeOfLocation;
		}
		return null;
	}
	private static String getString(KalpiType kalpiType, flag flagType) {
		String type;
		if (kalpiType == KalpiType.NormalKalpi) {
			type = "Normal Kalpi";
			return type;
		}else if(kalpiType == KalpiType.CoronaKalpi) {
			type = "Corona Kalpi";
			return type;
		}else if(kalpiType == KalpiType.ArmyKalpi) {
			type = "Army Kalpi";
			return type;
		}
		else if(kalpiType == KalpiType.ArmyCoronaKalpi) {
			type = "Army Corona Kalpi";
			return type;
		}
		if (flagType == flag.LEFT) {
			type = "Left";
			return type;
		}else if(flagType == flag.MIDDLE) {
			type = "Middle";
			return type;
		}else if(flagType == flag.RIGHT) {
			type = "Right";
			return type;
		}
		return null;
	}
	private static char checkWantToVote(Scanner Vote) {
		char isVoting;
		//boolean isValid = false;
		System.out.println("Do you want to vote? press Y-Yes or N-No");
		isVoting = Vote.next().charAt(0);
		if (isVoting != 'Y' && isVoting != 'N') {
			do {
				System.out.println("You may have a typo try again..");
				isVoting = Vote.next().charAt(0);
			}while(isVoting != 'Y' && isVoting != 'N');
		}
		return isVoting;
	}
	
}