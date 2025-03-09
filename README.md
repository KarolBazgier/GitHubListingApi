## 📌 Opis projektu: 
**GitHub Listing API** to aplikacja Spring Boot, która pobiera listę repozytoriów danego użytkownika GitHub i zwraca ich szczegóły, w tym gałęzie i właściciela repozytorium. Dane pobierane są przy użyciu **WebClient** z publicznego API GitHub.

## 📌 Api Endpoints: 
GET	/{username} -Pobiera repozytoria użytkownika z GitHub

## 📌 Wymagania: 
- Java 17+
- Maven 3+
- Spring Boot 3+

## 📌 Uruchomienie: 
git clone https://github.com/KarolBazgier/GitHubListingApi.git
cd GitHubListingApi
mvn clean spring-boot:run