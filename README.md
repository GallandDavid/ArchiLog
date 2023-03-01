# Genrate pom.xml
```
mvn archetype:generate \
        -DarchetypeGroupId=org.openjfx \
        -DarchetypeArtifactId=javafx-archetype-simple \
        -DarchetypeVersion=0.0.3 \
        -DgroupId=org.openjfx \
        -DartifactId=sample \
        -Dversion=1.0.0 \
        -Djavafx-version=17.0.1
```

# Run code
```
mvn clean package  
mvn javafx:run
```
# Todo
'''
Projet Micro éditeur de figures géométriques.
Fonctionnalités attendues

On désire créer un logiciel de dessin vectoriel dont le principe est de créer de nouveaux dessins en utilisant/modifiant/combinant des dessins existants. Plus précisément, nous voulons pouvoir :

    Sélectionner un objet depuis un menu graphique (toolbar), et le positionner sur notre dessin – (whiteboard) en utilisant le glisser-déposer (drag and drop).

    Créer des groupes d’objets et sous-groupes d’objets, sous-sous-groupes etc…

    Dissocier un groupe d’objet.

    Modifier la taille, position, etc… de nos objets ou groupes d’objets une fois ceux-ci incorporés dans le dessin.

    Ajouter des groupes d’objets ou des objets paramétrés à notre toolbar en les déposants sur la toolbar (drag and drop).

    Annuler ou refaire une opération.

    Sauvegarder un document et charger un document.

    Sauvegarder l’état du logiciel (toolbar) et le recharger au démarrage.

Vous devez fournir au minimum les deux objets suivants :

    Un rectangle : Ses propriétés sont : 
    largeur, ($check)
    hauteur, ($check)
    position, ($check)
    rotation, 
    centre de rotation, 
    translation, 
    couleur,
    arrondi des bords.

    Un polygone régulier : Ses propriétés sont : nombre de côtés, longueur d’un côté, position, rotation, centre de rotation, translation, couleur.

Observations générales

    On utilisera la bibliothèque JavaFX pour le rendu graphique.
    Tous les choix de patrons de conception utilisés seront justifiés dans le rapport.
    Il est demandé de rédiger des tests unitaires et d'intégration pertinents, notamment pour s'assurer du bon fonctionnement du mécanisme d'undo-redo. En particulier, on ne testera pas spécifiquement les classes de rendu graphique.
    Le rendu comportera un diagramme d'architecture.
    Pour faciliter la réutilisation de code, on prendra soin de séparer les objets gérant le rendu graphique, des objets contenant les données de la scène.

Évaluation

    Comme pour les TDs + soutenances

Détail des cas d'utilisation

Screenshot
Cas 1. Glissé-déposer depuis toolbar.

    L'utilisateur clique sur un élément de la toolbar.
    Il effectue un glissé-déposer.
    Il relache son clic :
        Au dessus de la feuille blanche, une nouvelle instance de l'objet est ajoutée à la feuille de dessin.
        Dans une icône "Poubelle", l'élément est supprimé de la toolbar.
        Ailleurs, rien ne se passe.

Cas 2. Groupage.

    L'utilisateur réalise une sélection potentiellement multiple d'objets.
        Soit via un rectangle de sélection sur le whiteboard.
        Soit par control-clics successifs.
    Il sélectionne l'option "Group" d'un menu en clic-droit.
    Les objets deviennent enfants d'un groupe d'objets.

Cas 3. Dissociation d'un groupe.

    L'utilisateur sélectionne un groupe d'objets.
    Il sélectionne l'option "De-group" d'un menu en clic-droit.
    Les objets sont dégroupés, c'est-à-dire rajoutés au groupe parent. Ils ne doivent pas changer de position, etc.

Cas 4. Édition des propriétés des objets.

    L'utilisateur sélectionne un objet ou un groupe d'objets.
    Il réalise un clic droit et sélectionne "Edit" dans un menu déroulant.
    Une boite de dialogue s'ouvre : elle contient les paramètres que l'on peut modifier.
    L'utilisateur change un ou plusieurs des paramètres.
    Il appuie sur :
        "Appliquer": les modifications sont appliquées, et il peut continuer à changer des paramètres.
        "Ok": les modifications sont appliquées et le dialogue se ferme.
        "Annuler": toutes les modifications réalisées depuis l'ouverture du dialogue sont annulées.

Cas 5. Glissé-déposé vers toolbar.

    L'utilisateur sélectionne un objet ou groupe d'objets.
    Il effectue un drag'n'drop.
    S'il relache la souris sur la toolbar, un nouvel élément est ajouté dans la toolbar.
    S'il relache la souris sur la poubelle, les objets sont supprimés.

Cet élément contient les informations nécessaires pour créer des clones exacts de l'objet qui a été originellement drag'n'dropped, à l'exception de l'information de position. Note : S'il crée un nouveau document, les éléments de la toolbar sont conservés.
Cas 6. Undo-Redo.

Ce cas s'applique aux opérations 1. à 5.

Undo : nécessite qu'au moins une action ait été réalisée.

    L'utilisateur clique sur le bouton Undo dans le menu.
    La dernière action réalisée par l'utilisateur est annulée : le document revient à son état précédent.

Redo : nécessite qu'au moins une action ait été annulée.

    L'utilisateur clique sur le bouton Redo dans le menu.
    La dernière action qui avait été annulée est rejouée.

Cas 7. Sérialisation d'un document.

Sauvegarde

    L'utilisateur appuie sur le bouton "Save".
    Un dialogue s'ouvre et lui demande l'emplacement du fichier de sauvegarde.
    Le document est sauvegardé dans ce fichier.

Rechargement

    L'utilisateur appuie sur le bouton "Load".
    Un dialogue s'ouvre et lui demande de chercher un fichier de sauvegarde.
    Si c'est un fichier de sauvegarde valide, le document actuel est remplacé par le document dans le fichier.

Cas 8. Sauvegarde de l'état du logiciel.

    L'utilisateur rajoute et supprime des éléments de sa toolbar.
    Il quitte le logiciel.
    Lorsqu'il ré-ouvre le logiciel, la toolbar est telle qu'il l'avait laissée : ses formes peuvent être réutilisées dans de nouveaux whiteboards.

'''