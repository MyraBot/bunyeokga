# 🌐 Bunyeokga

Bunyeokga is a small library to make internationalization (i18n) easy. It features an argument handler which makes working
with more complex translations slightly more fun.
It's made for my private Discord wrapper. It uses `.properties` files for each langauge.

![Warning](https://raw.githubusercontent.com/MyraBot/.github/main/code-advise.png)

## 📌 Table of content

* [Setup](#-setup)
    * [Project structure](#-project-structure)
    * [Translation files](#-translation-files)
* [Use of internationalization](#-use)
  * [Different Methods](#-methods-of-applying-arguments)
  * [Arguments in arguments](#-arguments-in-arguments-lol)

## 🏗️ Setup

To use Bunyeokga call the `bunyeokga` function and set the `defaultLang` variable. This will be the fallback language. It
gets used whenever a translation is missing. **Make sure that the keys you use are always available in the language file you
set as `defaultLang`!** Otherwise an error will be thrown.  
The `directory` field is optional and can be used to place the translation files in a different directory than the
root `/resources` folder. In this example I've set the directory to be `translations/`.

```kotlin
bunyeokga {
    directory = "translations/"
    defaultLang = Locale.EN_GB
}.loadLanguages()
```

### 📁 Project structure

this project structure is a simplified kotlin project. The important bit is the `resources` and `translations` part. Because
we've set the `directory` field in the setup to be `translations/` we created another directory under the `resources` folder
called `translations`. If you leave the `directory` field in the bunyeokga builder empty, place your translations in the
root `resources` folder.

```
📦 Your project
┗ 📂 src/main
  ┣ 📂 kotlin
  ┗ 📂 resources
    ┗ 📂 translations
      ┣ 📜 en-GB.properties 
      ┗ 📜 de.properties
```

### 📄 Translation files

Here are two example translations files for english and german. Name them accordingly. For this you can use ISO 3166 1
alpha-2.

`en-GB.properties` - English

```properties
greeting=Hello {user}! How are you?
unknownUser=unknown user
```

`de.properties` - German

```properties
greeting=Hallo {user}! Wie geht's dir?
unknownUser=unbekannter Benutzer
```

## 🚧 Use

### 🖊️ Methods of applying arguments

There are a lot of ways to get the translated text. All are listed below. Every one of them is of course also possible
without arguments. Just leave them out then.

#### Message

Normal messages, so no interaction responses require the guild to find out the language to use.

```kotlin
val guild = Diskord.getGuild("1234567890")
val channel = guild.getChannel<TextChannel>("1234567890")

channel.send {
    arguments { arg("user", "Marian") }
    content = get(guild, "greeting")
}
```

#### Message with scope specific arguments

Add scope specific arguments, like here where the `user` argument gets only applied to the `greeting` translation.  
The same is possible with `Locale#get` instead of a `Guild#lang` since `Guild#lang` calls `Lang#get` internally.

```kotlin
val guild = Diskord.getGuild("1234567890")
val channel = guild.getChannel<TextChannel>("1234567890")

channel.send {
    content = guild.get("greeting") { arg("user", "Marian") }
}
```

#### Interaction response

Interaction responses don't require a guild argument because the language is provided in the event.

```kotlin
interactionEvent.acknowledge {
    arguments { arg("user", "Marian") }
    content = get("greeting")
}
```

### ⭐ Arguments in arguments lol
To not end up in an endless loop call the `ArgumentBuilder#get` function to use arguments in the argument builder.
```kotlin
interactionEvent.acknowledge {
    arguments {
        arg("{user}", this.get(guild, "unknownUser"))
    }
    content = get("greeting")
}
```