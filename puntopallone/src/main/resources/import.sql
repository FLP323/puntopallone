-- ================================================
-- ARBITRI
-- sequence arbitro_seq: nextval = 1, 51, 101, 151, 201
-- ================================================
insert into arbitro(id, nome, cognome, codice_arbitrale) values(nextval('arbitro_seq'), 'Pierluigi', 'Collina', 'PLGCLN1');   -- id=1
insert into arbitro(id, nome, cognome, codice_arbitrale) values(nextval('arbitro_seq'), 'Daniele', 'Orsato', 'DANORS1');      -- id=51
insert into arbitro(id, nome, cognome, codice_arbitrale) values(nextval('arbitro_seq'), 'Marco', 'Guida', 'MARCGUI1');       -- id=101
insert into arbitro(id, nome, cognome, codice_arbitrale) values(nextval('arbitro_seq'), 'Maurizio', 'Mariani', 'MAURMAR2');  -- id=151
insert into arbitro(id, nome, cognome, codice_arbitrale) values(nextval('arbitro_seq'), 'Fabio', 'Maresca', 'FABMAR3');     -- id=201

-- ================================================
-- SQUADRE
-- sequence squadra_seq: nextval = 1, 51, 101, 151, 201, 251
-- ================================================
insert into squadra(id, nome, anno_di_fondazione, città) values(nextval('squadra_seq'), 'Inter', 1908, 'Milano');      -- id=1
insert into squadra(id, nome, anno_di_fondazione, città) values(nextval('squadra_seq'), 'Milan', 1899, 'Milano');      -- id=51
insert into squadra(id, nome, anno_di_fondazione, città) values(nextval('squadra_seq'), 'Juventus', 1897, 'Torino');   -- id=101
insert into squadra(id, nome, anno_di_fondazione, città) values(nextval('squadra_seq'), 'Napoli', 1926, 'Napoli');     -- id=151
insert into squadra(id, nome, anno_di_fondazione, città) values(nextval('squadra_seq'), 'Roma', 1927, 'Roma');        -- id=201
insert into squadra(id, nome, anno_di_fondazione, città) values(nextval('squadra_seq'), 'Lazio', 1900, 'Roma');       -- id=251

-- ================================================
-- TORNEI
-- sequence torneo_seq: nextval = 1, 51, 101, 151, 201
-- ================================================
insert into torneo(id, nome, anno, descrizione) values(nextval('torneo_seq'), 'Serie A', 1898, 'Il massimo campionato italiano di calcio');                    -- id=1
insert into torneo(id, nome, anno, descrizione) values(nextval('torneo_seq'), 'Coppa Italia', 1922, 'La principale coppa nazionale italiana');                  -- id=51
insert into torneo(id, nome, anno, descrizione) values(nextval('torneo_seq'), 'UEFA Champions League', 1955, 'La massima competizione europea per club');      -- id=101
insert into torneo(id, nome, anno, descrizione) values(nextval('torneo_seq'), 'FIFA World Cup', 1930, 'Il campionato mondiale di calcio per nazionali');       -- id=151
insert into torneo(id, nome, anno, descrizione) values(nextval('torneo_seq'), 'Campionato Primavera', 1962, 'Campionato giovanile italiano');                 -- id=201

-- ================================================
-- GIOCATORI (con squadra di appartenenza)
-- sequence giocatore_seq: nextval = 1, 51, 101, 151, 201, 251, 301, 351, 401, 451, 501, 551, 601, 651, 701
-- Assegnazioni:
--   Milan (51): Maignan, Theo Hernández, Leão
--   Inter (1): Bastoni, Dimarco, Barella, Çalhanoğlu, Lautaro
--   Juventus (101): Di Lorenzo, Vlahovic
--   Napoli (151): Osimhen, Kvaratskhelia
--   Roma (201): Pellegrini, Dybala
--   Lazio (251): Berardi
-- ================================================
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Mike', 'Maignan', '1995-07-03', 'GOALKEEPER', 191, 51);                     -- id=1  (Milan)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Alessandro', 'Bastoni', '1999-04-13', 'CENTRE_BACK', 190, 1);              -- id=51 (Inter)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Federico', 'Dimarco', '1997-11-10', 'LEFT_BACK', 175, 1);                 -- id=101 (Inter)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Giovanni', 'Di Lorenzo', '1993-08-04', 'RIGHT_BACK', 183, 101);           -- id=151 (Juventus)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Nicolò', 'Barella', '1997-02-07', 'BOX_TO_BOX', 175, 1);                 -- id=201 (Inter)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Hakan', 'Çalhanoğlu', '1994-02-08', 'DEEP_LYING_PLAYMAKER', 178, 1);     -- id=251 (Inter)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Lorenzo', 'Pellegrini', '1996-06-19', 'ATTACKING_MIDFIELDER', 186, 201); -- id=301 (Roma)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Rafael', 'Leão', '1999-06-10', 'WINGER_LEFT', 188, 51);                 -- id=351 (Milan)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Domenico', 'Berardi', '1994-08-01', 'WINGER_RIGHT', 183, 251);          -- id=401 (Lazio)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Lautaro', 'Martínez', '1997-08-22', 'STRIKER', 174, 1);                -- id=451 (Inter)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Victor', 'Osimhen', '1998-12-29', 'STRIKER', 185, 151);                -- id=501 (Napoli)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Dusan', 'Vlahovic', '2000-01-28', 'STRIKER', 190, 101);               -- id=551 (Juventus)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Paulo', 'Dybala', '1993-11-15', 'SECOND_STRIKER', 177, 201);          -- id=601 (Roma)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Khvicha', 'Kvaratskhelia', '2001-02-12', 'WINGER_LEFT', 183, 151);   -- id=651 (Napoli)
insert into giocatore(id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_di_appartenenza_id)values(nextval('giocatore_seq'), 'Theo', 'Hernández', '1997-10-06', 'WING_BACK_LEFT', 184, 51);        -- id=701 (Milan)

