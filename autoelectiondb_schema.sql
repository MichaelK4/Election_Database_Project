create schema autoelectiondb;

#drop schema autoelectiondb;

use autoelectiondb;

CREATE TABLE ElectionSupervisor_t(
	ID INT PRIMARY KEY,
	numOfElections INT
);

CREATE TABLE Election_t (
    ElectionYear INT,
    ElectionMonth INT,
	numOfKalpies INT,
    numOfParties INT,
    numOfVoters INT,
    numOfCandidates INT,
    numOfVotes INT,
    ElectedParty VARCHAR(255),
    PRIMARY KEY (ElectionYear, ElectionMonth)
);

CREATE TABLE Kalpi_t (
    KalpiID INT,
    Location VARCHAR(255),
    KalpiType ENUM('CoronaKalpi', 'ArmyKalpi', 'NormalKalpi', 'ArmyCoronaKalpi'),
	numOfVoters INT,
    numOfVotes INT,
    ElectionYear INT,
    ElectionMonth INT,
    PRIMARY KEY (KalpiID, ElectionYear, ElectionMonth),
    FOREIGN KEY (ElectionYear, ElectionMonth) REFERENCES
    Election_t(ElectionYear, ElectionMonth)
);

CREATE TABLE Party_t (
    PartyName VARCHAR(255),
    flagType ENUM('LEFT', 'MIDDLE', 'RIGHT'),
    Day INT,
    Month INT,
    Year INT,
	numOfCandidates INT,
    numOfVotes INT,
    ElectionYear INT,
    ElectionMonth INT,
    PRIMARY KEY (PartyName, ElectionYear, ElectionMonth),
    FOREIGN KEY (ElectionYear, ElectionMonth) REFERENCES
    Election_t(ElectionYear, ElectionMonth)
);

CREATE TABLE Voter_t (
    VID INT,
    Name VARCHAR(255),
    BirthYear INT,
    isIsolation BOOLEAN,
    hasMask BOOLEAN,
    KalpiID INT,
    ElectionYear INT,
    ElectionMonth INT,
    FOREIGN KEY (KalpiID) REFERENCES Kalpi_t(KalpiID),
    PRIMARY KEY (VID,ElectionYear, ElectionMonth),
    FOREIGN KEY (ElectionYear, ElectionMonth) REFERENCES
    Election_t(ElectionYear, ElectionMonth)
);

CREATE TABLE CoronaVoter_t (
    VID INT,
    numOfDaysSick INT,
    ElectionYear INT,
    ElectionMonth INT,
    PRIMARY KEY (VID,ElectionYear, ElectionMonth),
    FOREIGN KEY (VID) REFERENCES Voter_t(VID),
    FOREIGN KEY (ElectionYear, ElectionMonth) REFERENCES
    Election_t(ElectionYear, ElectionMonth)
);

CREATE TABLE SoldierVoter_t (
    VID INT,
	isCarryingWeapon BOOLEAN,
    isIsolation BOOLEAN,
    hasMask BOOLEAN,
    numOfDaysSick INT,
	ElectionYear INT,
    ElectionMonth INT,
	PRIMARY KEY (VID,ElectionYear, ElectionMonth),
    FOREIGN KEY (VID) REFERENCES Voter_t(VID),
    FOREIGN KEY (ElectionYear, ElectionMonth) REFERENCES
    Election_t(ElectionYear, ElectionMonth)
);

CREATE TABLE Candidate_t (
    VID INT,
    partyName VARCHAR(255),
    numOfDaysSick INT,
    ElectionYear INT,
    ElectionMonth INT,
	PRIMARY KEY (VID,ElectionYear, ElectionMonth),
    FOREIGN KEY (VID) REFERENCES Voter_t(VID),
    FOREIGN KEY (partyName) REFERENCES Party_t(PartyName),
    FOREIGN KEY (ElectionYear, ElectionMonth) REFERENCES
    Election_t(ElectionYear, ElectionMonth)
);
