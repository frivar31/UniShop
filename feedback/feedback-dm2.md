# Commentaires DM2

Corrigé par An Li

Total: 86%

## Description du système opérationnel [5/5]

Bien!

Attention! Les captures d'écran et les images ne s'affichent pas dans votre rapport. Évitez de commencer les liens par 'C:\Users\...'

## 6 diagrammes d'activité UML [22/25]

- Flux principal
  - Montrer le menu principal, pas le flux d'achat

## Diagramme de classes UML [16/20]

- Rendre la classe Product abstraite
- Pas nécessaire de mettre la propriété <<Property>> aux attributs
- Mettre les méthodes pour interagir avec d'autres classes dans des contrôleurs pour permettre la réutilisation de celles-ci
- Redondance: OrderItem = Product (ou une instance de celle-ci)?
- L'espacement rend la lecture un peu difficile

## 5 diagrammes de séquence UML [20/25]

- Éviter les points qui ne partent de rien
- Manque validation:
  - Mode de paiement
  - Adresse d'expédition
  - Informations du produit

## Code source Java du programme et fichier JAR [21/25]

- Évitez de tout mettre dans le fichier App.java
- Le code est là, mais une bonne partie des méthodes n'est pas appelée
- Besoin du mot de passe pour s'inscrire
  - Le pseudo n'est pas le mot de passe
- Pas possible de revenir en arrière à partir de certains menus, dont 'Modifier son profil'

## Bonus: Utilisation de GitHub [2/5]

- Pas de pull requests depuis le devoir 1
- Pas d'Issues créés
- Tous les commits sont sur main
