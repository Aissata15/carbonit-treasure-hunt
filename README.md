# CarbonIT Treasure Hunt

## Présentation

Ce projet simule une chasse au trésor sur une carte, avec des aventuriers, des montagnes et des trésors. Les règles du jeu sont respectées : les aventuriers ne peuvent pas traverser les montagnes, ramassent les trésors, et ne peuvent pas se déplacer sur la même case qu’un autre aventurier.

---

## Utilisation rapide avec Docker (aucune installation Java/Maven requise, mais Docker doit être installé)

> **Attention : Docker doit être installé sur votre machine pour utiliser cette méthode.**  
> [Télécharger Docker Desktop](https://www.docker.com/products/docker-desktop/)

1. **Préparez le fichier `input.txt` dans le dossier racine du projet.**
2. **Copiez l’un des scénarios ci-dessous ou construisez votre propre carte dans `input.txt`.**
3. **Construisez l’image Docker :**
   ```shell
   docker build -t treasure-hunt .
   ```
4. **Lancez la simulation :**
   ```shell
   docker-compose up
   ```
5. **Le résultat sera écrit dans `output.txt` à la racine du projet, pour voir le résultat directement dans le terminal, taper dnas le shell:**
 ```shell
   type .\output.txt
 ```
---

## Utilisation avec Maven (installation Java et Maven requise)

Pour utiliser Maven, vous devez installer :

- **Java JDK 24** ou supérieur ([OpenJDK 24](https://adoptium.net/))
  - Ajoutez le dossier `bin` du JDK à votre variable d’environnement `PATH`
  - Définissez la variable d’environnement `JAVA_HOME`
- **Maven** ([Maven Download](https://maven.apache.org/download.cgi))
  - Ajoutez le dossier `bin` de Maven à votre variable d’environnement `PATH`

---

## Compilation et tests avec Maven

Dans le dossier du projet, ouvrez un terminal et lancez :

```shell
mvn clean package
```

Pour exécuter uniquement les tests unitaires :

```shell
mvn test
```

---

## Lancer une simulation avec Maven

Préparez un fichier d’entrée (par exemple `input.txt`) dans le dossier du projet.  
Lancez la simulation avec :

```shell
java -jar target/treasure-hunt-1.0-SNAPSHOT.jar input.txt output.txt
```

Le résultat sera écrit dans `output.txt`.

---

## Format du fichier d’entrée

Chaque ligne décrit un élément de la carte ou un aventurier :

- `C - largeur - hauteur` : création de la carte
- `M - x - y` : montagne en (x, y)
- `T - x - y - nb_trésors` : trésor en (x, y) avec nb_trésors
- `A - nom - x - y - orientation - séquence_mouvements` : aventurier

Les lignes commençant par `#` sont ignorées (commentaires).

**Orientations possibles :**
- N : Nord
- S : Sud
- E : Est
- O : Ouest

**Mouvements possibles :**
- A : Avancer
- G : Tourner à gauche
- D : Tourner à droite

---

## Construire votre propre carte d'entrée

Vous pouvez créer votre propre scénario en respectant le format ci-dessus.  
Exemple :

```
C - 5 - 5
M - 2 - 2
T - 4 - 4 - 3
A - Alice - 0 - 0 - E - AGADA
A - Bob - 1 - 1 - S - AAAG
# Ceci est un commentaire, il sera ignoré
```

---

## Exemples de scénarios à tester

**Pour jouer, copiez l’un des scénarios ci-dessous dans le fichier `input.txt` puis lancez la simulation.**

### 1. Déplacement bloqué par une montagne
```
C - 3 - 3
M - 1 - 1
A - Indy - 1 - 0 - S - A
```

### 2. Ramassage de plusieurs trésors
```
C - 3 - 3
T - 0 - 1 - 2
A - Lara - 0 - 0 - S - AA
```

### 3. Deux aventuriers tentent d’aller sur la même case
```
C - 3 - 3
A - Indy - 0 - 0 - S - A
A - Lara - 0 - 2 - N - A
```

### 4. Déplacement hors de la carte
```
C - 2 - 2
A - Indy - 0 - 0 - W - A
```

### 5. Rotation gauche/droite
```
C - 2 - 2
A - Lara - 1 - 1 - N - GDGA
```

### 6. Trésor ramassé par un aventurier, puis un autre arrive
```
C - 2 - 2
T - 1 - 1 - 1
A - Indy - 1 - 0 - S - A
A - Lara - 1 - 2 - N - A
```

### 7. Carte complexe avec plusieurs montagnes et trésors
```
C - 4 - 4
M - 1 - 1
M - 2 - 2
T - 0 - 3 - 2
T - 3 - 0 - 1
A - Lara - 0 - 0 - S - AAAA
A - Indy - 3 - 3 - N - AAAA
```

---

## Structure du projet

- `src/main/java/com/carbonit/` : code source principal
- `src/test/java/com/carbonit/` : tests unitaires
- `pom.xml` : configuration Maven
- `Dockerfile` et `docker-compose.yml` : pour lancer la simulation sans installation Java/Maven

---

## Auteur

Assignment CarbonIT

---

Pour toute question ou amélioration, contactez le responsable du projet.