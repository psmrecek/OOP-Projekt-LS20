Tema projektu: Zdravotnicky system evidencie lekarov, pacientov, vymennych listkov a predpisov

K priebeznemu odovzdaniu je dostupny diagram tried, ktory zahrna vsetko okrem GUI. Bol vygenerovany programom The ObjectAid UML Explorer.

Poznamky k uzivatelskemu rozhraniu a pokyny k jeho pouzivaniu:

Program sa spusta suborom PrihlasovacieOknoGUI

Po spusteni programu sa zobrazi moznost prihlasit sa. Prihlasit sa da v roznych roliach.

Ak sa chcete prihlasit ako Manazer Poistovne, zaskrtnite radiobutton 'Poistovna' v prihlasovacom okne, zadajte 
prihlasovacie meno: admin
heslo: 123
a kliknite na tlacidlo 'Prihlasit sa'.
Otvori sa okno manazera poistovne. 
Kliknutim na tlacidlo 'Vypisat lekarov' sa v listview vypisu vsetci lekari patriaci pod poistovnu.
Kliknutim na tlacidlo 'Pridat lekara' sa vytvori v poistovni novy lekar s udajmi, ktore sa zadaju do textfieldov a pripadne mozete vybrat specializaciu.
Tlacidlo 'Vymazat pole' vymaze listview lekarov. Tlacidlo 'Vycisti' vymaze obsah textfieldov a ostatnych prvkov.
Tlacidlo 'Ulozit' spusti serializaciu a ulozi vykonane zmeny tak, aby sa dali pouzivat aj pri opatovnom otvoreni programu.
Tlacidlo 'Reset' vymaze serializovane zaznamy a vygeneruje nove. Toto sa vo finalnej verzii nebude nachadzat, ale teraz je potrebne kvoli debuggingu.

Ak sa chcete prihlasit ako vseobecny lekar, zaskrtnite radiobutton 'Lekar' v prihlasovacom okne a zadajte
prihlasovacie meno: arnost
heslo: aaa
V evidencii su 3 lekari, Arnost, Beata a Cyril. Prihlasovacie meno kazdeho z nich je jeho krstne meno malymi pismenami a prve pismeno jeho mena trikrat.
Po prihlaseni sa otvori okno lekarskych zaznamov.
Tlacidlo 'Vypisat zoznam pacientov' vypise zoznam lekarovych pacientov do listview a do textarea 'Akcie' vypise hlasenie. V listview je meno pacienta 
a boolovska hodnota, ci ma vydany vymenny listok, alebo nie. Niektori pacienti ho uz vydany maju, ini, nie.
Tlacidlo 'Vydat pacientovi vymenny listok' vyda pacientovi vymenny listok. Vzhladom na priebezne odovzdanie je vymenny listok iba boolean hodnota,
ktoru toto tlacidlo pacientovi zmeni.
Tlacidlo 'Vydat predpis' v buducej verzii programu vyda pacientovi predpis s textom, ktory je zadany v textarea 'Text Predpisu'.
Tlacidlo 'Ulozit' spusti serializaciu a ulozi zmeny.

Ak sa chcete prihlasit ako specializovany lekar, zaskrtnite radiobutton 'Lekar' v prihlasovacom okne a zadajte
prihlasovacie meno: cyril
heslo: ccc
Po prihlaseni sa otvori okno lekarskych zaznamov a okno sa sprava tak isto, ako okno vseobecneho lekara. Rozdiel bude spravanie po kliknuti na 
tlacidlo 'Vydat predpis'. Toto vsak pri priebeznom hodnoteni este nie je implementovane.

Ak sa chcete prihlasit ako pacient, zaskrtnite radiobutton 'Pacient' v prihlasovacom okne a zadajte
prihlasovacie meno: d
heslo: a
V evidencii je 9 pacientov. Kazdeho prihlasovacie meno je jedno pismeno z intervalu <d-l> a heslo je vzdy male a.
Po prihlaseni sa otvori okno pacienta. Pacient v nom vidi svoje osobne udaje, ktore nemoze upravovat. V buducom vydani programu ale bude moct upravit svoju adresu.
Tlacidlo 'Vypisat vsetkych lekarov' do listview vypise vsetkych lekarov poistovne a ich specializaciu. Pacient si vyberie lekara kliknutim na jeho meno.
Tlacidlo 'Zapisat sa k vybranemu lekarovi' zapise pacienta k lekarovi. Pacient sa moze zapisat k akemukolvek vseobecnemu lekarovi.
Ak sa pacient, ktory ma v poli 'Vymenny listok' hodnotu false pokusi zapisat k inemu ako vseobecnemu lekarovi, zobrazi sa hlasenie o potrebe mat vymenny listok
skor, ako sa k takemuto lekarovi pacient chce zapisat. Ak pacient vymenny listok ma, zapis do lekarovej evidencie prebehne uspesne.

