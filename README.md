# apps-rendering-api-models

These models are used to communicate with the Apps Rendering API

## How to release to NPM

This repository uses [`changesets`](https://github.com/changesets/changesets) to manage versions and releases.

To create a changeset, ensure you are using the correct Node version defined in [`.nvmrc`](./.nvmrc) and project dependencies installed by running `npm install`. Then:

- Run `npx changeset`. Select the type of change and enter a description
- Commit and push the changeset file to your branch
- When your feature PR is merged to `main`, the [changesets action](.github/workflows/changesets.yaml) will open a PR against `main` with the details of all unreleased changes. This is a "Release PR"
- When ready to release, merge the Release PR

## How to release a Snapshot to NPM

- Create a branch which has the changes you want to test
- Create a **Prerelease** using GitHub Releases
    - Set the Target to your branch
    - You must also create a tag for the snapshot release. For example, `v0.0.0-2022-10-20-SNAPSHOT`
    - To automatically release the snapshot to `npm`, publish the prerelease
    - Snapshots are released to the `snapshot` tag on `npm`. You can install them with `npm install @guardian/apps-rendering-api-models@snapshot`

## How to release to Sonatype

Prerequisites:

- have a Sonatype account with access to the guardian organisation

Steps:

- Having followed the instructions in [How to release to NPM](#how-to-release-to-npm) (including merging the "Release PR"):
    - switch to branch `main` and `git pull`
- In the SBT repl:

```sbtshell
clean
project scalaApiModels
release with-defaults
```
