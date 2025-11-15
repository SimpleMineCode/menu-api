## Use in your projects

**Maven**
```XML
<dependency>
    <groupId>io.smcode</groupId>
    <artifactId>menu-api</artifactId>
    <version>1.1.0</version>
</dependency>
```

**Gradle**
```gradle
implementation("io.smcode:menu-api:1.1.0")
```

## Create a GUI
To make a GUI you have to create a class and let it extend `SimpleMenu`:

```JAVA
public class MyMenu extends SimpleMenu {
    public MyMenu() {
        super(Rows.THREE, Component.text("Title of the menu"));
    }

    @Override
    public void onSetItems() {
        // This method can be used to add items to the GUI
        final ItemStack item = new ItemStack(Material.BARRIER);

        setItem(13, item, player -> {
            // This will be executed when the player clicks this item
            player.sendMessage("You clicked this item");
        });
    }
}
```

