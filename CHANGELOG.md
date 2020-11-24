<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# collections-intellij Changelog

## [Unreleased]
### Added

### Changed

### Deprecated

### Removed

### Fixed
- Fixed closure to arrow function not working on collections variables

### Security

## [0.0.1-EAP.11]
### Changed
- Redefine how we determine if a call is a collection call

## [0.0.1-EAP.10]
### Added
- Added `where(...)->first()` to `firstWhere` refactoring ([#31](https://github.com/olivernybroe/collector-intellij/pull/31))

## [0.0.1-EAP.9]
### Added
- Added notification for enabling inspections if disabled

## [0.0.1-EAP.8]
### Fixed
- Ignore foreach to collection in blade files

## [0.0.1-EAP.7]
### Added
- Added closure to arrow function inside collection

### Fixed
- Fixed foreach refactoring. `use` statement contains empty variable when expression inside string interpolation.

## [0.0.1-EAP.6]
### Fixed
- Fixed foreach refactoring. `use` statement missing array variable when used in foreach.
- Fixed foreach refactoring. `use` statement having duplicate variable, if used twice.
- Fixed foreach refactoring. `use` statement containing array variable when using property accessor.
- Fixed `map->flatten` to `flatMap` when it contains indentations.

## [0.0.1-EAP.5]
### Fixed
- Fixed refactoring foreach to collection with multiline
- Fixed refactoring foreach to collection with this variable

## [0.0.1-EAP.4]
### Added
- Added `map(...)->flatten(1)` to `flatMap` refactoring ([#12](https://github.com/olivernybroe/collector-intellij/pull/12))
- Added removing of nested collection statement in collect function call.

## [0.0.1-EAP.3]
### Added
- Initial scaffold created from [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template)
- Added foreach to collection refactoring
- Added array map to collection refactoring
- Added logo to the plugin ([#13](https://github.com/olivernybroe/collector-intellij/pull/13))
