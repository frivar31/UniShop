# Commentaires DM3

Corrigé par An Li

Total: 87%

Déplacez votre implémentation dans le dossier 'Implementation' au lieu de 'Unishop Java Project'

## Code source Java du programme [38/45]

- Le projet est mal structuré
  - Mettez votre fichier pom.xml dans le dossier src/main/java
  - Mettez tous vos tests dans le dossier src/test/java
- Pas une bonne idée de mettre à jour la base de données uniquement à la sortie
  - Si plusieurs clients se connectent à l'application, les autres clients n'ont pas la dernière version des données
- Certains aspects fonctionnent mal
  - Impossible de se connecter avec un nouveau compte après l'inscription
  - Impossible d'entrer un prix avec un nombre décimal
  - Un revendeur ne peut pas modifier ses produits
- Certains aspects n'ont pas de sens
  - Possible de spécifier dans le panier une quantité de produits plus élevée que ce qui est disponible dans l'inventaire
    - Heureusement, le problème est détecté au moment de passer la commande

## Tests unitaires en JUnit [20/20]

Bien!

## Configuration Maven [5/5]

Bien, mais les tests ne roulent pas. On comprend que votre projet est mal structuré

## Production du JAR [3/5]

- Le JAR fourni ne fonctionne pas, il manque la classe Main
  - Voir la [diapositive 27 de la démo 9](https://studium.umontreal.ca/pluginfile.php/8767136/mod_resource/content/7/D%C3%A9mo%2009.pdf) pour spécifier la classe main et ajouter les dépendances dans votre pom.xml

## Manuel utilisateur (README) [4/5]

- Indiquez vos noms avec le lien vers vos profils GitHub
- Ne mentionne pas la version de Maven (non utilisée) et JUnit requise

## JavaDocs générés [5/5]

Bien, mais le lien vers JavaDoc ne fonctionne pas dans votre rapport. Évitez de commencer les liens par 'C:\Users...'

## Cohérence entre les modèles et le code [12/15]

- Le diagramme de classes dans le rapport n'est pas en format SVG
- Rendre la classe Product abstraite
- Pas nécessaire de mettre la propriété &lt;&lt;Property&gt;&gt; aux attributs
- Redondance: OrderItem = Product (ou une instance de celle-ci)?
- L'espacement rend la lecture un peu difficile

## Bonus: Interface graphique [N/A]

## Bonus: Action GitHub [N/A]
