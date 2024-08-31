## Come impostare correttamente il db

**1** - Scaricare db.sql e caricarlo sul vostro db (in esso è compreso la creazione delle tabelle, store procedure ed i dati in esso)
**2** - Creare due user specifici: 'artista' e 'spettatore'
**3** - Dare a questi due user il permesso per visualizzare il db 'strart' (che si è caricato precedentemente), solo privilegio di SELECT
**4** - Configurazione dei Permessi delle Stored Procedures: Eseguire i seguenti comandi SQL per concedere i permessi di esecuzione delle stored procedures a ciascun utente:
	
	GRANT EXECUTE ON PROCEDURE recuperaProfilo TO 'artista'@'localhost';
	GRANT EXECUTE ON PROCEDURE cercaEventi TO 'artista'@'localhost';
	GRANT EXECUTE ON PROCEDURE cercaEventi TO 'spettatore'@'localhost';
	GRANT EXECUTE ON PROCEDURE creaEvento TO 'artista'@'localhost';
	GRANT EXECUTE ON PROCEDURE visualizzaEventi TO 'artista'@'localhost';
	GRANT EXECUTE ON PROCEDURE partecipaEvento TO 'spettatore'@'localhost';
	GRANT EXECUTE ON PROCEDURE partecipaEvento TO 'artista'@'localhost';
	GRANT EXECUTE ON PROCEDURE eliminaEvento TO 'artista'@'localhost';
	GRANT EXECUTE ON PROCEDURE eliminaPartecipazioneEvento TO 'artista'@'localhost';
	GRANT EXECUTE ON PROCEDURE eliminaPartecipazioneEvento TO 'spettatore'@'localhost';

**5** - Aprire il file 'db.properties' nelle resources, nell'CONNECTION_URL inserire il proprio URL di connessione.
	In LOGIN_USER e LOGIN_PASS inserire le vostre credenziali base per l'accesso alla connessione db.


Il driver di connessione al db verrà scaricato automaticamente dato che è stato incluso nelle dipendenze del pom ma
se non funziona, includetelo mano. Tale file è presente nella cartella libs.

Ogni utente inserito nel db ha come password lo username: username = 'Luca'   password = 'Luca'
	