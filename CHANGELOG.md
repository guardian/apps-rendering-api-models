# apps-rendering-api-models

## 6.1.4

### Patch Changes

- 85ef657: Updates version of semver and breakword
- c16f161: Bump node to 18.18.0

## 6.1.3

### Patch Changes

- 9cc0b53: Added a Scala 2.13 version of the library.

## 6.1.2

### Patch Changes

- 3fe859f: Test release after PR #57 - was `pull-requests: write` required?

## 6.1.1

### Patch Changes

- 3f459e4: Test patch release to confirm PR #54 worked

## 6.1.0

### Minor Changes

- 577d2e2: This changes pulls in the latest version of content-api-models

## 6.0.0

### Major Changes

- 8c65e61: Adds required hidden field with type boolean to the FormField

## 5.0.1

### Patch Changes

- fe7b44b: Fixes the Contact field ids which prevented model compilation

## 5.0.0

### Major Changes

- 7b36c83: This breaking changes adds 2 additional fields to the contacts array on the callout campaign - urlPrefix, which is required, and guidance, which is optional

## 4.2.0

### Minor Changes

- bb6f172: Add an optional Contacts array to the Campaign Participation fields

## 4.1.0

### Minor Changes

- 2dd63cf: Bumping content-api-models version to "17.4.2". This version includes a showTableOfContents bool as per this PR https://github.com/guardian/content-api-models/pull/217.

## 4.0.0

### Major Changes

- de73054: Adding Europe edition to Editions enum

## 3.1.0

### Minor Changes

- a4e85db: add Newsletter.paused and Newsletter.regionFocus

## 3.0.0

### Major Changes

- 0b4af41: Alters the `campaigns` field to provide a list of all possible campaign types. Previously this field only included "participation" (or "callout") campaigns.

## 2.0.0

### Major Changes

- dbe3a7d: Bumping content-api-models version to "17.4.0". This version adds Callout to the CAPI thrift model as per this pr https://github.com/guardian/content-api-models/pull/216
