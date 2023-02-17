use autoelectiondb;

SELECT * FROM 
Electionsupervisor_t WHERE ID = 1;

SELECT * FROM 
election_t WHERE ElectionYear = 2023 AND ElectionMonth = 1;

SELECT * FROM 
election_t;

SELECT * FROM 
party_t WHERE ElectionYear = 2023 AND ElectionMonth = 1;

SELECT * FROM 
party_t;

SELECT * FROM 
kalpi_t WHERE ElectionYear = 2023 AND ElectionMonth = 1;

SELECT * FROM 
kalpi_t;

SELECT * FROM 
voter_t WHERE ElectionYear = 2023 AND ElectionMonth = 1;

SELECT * FROM 
voter_t;

SELECT * FROM 
soldiervoter_t WHERE ElectionYear = 2023 AND ElectionMonth = 1;

SELECT * FROM 
soldiervoter_t;

SELECT * FROM 
coronavoter_t WHERE ElectionYear = 2023 AND ElectionMonth = 1;

SELECT * FROM 
coronavoter_t;

SELECT * FROM 
candidate_t WHERE ElectionYear = 2023 AND ElectionMonth = 1;

SELECT * FROM 
candidate_t;
