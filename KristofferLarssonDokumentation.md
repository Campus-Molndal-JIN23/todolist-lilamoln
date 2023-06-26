# Kristoffer Larsson

## Egna reflektioner



## Projektet

### Beskrivning av projektet

Projektet gick ut på att skapa en TODO-applikation, med CRUD-funktionalitet mot MongoDB eller SQLite samt Unit-tester med Junit och Github CI. Jag bestämde mig även för att lägga till stöd för flera användare.
### Vad du har gjort
Jag har skapat projektet enligt ovan. Dvs. en TODO-applikation med stöd för flera användare, CRUD-funktionalitet mot MongoDB, jag valde att också bygga stöd för SQLite så användaren kan själv välja vilken databaslösning hon vill använda.

## Planering

### Lösningsförslag innan uppgiften påbörjas
Det jag tänkte från början var att jag ville försöka använda fler SOLID-principer, de principer jag framförallt ville försöka använda var:
* Single responsibility
* Open/closed
* Interface segregation
* Dependency inversion

  så jag tänkte att jag gör tre interface för CRUD-klasserna som Service-klasser och Controller-klasser kan vara beroende av, och att segregera interface för POJO-klasserna Todo och User (Interface segregation samt Open/closed principle, Dao-klassen kan ärvas av andra Interface för att utöka sin funktionalitet men bör inte ändras). Jag planerade vad de skulle behöva ha för funktionalitet och utgick ifrån det när jag skrev min konkreta implementation. Jag funderade också kring hur integration med databaser skulle ske, både med MongoDB och SQLite (Dependency inversion principle) i och med att jag var intresserad av att testa på att få nytta av Interface-segregation i praktiken i projektet.

#### Skisser (exempelvis)
Diagram för interfacen som jag funderade fram:

| Dao \<T\>      | 
|----------------|
| T create(T t)  |
| T read(T t)    |
| T update(T t)  |
| T delete(T t)  |
| List<T> list() |

Generell typ för Dao, CRUD-funktionalitet som kan användas av alla olika objekttyper
Anledning till att jag valde att returnera t är att jag vet att det är ganska smidigt i Mongo då man kan få tillbaka dokument man stoppat in, uppdaterat, tagit bort etc. om operationen lyckades. Lyckas inte operationen så returneras null, så att klasserna ovanför kan hantera det på ett smidigt sätt.

| TodoDao\<Todo\>        |
|------------------------|
| getByUserId(String id) |
Anledningen till att jag valde String var att jag ville att interfacet skulle kunna användas oavsett vilken databaslösning man har.

| UserDao\<User\> |
|-----------------|

UserDao behövde inga egna metoder utan bara säga vilken typ av objekt den skulle hantera.

