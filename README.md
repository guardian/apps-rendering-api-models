# apps-rendering-api-models

These models are used to communicate with the Apps Rendering API

## How to release to NPM (beta)

This repository uses [`changesets`](https://github.com/changesets/changesets) to manage versions and releases.

To create a changeset, ensure you are using the correct Node version defined in `.nvmrc` and have dependencies installed by running `npm install`. Then:

- Run `npx changeset`. Select the type of change and enter a description
- Commit and push the changeset file to your branch
- When merged to `main`, the [changesets action](.github/workflows/changesets.yaml) will open a PR with the details of all unreleased changes
- Merging this "Release PR" will publish changes to `npm` and create a GitHub Release

## How to release to NPM and Sonatype (legacy)

Prerequisites:

- have a Sonatype account with access to the guardian organisation
- have an NPM account, part of the [@guardian](https://www.npmjs.com/org/guardian) org with a [configured token](https://docs.npmjs.com/creating-and-viewing-authentication-tokens)
- have typescript installed globally (`npm i -g typescript`)

Make sure you set upstream `git push --set-upstream origin <BRANCH_NAME>`

In the SBT repl:

```sbtshell
clean
project scalaApiModels
release
project tsApiModels
releaseNpm <versionNumber>
```
