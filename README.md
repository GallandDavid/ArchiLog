# Todo
'''
    Un polygone régulier : 
    Ses propriétés sont : 
        nombre de côtés, 
        longueur d’un côté, 
        position, 
        rotation, 
        centre de rotation, 
        translation, 
        couleur.
        
Screenshot
Cas 1. Glissé-déposer depuis toolbar.

    L'utilisateur clique sur un élément de la toolbar.
    Il effectue un glissé-déposer.
    Il relache son clic :
        Dans une icône "Poubelle", l'élément est supprimé de la toolbar.

Cas 2. Groupage.

    L'utilisateur réalise une sélection potentiellement multiple d'objets.
        Soit via un rectangle de sélection sur le whiteboard.

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
Redo : nécessite qu'au moins une action ait été annulée.

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