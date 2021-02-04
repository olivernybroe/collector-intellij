<!-- Plugin description -->
<p align="center">
    <img src="https://github.com/olivernybroe/collector-intellij/blob/main/art/header.png?raw=1" alt="collector header">
    <p align="center">
        <a href="https://github.com/olivernybroe/collections-intellij"><img alt="GitHub Workflow Status (master)" src="https://github.com/olivernybroe/collections-intellij/workflows/Build/badge.svg"></a>
        <a href="https://plugins.jetbrains.com/plugin/15246"><img alt="Total Downloads" src="https://img.shields.io/jetbrains/plugin/d/15246"></a>
        <a href="https://plugins.jetbrains.com/plugin/15246"><img alt="Latest Version" src="https://img.shields.io/jetbrains/plugin/v/15246"></a>
	    <a href="https://plugins.jetbrains.com/plugin/15246"><img alt="Latest EAP Version" src="https://img.shields.io/badge/dynamic/xml?label=EAP version&query=%2Fplugin-repository%2Fcategory%2Fidea-plugin%5B1%5D%2Fversion&url=https%3A%2F%2Fplugins.jetbrains.com%2Fplugins%2Flist%3Fchannel%3Deap%26pluginId%3D15246"></a>
    </p>
</p>

# Collector - A collection's plugin for PhpStorm

This plugin adds support for Laravel Collections.

It contains a bunch of handy refactorings for making your collections better.
It can also convert normal PHP statements into collections.

## Installation
The plugin has no stable version, only manual and EAP is possible.


- ~~Using IDE built-in plugin system~~: (no stable release yet)

  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Collector"</kbd> >
  <kbd>Install Plugin</kbd>

- Manually:

  Download the [latest release](https://github.com/olivernybroe/collections-intellij/releases/latest) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Using Early Access Program (EAP) builds:

  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Manage plugin repositories</kbd>

  Add a new entry for [`https://plugins.jetbrains.com/plugins/eap/15246`](https://plugins.jetbrains.com/plugins/eap/15246)

  Then search for the plugin and install it as usual.

## Configuration
> :warning: The inspections might be disabled by default! Go to preferences for enabling them.

All the features are added as inspections in PhpStorm, this means you can disable
the ones you don't like or change how severe the warning are on them.  

For example, by default refactoring `foreach` to collection is not highlighting the text.  
However, if you would like to enforce yourself to never use `foreach`, you can change the severity to error.

![inspection-settings](https://github.com/olivernybroe/collector-intellij/blob/main/art/usage/inspection.jpg?raw=1)

## Features

### `foreach` to collection
![foreach-example](https://github.com/olivernybroe/collector-intellij/blob/main/art/usage/foreach.gif?raw=1)

### `array_map` to collection
![array_map-example](https://github.com/olivernybroe/collector-intellij/blob/main/art/usage/array_map.gif?raw=1)

### `map(...)->flatten(1)` to `flatMap`
![flatmap-example](https://github.com/olivernybroe/collector-intellij/blob/main/art/usage/flatmap.gif?raw=1)

### Remove nested collections
![recursive-collection-example](https://github.com/olivernybroe/collector-intellij/blob/main/art/usage/recursiveCollection.gif?raw=1)

### Closure to arrow functions
![closure-to-arrow-example](https://github.com/olivernybroe/collector-intellij/blob/main/art/usage/closureToArrow.gif?raw=1)

### `where(...)->first()` to `firstWhere`
![where-first-to-firstWhere-example](https://github.com/olivernybroe/collector-intellij/blob/main/art/usage/where-first-to-firstWhere.gif?raw=1)

### `where(...)->isNotEmpty()` to `contains`
![where-isNotEmpty-to-contains-example](https://github.com/olivernybroe/collector-intellij/blob/main/art/usage/whereIsNotEmpty-to-contains.gif?raw=1)

## Credits

- [Oliver Nybroe](https://github.com/olivernybroe)
- [All contributors](https://github.com/olivernybroe/collector-intellij/contributors)

Special thanks to [Caneco](https://twitter.com/caneco) for the logo ✨

<!-- Plugin description end -->

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