### Jira/Trello/Github Project och projekthantering enligt Scrum/Kanban
(Github Project)[https://github.com/orgs/Campus-Molndal-JIN23/projects/51/views/1?layout=board]
## Arbetet och dess genomförande

### Vad som varit svårt
Det stökigaste var att jag i början ville använda mig av CodecRegistry i MongoDB för att mappa mina POJO-objekt direkt till implementeringen av interfacen. Jag hade en generell klass, MongoDao som ärvde Dao och alltså kunde använda generics och sedan MongoTodoDao och MongoUserDao som ärvde TodoDao och UserDao och extendade MongoDao med <Todo> och <User>.

Men CodecRegistry bråkade något oerhört och ville bara fungera typ 25% av gångerna mot Atlas, men den funkade klockrent när jag körde mot localhost. Så det höll jag på att bråka med ganska länge utan att komma framåt, jag funderade på att skriva egna codecs men det skulle sannolikt inte funka då mappingen till mina POJO:s ju fungerade och felet låg någon annanstans.

När jag släppte det och jobbade mot Document gick det smidigt. SQLite-implementationen gick också smidigt.

Jag skrev tester som jag tror ska täcka de vanligaste felen, men har säkert missat mycket. Blir också mycket boilerplate tycker jag när man skriver tester så använde CoPilot för att komplettera när jag skrivit testerna till en klass (i och med att alla Service och Dao-klasser är relativt lika så gick det smidigt. Jag fick dock aldrig till att Mockito kunde mocka en MongoDB-databas.)
### Beskriv lite olika lösningar du gjort
Beskrev det mesta i planeringen, delat upp i Interface och olika "lager" för att få separation och SRP. I botten finns Dao-klasserna som sedan används av Serviceklasser som antingen bara returnerar det Dao ger dem, eller utför ytterliggare logik om det behövs, AppController plockar ihop TodoService och UserService så att man får ut samlad data. ConsoleController använder AppController för att skicka ut till konsolen. Fördelen med att bygga på det sättet är ju att både "botten", dvs. Dao och databaslösning enkelt kan bytas ut, men även interfacet. Man skulle lika gärna kunna byggt GUIcontroller eller ApiController som skickar ut datan från AppController till GUI eller API.

### Beskriv något som var besvärligt att få till
Som jag skrev innan, MongoDB implementationen var lite krånglig mest för jag var så envis och tyckte lösningen med en MongoDao som använde generic types var snygg, men när jag släppte det gick det ganska smidigt. Att få mockito att mocka både MongoDatabase och MongoCollection var stökigt och det fick jag inte att funka. Dock testar jag alla lager över, och det enda Dao gör är att skicka svar från MongoDatabase och Collection så om jag mockar de är det inte mycket kvar i min klass jag testar.

### Beskriv om du fått byta lösning och varför i sådana fall
Jag böt lösning med MongoDB, istället för generics och CodecRegistry jobbar jag med Documents och har metoder för att omvandla objekt till Document och Document till objekt. Det funkar toppen mot både Atlas och localhost.
## Reflektion & Slutsatser

### Vad gick bra
Tycker överlag det gick bra, fick till ett projekt jag själv tycker är överskådligt och lättarbetat. Även när jag stötte på problem gjorde min design från början att det var ganska små delar som berördes så det övriga programmet inte behövde ändras.

### Vad gick dåligt / Vad hade ni gjort annorlunda om ni gjort om projektet
Jag tycker inte SQLite-lösningen är lika snygg som MongoDB, det blir så jäkla mycket upprepad kod med olika PreparedStatements. Jag hade nog kunnat lösa det med mer generella metoder och ha (Object... args) som input istället så man kan skicka in olika många argument beroende på och loopa igenom och sätta PreparedStatement utifrån det. Det hade nog sannolikt gjort läsbarheten lägre dock, så en avvägning mellan "smarta/snygga" lösningar och enkla lösningar. Jag valde den enkla.

Hade man fått hade jag gärna haft ett ORM-bibliotek, typ JPA, istället för att göra det både lite snyggare och mer lättläst.

### Vad har du lärt dig
Det som var nytt i projektet var Github CI, och det känner jag att jag i alla fall fick en grundläggande koll på, och fick det att funka utan större krångligheter. Förstår också snabbt nyttan med det i ett större projekt med många inblandade. Framförallt i samband med TDD så att testerna är färdiga innan koden skrivs. Nu gjorde jag inte TDD i detta projekt utan började med att koda och testade ju alltid klasserna lokalt också, men även då så är det såklart grymt att kunna testa på flera maskiner än sin egen, men att kunna testa på massa olika maskiner som man har i produktion etc. måste ju såklart vara guld värt i skarpa projekt.

Sedan märker jag såklart också att jag blir mer bekväm med olika tekniker varje gång jag använder de i ett projekt, så även om jag inte är någon expert på något av det jag använt i detta projekt så känner jag mig bekvämare med det än innan och det går snabbare att få till det jag vill.

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
Framförallt att använda och se nyttan av mer SOLID-principer har varit kul, tycker de är skojiga överlag att implementera och det är också tillfredsställande när man ibland kan få till riktigt snygg och tydlig kod som dessutom är bra. Att bli mer bekväm med vanliga tekniker som NoSQL med Mongo, SQL med SQLite, Github CI etc. är ju såklart också värdefulla erfarenheter för framtida praktik och förhoppningsvis jobb.