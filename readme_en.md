# TVTower Database Editor

An Xtext-based editor fo the editing of database files (films, news, advertising, etc.) for TVTower should come into being here. Use as little effort as possible to give as much added value (support tooling) as possible for the editing of the entries for programmes, news items, advertising, etc.

* Autocomplete
* Hover
* Outline
* Navigation to referenced objects
* Validation
* ...

And all of this while directly editing the original file and having control over the changes made (diff between versions).

# Setting up the Editor

In the long-term, there should be an Eclipse update site for distributing the editor. In order to achieve quick quick feedback cycles in the prototype development phase, the editor must initially be built locally.

## One-off Steps

I'm assuming that the TVTower repository is already cloned!

1. Install Java
2. Install Eclipse (Eclipse installer), preferably Eclipse IDE for Java Developers, and then start it
3. Set up Eclipse using the Oomph setup script:

XXXXXXXXXXX Will need to check the UI for the following steps in English!

    * Kontextmenü im Package-Explorer -> `Import...` -> `Ooomph` -> `Projects from Catalog`
    * Grünes Plus oben rechts für das Hinzufügen eines neuen Setups anklicken (es geht der `Add User Projects`-Dialog auf
    * Im Catalog-Dropdown `Github Projects` auswählen
    * `https://raw.githubusercontent.com/TVTower/TVTDatabaseEditor/master/TVTDbEditor.setup` in das Resoource-URIs-Textfeld   eintragen; mit `OK` abschließen
    * den Eintrag `TVT Database Editor` unter `Github Projects` -> `<User>` selektieren (das Projekt sollte jetzt unten in der Tabelle auftauchen); mit `Next` fortfahren
    * Auf der Variablen-Seite muss man sich entscheiden, wohin das Repository geklont und mit welchem Protokoll der Zugriff erfolgen soll (Vorschlag https-read-only); mit `Next` und `Finish`abeschließen
    * Unten rechts in der Eclipse-Statusleiste dreht sich ein kleines Update-Rädchen, das kann man Anklicken, um den Oomph-Installationsfortschritt zu verfolgen. Hier kann man den nötigen Eclipse-Neustart bestätigen.

Nach dem Neustart wird die Installation fortgesetzt, das Editor-Repository geklont und die Projekte importiert. Im Package-Explorer sollte nun eine Reihe von `org.tvtower.db`-Projekten erschienen sein (noch mit Fehlermarkierung).

**Repositories View aufmachen**
* `Strg-3` zum Öffnen des `Quick Access`
* nach `git repo` suchen; bei den Views sollte der Git Repositories View dabei sein - diesen auswählen.

**Run-Konfigurationen verfügbar machen**
* In der Eclipse-Toolbar unter den Hauptmenüpunkten sollte ein Bereich mit einem "Bug" und 3 verschiedenen grünen "Play"-Icons sein; das sind die verschiedenen Debug und Run-Varianten.
* Auf den Pfeil(!) nach unten neben dem "Play" ganz links (`Run`) klicken
* Es sollte ein Menü aufgehen, den Punkt `Organize Favorites...` auswählen
* Im Dialog `Organize Run Favorites` die Aktion `Add...` auswählen und jeweils einen der dopptelt vorhandenen Einträge `Generate Database...` und `Launce Runtime Eclipse` selektieren und mit `OK` abschließen
* Im Dialog `Organize Run Favourites` sollten die beiden Einträge nun sichtbar sein; mit `OK` abschließen
* Wenn man jetzt das `Run`-Menü nochmal öffnet, sollten die beiden gewählten Run-Konfigurationen verfügbar sein

## Generierungsschritt

Initial einmal und immer wenn es Änderungen an der Grammatik gegeben hat, muss der Xtext-Generator angeworfen werden.
Bei einfachen Erweiterungen im Java-Code kann dieser Schritt entfallen.

In der `Run`-Aktion den Punkt `Generate Database...` anklicken (und im sich ggf. Öffnenden Dialog, dass noch Fehler existieren, bestätigen dass das in Ordnung ist).

Nun läuft der Generator los und baut aus der Xtext-Grammatik den Parser und haufenweise Infrastrukturcode. In der Console sieht man den Fortschritt. Nach einer Weile sollte dort dann `... - Done.` stehen, die Konsole als `<terminated>` markiert sein und der Java-Build starten (`Building: (x%)` in der Statusleiste).

Nach Abschluss des Builds sollte in keinem der Projekte mehr ein Fehler sein. Nun ist der Editor gebaut.

## Runtime-Eclipse starten

Um den Editor in Aktion zu sehen, wird ein Runtime-Eclipse gestartet, in dem dann der Editor verfügbar ist.
In der `Run`-Aktion den Punkt `Launch Runtime Eclipse` anklicken.
(Es kann hier zu Problemen führen, wenn die plattformspezifischen Plugins nicht passen.
Für diesen Fall unter `Run Configurations` in der `Run`-Aktion die Konfiguration öffnen und `Plugins`-Reiter nicht alle Plugins selektieren, sondern nur die Workspace-Plugins und dann rechts die `required plungins` ergänzen.
Die Fehlermeldungen beim Start helfen, dann die noch fehlenden Plugins nach und nach zu ergänzen.
Wie gesagt, mittelfristig soll man sich den Editor ja nicht selbst bauen müssen.)

* Git-Repositories-View öffnen (siehe oben mit `Strg-3`...)
* Importieren des TVTower-Repositories (Kontext-Menü im Repositories-View
* Im Kontextmenü des `Working Tree`-Knotens des TVTower-Repositories den Punkt `Import Projects` wählen. Dort sollte in der mittleren Box TVTower selektiert sein (Import as Eclipse project); mit `Finish` abschließen.

Im Project Explorer sollte jetzt das TVTower-Projekt erschienen sein.
Beim allerersten Öffnen einer `res/database/default`-Datenbankdatei wird man noch gefragt, ob das Projekt in ein Xtext-Projekt konvertiert werden soll.
Das muss bestätigt werden.

Nun sollte die Datei mit erkennbarem Syntax-Highlighting aufgegangen sein.

# Und wie weiter

## Editor-Features ausprobieren

Zunächst sind nur wenige Features umgesetzt, da als erstes die Grammatik für die Dateien definiert werden musste.
Triviale Autovervollständigung, einfacher Outline und etwas Linking sind schon vorhanden.

* Autovervollständigung mit `Strg-Leertaste` am besten nach der öffnenden spitzen Klammer, damit die möglichen XML-Tags angeboten werden; sowie nach einem Leerzeichen hinter dem Haupt-Tag für die Eigenschaften
* Outline - Der View sollte schon offen sein oder kann mit `Strg-3` gesucht werden und zeigt die XML-Struktur an
* Quick-Outline - mit `Strg-o` im Editor kann man ein Outline-Popup aufmachen, in dem man auch suchen kann
* GoTo-Navigation - `F3` springt bei verlinkten Objekten zur Definition (z.B. bei News-Triggern zu den Nachfolge-News, oder bei Programm-Staff-Membern zur Personendefinition
* Hover-Info: Mouse-Hover über einem Verlinkten Objekt zeigt Infos an (z.B. bei Nachfolge-News den Titel oder bei Staff-Membern den Personennamen)
* TO BE CONTINUED

Standard-Editor-Funktionalität wie Suchen (`Strg-f`) Copy/Paste, Springe zu Zeile (`Strg-l`) gehen natürlich auch.

## Fehler/Änderungswünsche/Probleme melden

Feedback am besten im Editor-Repo-Ticketsystem hinterlegen.

Ein vereinfachtes Setup (ohne Runtime-Eclipse) wird es dann geben, wenn die Grundfunktionalität umgesetzt und stabilisiert wurde.