Ak chcete vytvorit noveho pacienta, kliknite v prihlasovacom okne na tlacidlo 'Chcem sa zaregistrovat'.
Otvori sa okno registracie pacienta. Pacient vyplni svoje osobne udaje a vytvori si prihlasovacie udaje.
Moznost kontroly, ci uz rovnake prihlasovacie meno je v evidencii NIE JE podporovana. Vyberte prosim jedinecne prihlasovacie meno.
Tlacidlo 'Zaregistrovat sa' vytvori noveho pacienta.
Tlacidlo 'Vycistit' vymaze hodnoty zadane to textiefldov a inych poli.
Ak si zelate zmeny serializovat, kliknite na tlacidlo 'Ulozit'. 

Poznamky ku kodu a popis najdolezitejsich tried programu
Program vypisuje kontrolne vypisy na konzlou. Tieto vypisy sa vo finalnej verzii programu neobjavia.
Logika tohoto programu je nasledujuca:
Manazer poistovne zaeviduje lekara/ov. Pacient sa zaregistruje, prihlasi sa a nasledne sa zapise k vseobecnemu lekarovi. Vseobecny lekar sa prihlasi
a vyda pacientovi, ktory sa k nemu zapisal, vymenny listok. Pacient sa opat prihlasi a s vymennym listkom sa zaregistruje k specializovanemu lekarovi.

Trieda 'poistovna': Objekt triedy poistovna je hlavnym objektom programu a drzi v sebe agregovane objekty lekarov, pacientov
a samotne udaje o poistovni. 
Metody 'autentifikaciaPoistovne', 'autentifikaciaLekara' a 'autentifikaciaPacienta' su metody nutne k prihlaseniu. 
Volaju sa z 'PrihlasovacieOknoGUI'. 
Metoda 'evidujLekara' podla poctu argumentov vytvara objekt bud vseobecneho alebo specializovaneho lekara.
Volaju sa z 'ManazerPoistovneGUI'.
Metoda 'vypisLekarov' upovedomi sledovatelov lekarov. Je volana z viacerych okien.
Metoda 'vratLekara' vrati objekt lekara zdravotnej poistovne.
Metoda 'evidujPacienta' vytvori novy objekt pacienta. Metoda je volana z okna 'RegistraciaPacientaGUI'.
Metody 'uloz', 'nacitaj' a 'vymazOutput' su metody pouzivane pre potreby serializacie.

Trieda 'ResetVstupov' je trieda obsahujuca pomocnu metodu na resetovanie serializacie. Vo vyslednom kode sa nebude nachadzat.

Trieda 'Predpis' je trieda obsahujuca informacie o konkretnom predpise.
Metody su gettery a settery pre tieto udaje.

Trieda 'Lekar': Objekt triedy lekar je konkretny lekar evidovany poistovnou,
Agreguje objekty tried 'OsobneUdaje', 'PrihlasovacieUdaje' a 'Pacient'.
Metoda 'evidujPacienta' prida do zoznamu pacientov objektu lekar noveho pacienta. 
Je volana z 'PrihlasyPacient' a vracia boolovsku hodnotu, ci lekar pacienta zaevidoval, alebo nie.
Metoda 'vratPacienta' vrati objekt konkretneho pacienta daneho lekara.
Metody 'vydajVymennyListok' a 'vytvorPredpis' su metody volane z 'LekarskeZaznamyGUI'.
Metoda 'zistiSpecializaciu' vrati "Vseobecny lekar".
Zvysne metody su settery a gettery pre konkretne udaje.

Trieda 'SpecializovanyLekar' je podtrieda triedy 'Lekar'.
Dedi metody triedy lekar.
Metoda 'evidujPacienta' je prekonana metoda triedy 'Lekar'. Jej spravanie je odlisne. Pacienta prida do evidencie iba ak ma pacient vymenny listok.
Metoda 'zistiSpecializaciu' je prekonana metoda triedy 'Lekar'. Vrati konkretnu specializaciu daneho lekara, co je odlisne ako povodna metoda.

