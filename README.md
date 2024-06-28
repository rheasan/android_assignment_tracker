# Android assignment

## Write a short description of each Android application component (Activity, Service, Broadcast Receiver, Content Provider).

1. Activity -> Activity is a single focused screen in an application. Activity provides a window in which UI can be drawn. Activities are managed in a stack, when a new activity is created it will be pushed on top of current stack.

2. Service -> Service is a component which is used to perform long running tasks. Foreground services perform operations that are noticable to the user, e.g. Music player playing an audio track. Background services perform tasks that are not noticable by the user, e.g. an application compacting its cached data. Bound services allow other services or processes to bind themselves to Unless specified otherwise it will run in the main thread of the host who started the service.

3. Broadcast Receiver -> Broadcast receiver is a component which can receive messages from other publishers. They can be used to communicate between apps outside of the normal user flow. Manifest declared broadcast receivers can receive messages even if the app is not running. When the receiver is registered in the system via the manifest, system can start the app and deliver the broadcast even if the app is not currently running. Context-registered receivers are active as long as the context they are registered in is valid. e.g. a broadcast receiver registered in an activity will be valid as long as the activity exists.

4. Content Provider -> Content provider component manages access to the central repository of data for an application. Content Provider along with provider client object can be used to allow other applications to access the current application's data.
---

## Describe the purpose and basic usage of common UI elements (TextView, EditText, Button, ImageView).

1. TextView -> This component is used to display text to the user
2. EditText -> Provides component to enter or modify text. The type of keyboard can be configured using ```android::inputType``` attribute.
3. Button -> Provides an interface that the user can tap on. A click listener can be defined for the button to execute code on click.
4. ImageView -> Can be used to display Image resources such as bitmap images or xml bitmaps.