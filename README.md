# Application d'Authentification en Java

## Présentation
Ceci est une application web Java simple pour l'authentification des utilisateurs. Les utilisateurs ont la possibilité de s'inscrire, de se connecter, de se déconnecter, et de récupérer leur mot de passe.

## Fonctionnalités
### 1. Page de Connexion
- Sur la page de connexion, les utilisateurs existants peuvent saisir leur nom d'utilisateur et leur mot de passe.
- Une fois connectés avec succès, ils accèdent à leur espace personnel.

   ![Capture d'écran de la Page de Connexion](images/signin.png)

### 2. Page d'Inscription
- Les nouveaux utilisateurs peuvent s'inscrire en fournissant leur nom, prénom, nom d'utilisateur et mot de passe.
- Une fois inscrits avec succès, ils sont redirigés vers la page de connexion pour se connecter.

   ![Capture d'écran de la Page d'Inscription](images/signup.png)

### 3. Déconnexion
- Les utilisateurs connectés ont la possibilité de se déconnecter de leur session.
- Après la déconnexion, ils sont redirigés vers la page de connexion.

   ![Capture d'écran de la Page de Déconnexion](images/panel.png)

### 4. Récupération de Mot de Passe
- Les utilisateurs ayant oublié leur mot de passe peuvent le récupérer en trois étapes :
  - Entrez votre adresse e-mail.
  - Recevez le code de récupération dans votre boîte de réception e-mail.
  - Définissez un nouveau mot de passe.

   ![Capture d'écran de la Page de Récupération de Mot de Passe](images/recoverpassword.png)
   ![Capture d'écran de la Génération de Code](images/codegen.png)
   ![Capture d'écran de la Réinitialisation de Mot de Passe](images/resetpassword.png)

## Configuration et Installation
Pour déployer cette application sur votre propre serveur, suivez les étapes suivantes:
1. Clonez ce dépôt Git.
2. Configurez votre base de données (nommée "customer_db") pour stocker les informations des utilisateurs.
3. Modifiez les paramètres de connexion à la base de données dans le fichier de configuration.
4. Compilez et exécutez l'application avec un serveur Java compatible.

## Auteur
Ce projet a été développé par Mohamed OUHAMI.

## Licence
Ce projet est sous licence MIT. Consultez le fichier LICENSE pour plus d'informations.

Lorsque l'utilisateur tente de se connecter après s'être déconnecté, il sera redirigé vers l'espace utilisateur s'il était toujours connecté. Si non, il verra la page de connexion.