Trieda 'Pacient': Objekt triedy pacient je konkretny pacient evidovany zdravotnou poistovnou a lekarom
Agreguje objekty tried 'OsobneUdaje', 'PrihlasovacieUdaje', 'Predpis', 
Metody tejto triedy su gettery a settery pre udaje pacienta.

Triedy packageu GUI vytvaraju pouzivatelske rozhranie. Aplikacia sa spusta v 'PrihlasovacieOknoGUI'. 
Podla volby prihlasovanej osoby alebo registracie sa vytvara novy stage. Aplikacia nepodporuje prihasenie viacerych uzivatelov naraz.

Kontrolovane kriteria:
1. Opisat funkcionalitu - opisana vyssie

2. Polymorfizmus - priklad v 'PrihlasenyPacient' - Event handler tlacidla 'navsteva'

		navsteva.setOnAction(e->{
			if (lwVypisLekarov.getSelectionModel().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Nevybrany lekar");
				alert.setContentText("Ziaden lekar zo zoznamu nebol zvoleny.\n"
						+ "Najskor vyber lekara.");
				alert.showAndWait();
			} else {
				int index = lwVypisLekarov.getSelectionModel().getSelectedIndex();
				if(!poistovna.vratLekara(index).evidujPacienta(pacient)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Vymenny listok nebol najdeny");
					alert.setContentText("K specializovanemu lekarovi mozes ist len s vymennym listkom.\n"
							+ "Najskor navstiv vseobecneho lekara a poziadaj ho o vydanie vymenneho listka.");
					alert.showAndWait();
				}
				
			}
		});

'poistovna.vratLekara(index)' vrati NEJAKEHO lekara zo zoznamu, ktory si drzi poistovna. Lekar moze byt vseobecny, alebo specialista.
'poistovna.vratLekara(index).evidujPacienta(pacient)' metoda pacienta bud vzdy zaeviduje, ak je lekar vseobecny, alebo pacienta zaeviduje iba s 
vymennym listkom, ak je lekar specialista.

dalsi priklad - vo 'VypisLekarovGUI'

		for (Lekar lekar : lekari) {
			this.getItems().add(lekar.zistiMeno() +" - "+ lekar.zistiSpecializaciu());
		}
		
Metoda 'lekar.zistiSpecializaciu()' sa sprava odlisne, ak je zo zoznamu vybrany specializovany lekar.

3. Dedenie - Moj program obsahuje zatial iba jednu hierarchiu dedenia. 'SpecializovanyLekar' je podtrieda triedy 'Lekar'.
Dalsie dedenie bude implementovane pri predpisoch. To sa v tejto verzii programu ale nenachadza.

4. Agregacia - 'Lekar', 'SpecializovanyLekar' aj 'Pacient' agreguju objekty tried 'OsobneUdaje', 'PrihlasovacieUdaje'.
Objekt triedy 'ZdravotnaPoistovna' agreguje objekty tried 'Lekar', 'SpecializovanyLekar' aj 'Pacient'.

5. Oddelenie aplikacnej logiky od pouzivatelskej.
V event handleroch danych elementov GUI volam metody tried ktore tvoria aplikacnu logiku a v event handleroch pripadne upravujem vlastnosti elementov GUI.
V priklade polymorfizmu uvadzam event handler ktory na zaklade toho, ci je zvoleny nejaky z vypisov vytvara Alert, alebo sa pokusa zapisat pacienta k lekarovi.
Ziska objekt lekara zo zdravotnej poistovne a nasledne sa pokusi k lekarovi evidovat pacienta. Metoda evidovania pacienta triedy Lekar vracia boolovsku hodnotu
na zaklade ktorej sa rozhodne, ci sa zobrazi Alert, alebo nie.

Dalsie priklady event handlerov - 'ManazerPoistovneGUI'

		vycisTF.setOnAction(e->{
			meno.clear();
			nick.clear();
			heslo.clear();
			adresa.clear();
			rodnec.clear();
			pohlavie.getSelectionModel().clearSelection();
			specializacia.getSelectionModel().clearSelection();
			specializaciaAktivna.setSelected(false);
		});
		
Vycisti udaje z poli v GUI.
		
		vypisAktualnychLekarov.setOnAction(e->{
			poistovna.vypisLekarov();
		});
		
Zavola metodu poistovne, ktora upovedomi sledovatelov.
		
		specializaciaAktivna.setOnAction(e->{
			if (!specializaciaAktivna.isSelected()) {
				specializacia.getSelectionModel().clearSelection();
				specializacia.setDisable(true);
			} else {
				specializacia.setDisable(false);;
			}
		});
		
Meni moznosti upravovania poli v GUI.
		





 