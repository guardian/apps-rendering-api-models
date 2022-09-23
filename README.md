# apps-rendering-api-models

These models are used to communicate with the Apps Rendering API

## How to release

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
