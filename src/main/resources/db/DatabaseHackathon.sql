CREATE table Utente(
	idUtente int PRIMARY KEY,
	UserNUtente VARCHAR(20),
	emailUtente VARCHAR(30) UNIQUE,
	nomeUtente VARCHAR(20),
	cognomeUtente VARCHAR(20),
	passwordUtente VARCHAR(20),
)

CREATE table Giudici(
	idGiudice int PRIMARY KEY,
	UserNGiudice VARCHAR(20),
	emailGiudice VARCHAR(30) UNIQUE,
	nomeGiudice VARCHAR(20),
	cognomeGiudice VARCHAR(20),
)

CREATE table Partecipante(
	idPartecipante int PRIMARY KEY,
	UserNPartecipante VARCHAR(20),
	emailPartecipante VARCHAR(30) UNIQUE,
	nomePartecipante VARCHAR(20),
	cognomePartecipante VARCHAR(20),
)

CREATE table Team(
	idTeam int,
	HackathonId,
	FOREIGN KEY ()
	Foreign KEY (HackathonId) References Hackathon(idHackathon) ON DELETE CASCADE
)


CREATE table Hackathon(
	idHackathon int PRIMARY KEY,
	hTitolo VARCHAR(20),
	hSede VARCHAR(20),
	dataInizio DATE,
	dataFine DATE,
	maxIscritti int,
	maxPersoneTeam int,
	dataInizioIscr DATE,
	problema TEXT
)