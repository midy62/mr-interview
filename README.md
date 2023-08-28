# Description globale du projet

Ce projet implémente un système de gestion d'expéditions en utilisant le framework Spring.
Il offre des fonctionnalités pour gérer les expéditions, les colis et les statuts d'expédition associés.

# Prérequis

1. Java 17 installé sur la machine
2. IDE de votre choix
3. Git installé sur la machine
4. Un client http de votre choix en capacité d'émettre des requêtes http (get/post/delete ...)

# Comment builder le projet

./gradlew clean build

# Comment démarrer le projet

./gradlew :webservice:bootRun

# Contrôleurs

Le projet comporte trois contrôleurs principaux :

## ShipmentController

Le contrôleur ShipmentController gère les opérations liées aux expéditions.

1. **Liste des expéditions (GET /shipments)** : Récupère la liste de toutes les expéditions existantes.

```bash
curl -X GET http://localhost:8080/api/shipments
```

2. **Création d'une expédition (POST /shipments)** : Crée une nouvelle expédition en utilisant les informations
   fournies.

```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "parcels": [
        {
            "sequence": 1
        },
        {
            "sequence": 2
        }
    ],
    "trackingNumber": 1
}' http://localhost:8080/api/shipments
```

3. **Mise à jour d'une expédition (PUT /shipments/{id})** : Met à jour les informations d'une expédition.

```bash
curl -X PUT -H "Content-Type: application/json" -d '{
    "id": 1,
    "parcels": [
        {
            "id": 1,
            "sequence": 2
        },
        {
            "id": 2,
            "sequence": 1
        }
    ],
    "trackingNumber": 5
}' http://localhost:8080/api/shipments/1
```

4. **Suppression d'une expédition (DELETE /shipments/{id})** : Supprime une expédition via son ID.

```bash
curl -X DELETE http://localhost:8080/api/shipments/1
```

5. **Liste des colis d'une expédition (GET /shipments/{shipmentId}/parcels)** : Récupère la liste des colis associés à
   une expédition spécifique.

```bash
curl -X GET http://localhost:8080/api/shipments/1/parcels
```

6. **Ajout d'un colis à une expédition (POST /shipments/{shipmentId}/parcels)** : Crée un nouveau colis et l'associe à
   une expédition spécifique.

```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "parcels": [
        {
            "sequence": 3
        }
    ]
}' http://localhost:8080/api/shipments/1/parcels
```

## ParcelController

Le contrôleur ParcelController gère les opérations liées aux colis.

1. **Mise à jour d'un colis (PUT /parcels/{parcelId})** : Met à jour les informations d'un colis.

```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "sequence": 1
}' http://localhost:8080/api/parcels
```

2. **Suppression d'un colis (DELETE /parcels/{parcelId})** : Supprime un colis via son ID.

```bash
curl -X DELETE http://localhost:8080/api/parcels/1
```

## ShipmentStatusController

Le contrôleur ShipmentStatusController gère les opérations liées aux status de l'expédition

1. **Liste des status possibles d'une expédition (GET /shipmentStatuses)** : Récupère la liste des status possibles
   d'une expédition.
```bash
curl -X GET http://localhost:8080/api/shipmentStatuses
```