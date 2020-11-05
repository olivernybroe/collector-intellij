<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# collections-intellij Changelog

## [Unreleased]
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