-- ================================================
-- PARTITE
-- sequence partita_seq: nextval = 1, 51, 101, 151, 201, 251
-- ================================================
-- 1. Giocate (PLAYED)
insert into partita(id, dataeora_inizio_partita, luogo, goals_home, goals_away, stato, torneo_di_appartenenza_id, squadra_in_casa_id, squadra_in_trasferta_id, arbitro_in_carica_id)values(nextval('partita_seq'), '2026-05-20T18:00:00Z', 'Stadio Giuseppe Meazza', 2, 1, 'PLAYED', 1, 1, 51, 1);    -- id=1   Serie A: Inter-Milan, Collina
insert into partita(id, dataeora_inizio_partita, luogo, goals_home, goals_away, stato, torneo_di_appartenenza_id, squadra_in_casa_id, squadra_in_trasferta_id, arbitro_in_carica_id)values(nextval('partita_seq'), '2026-05-20T20:45:00Z', 'Allianz Stadium', 1, 1, 'PLAYED', 1, 101, 151, 51);       -- id=51  Serie A: Juventus-Napoli, Orsato
insert into partita(id, dataeora_inizio_partita, luogo, goals_home, goals_away, stato, torneo_di_appartenenza_id, squadra_in_casa_id, squadra_in_trasferta_id, arbitro_in_carica_id)values(nextval('partita_seq'), '2026-05-21T15:00:00Z', 'Stadio Olimpico', 3, 0, 'PLAYED', 1, 201, 251, 101);      -- id=101 Serie A: Roma-Lazio, Guida

-- 2. Da disputare (SCHEDULED)
insert into partita(id, dataeora_inizio_partita, luogo, goals_home, goals_away, stato, torneo_di_appartenenza_id, squadra_in_casa_id, squadra_in_trasferta_id, arbitro_in_carica_id)values(nextval('partita_seq'), '2026-05-25T18:00:00Z', 'Stadio Diego Armando Maradona', NULL, NULL, 'SCHEDULED', 51, 151, 1, 151);   -- id=151 Coppa Italia: Napoli-Inter, Mariani
insert into partita(id, dataeora_inizio_partita, luogo, goals_home, goals_away, stato, torneo_di_appartenenza_id, squadra_in_casa_id, squadra_in_trasferta_id, arbitro_in_carica_id)values(nextval('partita_seq'), '2026-05-25T20:45:00Z', 'Stadio Giuseppe Meazza', NULL, NULL, 'SCHEDULED', 101, 51, 101, 201);       -- id=201 Champions: Milan-Juventus, Maresca
insert into partita(id, dataeora_inizio_partita, luogo, goals_home, goals_away, stato, torneo_di_appartenenza_id, squadra_in_casa_id, squadra_in_trasferta_id, arbitro_in_carica_id)values(nextval('partita_seq'), '2026-05-26T21:00:00Z', 'Stadio Olimpico', NULL, NULL, 'SCHEDULED', 151, 251, 201, 1);             -- id=251 World Cup: Lazio-Roma, Collina

-- ================================================
-- ASSOCIAZIONE MANY-TO-MANY: TORNEO – SQUADRA
-- Tabella di join: torneo_squadre_partecipanti
-- Colonne (generate automaticamente):
--   tornei_partecipati_id   (FK verso Torneo)
--   squadre_partecipanti_id (FK verso Squadra)
-- ================================================
-- Serie A (1) → tutte le 6 squadre
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(1, 1);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(1, 51);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(1, 101);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(1, 151);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(1, 201);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(1, 251);

-- Champions League (101) → Inter, Milan, Juventus, Napoli
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(101, 1);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(101, 51);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(101, 101);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(101, 151);

-- Coppa Italia (51) → Inter, Milan, Juventus, Roma
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(51, 1);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(51, 51);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(51, 101);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(51, 201);

-- World Cup (151) → Roma, Lazio
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(151, 201);
insert into torneo_squadre_partecipanti(tornei_partecipati_id, squadre_partecipanti_id) values(151, 251);