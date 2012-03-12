**actions** provides automatic action-per-controller management making the job of wiring up actions in Views much easier.

## Usage ##

At the heart of this plugin lies the `ActionManager`. This singleton component is responsible for instantiating, configuring and keeping references to all actions per controller. It will automatically harvest all action candidates from a Controller once it has been instantiated. Each action has all of its properties configured following this strategy:

 *  match &lt;controller.class.name>.action.&lt;action.name>.&lt;key>
 *  match application.action.&lt;action.name>.&lt;key>

&lt;action.name> should be properly capitalized. In other words, you can configure action properties specifically per Controller or application wide. Available keys are

| Key                | Default value                                                                                   |
| ------------------ | ----------------------------------------------------------------------------------------------- |
| name               | GriffonNameUtils.getNaturalName() applied to the action's name - 'Action' suffix (if it exists) |
| accelerator        | undefined                                                                                       |
| command            | undefined                                                                                       |
| short_description  | undefined                                                                                       |
| long_description   | undefined                                                                                       |
| mnemonic           | undefined                                                                                       |
| small_icon         | undefined; should point to an URL in the classpath                                              |
| large_icon         | undefined; should point to an URL in the classpath                                              |
| enabled            | undefined; boolean value                                                                        |

Another responsibility of the ActionManager component is to place variable bindings in the group's builder that point to the actions themselves. This relieves you of the burden of declaring the variables in an `actions` block if the default settings are good enough.

## Example ##

The following Controller defines four actions, the first two as closure properties while the others as methods. Two actions have an 'Action' suffix in their names.

	package sample

	import java.awt.event.ActionEvent

	class SampleController {
	    def newAction = { ... }
	    def open = { ... }
	    void close(ActionEvent evt) { ... }
	    void deleteAction(ActionEvent evt) { ... }
	}

The actions `new` and `delete` use the 'Action' suffix in order to avoid compilation errors given that they make use of reserved keywords. It's all the same to the ActionManager as it will generate the following variables in the group's builder: `newAction`, `openAction`, `closeAction` and `deleteAction`. ActionManager expects the following keys to be available in the application's i18n resources (i.e. griffon-app/i18n/messages.properties)

    sample.SampleController.action.New.name = New
    sample.SampleController.action.Open.name = Open
    sample.SampleController.action.Close.name = Close
    sample.SampleController.action.Delete.name = Delete
    # additional keys per action elided

In the case that you'd like the close action to be customized for all controllers, say using the Spanish language then you'll require a file named `griffon-app/i18n/messages_es.properties` file with the following keys

    application.action.Close.name = Cerrar

Make sure to remove any controller specific keys when reaching for application wide configuration.

# History #

| Version | Notes                               |
| ------- | ----------------------------------- |
| 0.3     | switched to i18n-support dependency |
| 0.2     | Release sync with Griffon 0.9.4     |
| 0.1     | Initial release                     |
