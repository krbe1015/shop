SHOP
====

Abgabe 1 (!!!!!!!!!!!!!!!!!!!AENDERUNGEN IM BRANCH EXPERIMENTAL!!!!!!!!!!!!!!!!!!!)

*Suche nach Kunden, Bestellungen, Artikel/Produkt*
- [x] Suchen K B A/P (findAll{Kunden,Bestellungen,Artikel}ById) GET

*Kunde registrieren u. aendern, Bestellung inkl. Positionen aufgeben, Artikel/Produkt anlegen u. aendern*
- [x] K reg/alter (createKunde, updateKunde) POST PUT
- [ ] B/P aufgeben (createBestellung, createBestellPosition) POST
- [ ] A/P anlegen/aendern (createArtikel, createProdukt) POST

*Mock-Objekte fuer den Anwendungskern*
- [x] add Logger instead of println()
- [x] rename Mock.java in MockService.java
- [x] fix/rewrite if (x != y) ..; else ..;

*"mvn site"*
- [x] generate maven report
