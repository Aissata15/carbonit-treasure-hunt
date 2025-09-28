FROM openjdk:24-jdk

WORKDIR /app

# Copie le JAR généré et le fichier d'entrée
COPY target/treasure-hunt-1.0-SNAPSHOT.jar app.jar
COPY input.txt input.txt

# Commande pour lancer la simulation
CMD ["java", "-jar", "app.jar", "input.txt", "output.txt"]