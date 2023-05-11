# Open Food Facts

Application de gestion de produits nutritionnels. Ce programme lit les données de produits dans un fichier CSV et les insère dans une base de données.

## Insertion de données

Le fichier CSV doit se situer dans un répertoire "target/" sur la racine du projet. Configurez le fichier "persistence.xml" afin de vous connecter à votre propre base de données.
Par défaut, le programme écrasera la database si elle existe pour la recréer entièrement.

## Interface Swing

Une interface Swing est disponible dans un package "fr.diginamic.openfoodfacts.views". Elle permet d'afficher dans une fenêtre l'ensemble des produits enregistrés en base, et de rechercher à partir d'une catégorie et/ou d'une marque.
ATTENTION ! Pensez à modifier le fichier "persistence.xml" avant de lancer cette interface (modifiez la valeur de "hibernate.hbm2ddl.auto" et saisissez "validate"). Dans le cas contraire, les données en base seront perdues.
