# Projet Java - Vous êtes le héros

## Informations Utiles
- [Explication du projet](https://github.com/BENZINE-ASMA/IAmTheHero_javaGame/blob/main/Prototype/ProjetJava_2324.pdf)
  
## Description
"Vous êtes le héros" est un jeu interactif où le joueur plonge dans une aventure narrative médiéval-fantasy. Structuré en graphes acycliques orientés, le jeu présente des situations et transitions variées. Le joueur, incarnant un Sorcier ou un Humain, fait des choix influençant l'histoire et les fins possibles. Dans un monde de magie et de monstres, le joueur part en quête d'un objet magique puissant, affrontant divers défis et ennemis pour devenir le clan le plus puissant de la région.

## Prototype
![Node with ImageNode Design Patern Decorateur)](https://github.com/BENZINE-ASMA/IAmTheHero_javaGame/blob/main/Prototype/first.png)


![Terrain Interactif](https://github.com/BENZINE-ASMA/IAmTheHero_javaGame/blob/main/Prototype/second.png)


## Fonctionnalités
- **Choix multiples** : Vos décisions déterminent la suite de l'histoire.
- **Nœuds interactifs** : Des nœuds de décision, de chance, de combats et terminaux qui dirigent le récit.
- **Immersion audiovisuelle** : Sons et images intégrés pour une expérience immersive.
- **Personnages et combats** : Rencontrez divers personnages et engagez des combats en utilisant vos compétences.
- **Terrain interactif** : Permet au joueur de se déplacer sur la carte pour explorer et interagir avec l'environnement.
- **Design Pattern** : Le design pattern Décorateur a été utilisé.

## Exécution du jeu

1. **Cloner le dépôt** 

2. **Télécharger le JAR et exécuter le jeu en version console**
    ```bash
    jar cvfe IAmTheHero.jar main.JeuMain -C bin .
    java -jar IAmTheHero.jar

   ```
   
3. **Télécharger le JAR et exécuter le jeu en version interface graphique avec SWING**
    ```bash
    jar cvfe IAmTheHero.jar main.MainUI -C bin .
    java -jar IAmTheHero.jar
   ```
