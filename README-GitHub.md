# Setup your Project on GitHub

## Init GitHub repository

* Create Repository via following [URL](https://github.com/new)
  * Set reposotiry name to ArchetypeTest 
  * Set visibility of the project. Note: In order to use GitHub's container registry ghcr, make your repository public or ensure that you have billing plan including access to ghcr. 
  * Set owner for this project which is either your account, or one of your organizations
* On local command line enter project directory and enter
    ```
    git init
    git add .
    git commit -m "first commit"
    git branch -M main
    git remote add origin https://github.com/shs-it/ArchetypeTest
    ```
* Push changes to your GitHub repo using your favorite IDE.


## Configure GitHub actions 

*   __Docker image:__

    * Make your repository public to use ghcr or ensure that you have billing plan including access to ghcr.
  
*   __Automatic Depencies Updates via dependabor:__ 

    *   Create a personal access token (PAT) in developer settings, as described [here](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token) with `public_repo` access enabled.

    *   Set up a secret for GitHub actions called `MERGE_ME_SECRET` for the new repository as described [here](https://docs.github.com/en/actions/security-guides/encrypted-secrets?tool=webui#creating-encrypted-secrets-for-a-repository) that includes the generated PAT

*   To build a new release via GitHub actions enable write access for the  `GITHUB_TOKEN` as described [here](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/enabling-features-for-your-repository/managing-github-actions-settings-for-a-repository#configuring-the-default-github_token-permissions).


## Using GitHub actions

We provide following GitHub actions that are either started manually or automatically. To validate the actions start all actions with start option `manually`:  

*   [mavenBuild.yml](https://github.com/shs-it/ArchetypeTest/actions/workflows/mavenBuild.yml):
    *   __Description:__ Builds the project after each push
    *   __Started:__ Automatically and manually   

*   [newRelease.yml](https://github.com/shs-it/ArchetypeTest/actions/workflows/newRelease.yml):
    *   __Description:__ Create a new release using maven via GitHub web page
    *   __Started:__ Manually only
    *   Please note that the first run might fail, because the link to the ghcr.io repository is automatically created, first time you try to access it. So, please run this action twice, as soon as you created the repo.


*   [autoMerge.yml](https://github.com/shs-it/ArchetypeTest/actions/workflows/autoMerge.yml):
    *   __Description:__ Automatic merge of dependency updates with new patch or minor versions of dependencies from Dependabot. See https://github.com/ridedott/merge-me-action for more information.
    *   __Started:__ Automatically only

*   [dependabot.yml](https://github.com/shs-it/ArchetypeTest/actions/workflows/dependabot.yml):
    *   __Description:__ Check for new dependencies and create a pull request
    *   __Started:__ Automatically only (each day)

## Deployment 

In the following we assume a docker-swarm setup which is a typical starting point for clustering your container.
In addition, it can be easily run and maintained on your developing machine. 

### Docker-Stacks

*   [developerStack.yml](deploy/developerStack.yml)
    *   Includes all required dependencies to run the application during development on your local machine

*   [docker-compose.yml](deploy/docker-compose.yml)
    *   Stack to run the application as stack in your production environment

### Deploy Stack 

In order to deploy the stack, you can use following command from your checkout directory. 
```shell
docker stack deploy --compose-file ./deploy/docker-compose.yml archetypetest
```
