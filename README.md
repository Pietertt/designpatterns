# GUI editor

Een GUI geprogrammeerd in Java.

Run `make run` om de applicatie te starten.<br>
Run `make clean` om alle .class bestanden op te ruimen.<br>
Run `make compile` om alle bestanden te compileren.<br>

**Stap 1: eenvoudig tekenprogramma**<br>
Maak een eerste versie van de grafische editor die ellipsen en rechthoeken kan tekenen, selecteren, verplaatsen en van grootte veranderen.<br>
Groepen, bijschriften en file operaties hoeven nu nog niet.<br>
- [x] Tekenen lukt
- [x] Selecteren lukt
- [x] Verplaatsen lukt
- [x] Van grootte veranderen lukt



**Stap 2: command pattern**<br>
Voeg file IO toe (volgens de grammatica die verderop uitgelegd wordt).<br>
Zorg ervoor dat alle acties die een gebruiker kan uitvoeren, via command-objecten worden gedaan.<br> 
Voeg de mogelijkheid toe om acties ongedaan te maken (onbeperkte undo en redo).<br>
- [x] File IO doet het
- [x] Alle gebruiker acties worden met het command pattern geregeld
- [x] Onbeperkte undo / redo werkt

**Stap 3: composite pattern**<br>
Voeg groepen toe volgens het composite pattern.<br> 
Pas ook de file IO aan.<br>
Bijschriften hoeven nu nog niet.<br>

**Stap 4: visitor pattern**<br>
Implementeer een visitor klasse voor de figuren.<br>
Verplaatsen, resizen en schrijven naar file moeten door subklassen van deze visitor uitgevoerd worden.<br> 
Refactor het programma.<br>
- [x] De visitor klasse is geimplementeerd
- [x] Het verplaatsen van shapes wordt geregeld door een visitor
- [x] Het resizen van shapes wordt geregeld door een visitor
- [x] Het schrijven van shapes naar een bestand wordt geregeld door een visitor

**Stap 5: strategy pattern en singleton pattern**<br>
Het verschil tussen een ellips en een rechthoek is eigenlijk alleen de manier van tekenen en hun naam. We kunnen ze dus wel samennemen tot een klasse "basisfiguur".<br>
Een basisfiguur bevat coordinaten en een pointer naar een strategy object (zijn delegate).<br>
De delegate kan tekenen en heeft een toString methode.<br>
Er zijn maar 2 delegates nodig: voor een ellips en voor een rechthoek. Dit kunnen singleton objecten zijn. <br>
Refactor het programma.<br>
- [x] De vormen zijn samengevoegd in de klasse "basisfiguur"
- [x] Het tekenen van vormen wordt geregeld door het strategy pattern
- [x] De delegates zijn singleton objecten

**Stap 6: decorator pattern**<br>
Nu moeten er bijschriften bij de figuren geplaatst kunnen worden (soms ernaast, soms erboven, soms eronder).<br>
Er kunnen meerdere bijschriften bij een figuur staan en ook groepen kunnen bijschriften hebben.<br>
Het decorator pattern is hiervoor zeer geschikt.<br>
Pas ook de file IO aan.<br>
Refactor het programma.<br>
