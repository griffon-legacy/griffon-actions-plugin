/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 */

/**
 * @author Andres Almiray
 */
class ActionsGriffonPlugin {
    // the plugin version
    String version = '0.3'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '0.9.5 > *'
    // the other plugins this plugin depends on
    Map dependsOn = [swing: '0.9.5', 'i18n-support': '0.1']
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    List toolkits = ['swing']
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-actions-plugin'

    List authors = [
        [
            name: 'Andres Almiray',
            email: 'aalmiray@yahoo.com'
        ]
    ]
    String title = 'Action management'
    String description = '''
Provides automatic action-per-controller management making the job of wiring up actions in Views much easier.

Usage
-----

At the heart of this plugin lies the `ActionManager`. This singleton component is responsible for instantiating, configuring
and keeping references to all actions per controller. It will automatically harvest all action candidates from a Controller
once it has been instantiated. Each action has all of its properties configured following this strategy:

 * match &lt;controller.class.name&gt;.action.&lt;action.name&gt;.&lt;key&gt;
 * match application.action.&lt;action.name&gt;.&lt;key&gt;

&lt;action.name&gt; should be properly capitalized. In other words, you can configure action properties specifically per Controller
or application wide. Available keys are

| *Key*             | *Default value*                                                                                 |
| ----------------- | ----------------------------------------------------------------------------------------------- |
| name              | GriffonNameUtils.getNaturalName() applied to the action's name - 'Action' suffix (if it exists) |
| accelerator       | undefined                                                                                       |
| command           | undefined                                                                                       |
| short_description | undefined                                                                                       |
| long_description  | undefined                                                                                       |
| mnemonic          | undefined                                                                                       |
| small_icon        | undefined; should point to an URL in the classpath                                              |
| large_icon        | undefined; should point to an URL in the classpath                                              |
| enabled           | undefined; boolean value                                                                        |

Another responsibility of the ActionManager component is to place variable bindings in the group's builder that point to the actions
themselves. This relieves you of the burden of declaring the variables in an `actions` block if the default settings are good enough.

### Example

The following Controller defines four actions, the first two as closure properties while the others as methods. Two actions have an
'Action' suffix in their names.

        package sample
        import java.awt.event.ActionEvent
        class SampleController {
            def newAction = { ... }
            def open = { ... }
            void close(ActionEvent evt) { ... }
            void deleteAction(ActionEvent evt) { ... }
        }

The actions `new` and `delete` use the 'Action' suffix in order to avoid compilation errors given that they make use of reserved
keywords. It's all the same to the ActionManager as it will generate the following variables in the group's builder: `newAction`,
`openAction`, `closeAction` and `deleteAction`. ActionManager expects the following keys to be available in the application's i18n
resources (i.e. griffon-app/i18n/messages.properties)

        sample.SampleController.action.New.name = New
        sample.SampleController.action.Open.name = Open
        sample.SampleController.action.Close.name = Close
        sample.SampleController.action.Delete.name = Delete
        # additional keys per action elided

In the case that you'd like the close action to be customized for all controllers, say using the Spanish language, then you'll
have to provide a file named `griffon-app/i18n/messages_es.properties` with the following keys

        application.action.Close.name = Cerrar

Make sure to remove any controller specific keys when reaching for application wide configuration.
'''
}
