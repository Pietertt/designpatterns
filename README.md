# GUI editor

Een GUI geprogrammeerd in Java.

Run `make run` om de applicatie te starten.__
Run `make clean` om alle .class bestanden op te ruimen.__
Run `make compile` om alle bestanden te compileren.__

**Stap 1: eenvoudig tekenprogramma**__
Maak een eerste versie van de grafische editor die ellipsen en rechthoeken kan tekenen, selecteren, verplaatsen en van grootte veranderen.
Groepen, bijschriften en file operaties hoeven nu nog niet.
```diff
# Tekenen lukt
# Selecteren lukt
# Verplaatsen lukt
# Van grootte veranderen lukt
```


**Stap 2: command pattern**__
Voeg file IO toe (volgens de grammatica die verderop uitgelegd wordt).
Zorg ervoor dat alle acties die een gebruiker kan uitvoeren, via command-objecten worden gedaan. Voeg de mogelijkheid toe om acties ongedaan te maken (onbeperkte undo en redo).

**Stap 3: composite pattern**<br>
Voeg groepen toe volgens het composite pattern. 
Pas ook de file IO aan.
Bijschriften hoeven nu nog niet.

**Stap 4: visitor pattern**__
Implementeer een visitor klasse voor de figuren.
Verplaatsen, resizen en schrijven naar file moeten door subklassen van deze visitor uitgevoerd worden. Refactor het programma.

**Stap 5: strategy pattern en singleton pattern**__
Het verschil tussen een ellips en een rechthoek is eigenlijk alleen de manier van tekenen en hun naam. We kunnen ze dus wel samennemen tot een klasse "basisfiguur".
Een basisfiguur bevat coordinaten en een pointer naar een strategy object (zijn delegate).
De delegate kan tekenen en heeft een toString methode.
Er zijn maar 2 delegates nodig: voor een ellips en voor een rechthoek. Dit kunnen singleton objecten zijn. Refactor het programma

**Stap 6: decorator pattern**__
Nu moeten er bijschriften bij de figuren geplaatst kunnen worden (soms ernaast, soms erboven, soms eronder). Er kunnen meerdere bijschriften bij een figuur staan en ook groepen kunnen bijschriften hebben.
Het decorator pattern is hiervoor zeer geschikt.
Pas ook de file IO aan.
Refactor het programma.
