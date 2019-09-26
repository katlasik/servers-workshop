1. Zbuduj aplikację pakując ją do pliku `jar`. Sprawdź czy plik został wygenerowany w katalogu `target`.
2. Uruchom instancję aplikacji przy pomocy polecania `java -jar counter.jar`. Sprawdź w konsoli, na jakim porcie uruchomiła się aplikacja.
3. Sprawdź jakie informacje są umieszczane w pliku logów. Zmień poziom logowania na `DEBUG` i sprawdź ponownie log.
4. Uruchom drugą instancję aplikacji na innym porcie używając parametru `-Dserver.port=<port>`
5. Wygeneruj keystore z certyfikatem za pomocą polecenia:

```bash
keytool -alias keystore -genkeypair -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -dname "CN=JoeDoe, OU=ACME, O=ACMECorp, L=Czestochowa, ST=Slaskie, C=PL"
```

6. Uruchom aplikację umożliwiającą połączenie się poprzez protokół `https`. Wykorzystaj do tego celu wygenerowany `keystore`:

```bash
java -Dserver.port=<port> -Dserver.ssl.key-store=keystore.p12 -Dserver.ssl.key-store-password=<haslo> -jar counter.jar
```
 
7. Zmień tryb budowania aplikacji na `war` oraz zbuduj projekt. Sprawdź czy plik został wygenerowany w katalogu `target`.

8. Skonfiguruj użytkownika dla serwera `Tomcat` modyfikując odpowiednio plik `conf/tomcat-users.xml`. Stwórz użytkownika `tomcat`
posiadającego uprawnienia `manager-gui`, `manager-script` oraz `tomcat`.
```xml
<role rolename="admin-gui"/>
<role rolename="admin-script"/>
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="tomcat" password="tomcat" roles="admin-gui, admin-script, manager-script, manager-gui"/>
```

9. Uruchom serwer **Tomcat** za pomocą pliku `bin/startup.sh`.

10. Zaloguj się do GUI managera serwera `Tomcat`. Sprawdź jakie aplikacje są zainstalowane na serwerze.

11. Zainstaluj aplikację `counter` kopiujać ją do katalug `webapps`.

12. Zainstaluj kolejną instancję aplikacji na serwerze `Tomcat` używając *GUI* pod inną ścieżką.

13. Skonfiguruj plugin *tomcat7-maven-plugin* w `pom.xml`. Podaj odpowiedni *URL*, nazwę użytkownika oraz hasło ustalone
podczas konfigurowania pliku `conf/tomcat-users.xml` w katalogu instalacji `Tomcat`. Przeprowadź opublikowanie 
nowej wersji na serwerze za pomocą pluginu.

14. Uruchom `nginx` w katalogu `docker` za pomocą polecenia:

```bash
docker-compose up --force-recreate nginx
```

15. Skonfiguruj *load-balancer* w pliku nginx.conf. Stwórz *load-balancer* korzystający z instancji
*standalone* aplikacji oraz opublikowanych na *Tomcacie*.

16. Stwórz następujace przekierowanie w pliku `nginx.conf`: 

    * przekierowanie serwerowe z `manual` na `help`
    * przekierowanie 301 z `faq` na `help` 
    * przekierowanie 302  z `readme` na `help`.

17. Uruchom instancję `Redis` w katalogu `docker` za pomocą polenia:

```bash
docker-compose up --force-recreate redis
```
Uruchom ponownie logi aplikacji. Sprawdź czy aplikacja poprawnie połączyła się z `Redisem` w logu. 

18. Uruchom `Wildfly` poprzez skrypt `/bin/standalone./sh`. Możesz skorzystać z parametru
`-Djboss.socket.binding.port-offset=1` aby zmienić port. Zainstaluj aplikację poprzez skopiowanie
pliku `war` do `standalone/deployments`.

19. Dodaj nowego użytkownika administracyjnego poprzez skrypt `add-user.sh` i zaloguj się do konsoli administracyjnej.
